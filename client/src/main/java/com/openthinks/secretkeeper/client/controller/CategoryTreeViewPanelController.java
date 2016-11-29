package com.openthinks.secretkeeper.client.controller;

import java.io.IOException;
import java.util.Set;

import com.openthinks.libs.utilities.logger.ProcessLogger;
import com.openthinks.secretkeeper.client.controller.support.ctvpc.ContextMenuItemsPropetyPool;
import com.openthinks.secretkeeper.client.model.CategoryData;
import com.openthinks.secretkeeper.client.model.ItemData;
import com.openthinks.secretkeeper.client.model.TransferData;
import com.openthinks.secretkeeper.client.model.support.ModelUtils;
import com.openthinks.secretkeeper.common.StaticDict;
import com.openthinks.secretkeeper.common.domain.Category;
import com.openthinks.secretkeeper.common.service.CategoryService;
import com.openthinks.secretkeeper.common.service.ItemService;
import com.openthinks.secretkeeper.common.utils.BeanLoader;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.EventDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ContextMenuEvent;

public class CategoryTreeViewPanelController extends BaseController {

	@FXML
	private TreeView<CategoryData> tv_categories;
	@FXML
	private ContextMenu cm_tree_menus;

	private CategoryService categoryService = BeanLoader.loadBean(CategoryService.class);

	private ItemService itemService = BeanLoader.loadBean(ItemService.class);

	private CategoryData topCategoryData;

	private ChangeListener<TreeItem<CategoryData>> treeNodeChangeListener;

	@Override
	protected void initModel() {
		super.initModel();
		BeanLoader.loadBean(TransferData.class).registerController(this);
		topCategoryData = new CategoryData(new Category("TOP", StaticDict.CATEGORY_TOP_LEVEL));
		Set<Category> categories = categoryService.getCategoriesByLevel(StaticDict.CATEGORY_ROOT_LEVEL);
		categories.parallelStream().forEach((categoryDomain) -> {
			CategoryData root = buildTreeData(categoryDomain);
			topCategoryData.addChildCategory(root);
		});
	}

	@Override
	protected void initUI() throws IOException {
		super.initUI();
		TreeItem<CategoryData> rootItem = buildTreeView(topCategoryData);
		tv_categories.setRoot(rootItem);
		tv_categories.setShowRoot(false);
		tv_categories.getSelectionModel().selectedItemProperty().addListener(treeNodeChangeListener);
		cm_tree_menus.getItems().filtered((menuItem) -> {
			return menuItem.getId() != null;
		}).forEach((menuItem) -> {
			menuItem.visibleProperty().bind(ContextMenuItemsPropetyPool.getVisableObservableValue(menuItem));
		});
	}

	@Override
	protected void initEvents() {
		super.initEvents();

		EventDispatcher oldEventDispatcher = tv_categories.getEventDispatcher();
		tv_categories.setEventDispatcher((event, tail) -> {
			if (event instanceof ContextMenuEvent) {
				ContextMenuEvent contextEvent = (ContextMenuEvent) event;
				if (tv_categories.getSelectionModel().isEmpty())
					contextEvent.consume();
			}
			return oldEventDispatcher.dispatchEvent(event, tail);
		});

		treeNodeChangeListener = (observable, oldValue, newValue) -> {
			ProcessLogger.debug("Tree view: Change selected node from " + oldValue + " to " + newValue);
			if (newValue != null) {
				CategoryData categoryData = newValue.getValue();
				Set<ItemData> itemDatas = null;
				ProcessLogger.debug("Tree view: New selected node item size:" + categoryData.childrenItemSize());
				if (categoryData.childrenItemSize() == 0) {
					String categoryID = categoryData.getPreload().getUniqueID();
					itemDatas = ModelUtils.toItemData(itemService.getItemsByCategory(categoryID));
					categoryData.setChildrenItem(itemDatas);
				} else {
					itemDatas = categoryData.getChildrenItem();
				}
				BeanLoader.loadBean(TransferData.class)
						.setItemDataPropertyValue(FXCollections.observableArrayList(itemDatas))
						.setSelectedCategoryPropertyValue(categoryData);
				ContextMenuItemsPropetyPool.compute(categoryData);
			}
		};
	}

	private CategoryData buildTreeData(Category categoryDomain) {
		CategoryData root = new CategoryData(categoryDomain);
		String parentID = categoryDomain.getUniqueID();
		Set<Category> children = categoryService.getCategoriesByParent(parentID);
		if (children.size() == 0)
			return root;
		children.parallelStream().forEach((subCategoryDomain) -> {
			CategoryData child = buildTreeData(subCategoryDomain);
			root.addChildCategory(child);
		});
		return root;
	}

	private TreeItem<CategoryData> buildTreeView(CategoryData categoryData) {
		TreeItem<CategoryData> item = new TreeItem<>();
		item.setValue(categoryData);
		if (categoryData == null || categoryData.childrenCategorySize() == 0) {
			return item;
		}
		categoryData.getChildrenCategory().forEach((cateData) -> {
			TreeItem<CategoryData> subItem = buildTreeView(cateData);
			item.getChildren().add(subItem);
		});
		return item;
	}

}
