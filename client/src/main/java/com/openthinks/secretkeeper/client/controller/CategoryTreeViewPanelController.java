package com.openthinks.secretkeeper.client.controller;

import java.io.IOException;
import java.util.Set;

import com.openthinks.secretkeeper.client.model.CategoryData;
import com.openthinks.secretkeeper.common.StaticDict;
import com.openthinks.secretkeeper.common.domain.Category;
import com.openthinks.secretkeeper.common.service.CategoryService;
import com.openthinks.secretkeeper.common.utils.BeanLoader;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class CategoryTreeViewPanelController extends BaseController {

	@FXML
	private TreeView<CategoryData> tv_categories;

	private CategoryService categoryService = BeanLoader.loadBean(CategoryService.class);

	private CategoryData topCategoryData;

	@Override
	protected void initUI() throws IOException {
		super.initUI();
		TreeItem<CategoryData> rootItem = buildTreeView(topCategoryData);
		tv_categories.setRoot(rootItem);
		tv_categories.setShowRoot(false);
	}

	@Override
	protected void initModel() {
		super.initModel();
		topCategoryData = new CategoryData(new Category("TOP", StaticDict.CATEGORY_TOP_LEVEL));
		Set<Category> categories = categoryService.getCategoriesByLevel(StaticDict.CATEGORY_ROOT_LEVEL);
		categories.parallelStream().forEach((categoryDomain) -> {
			CategoryData root = buildTreeData(categoryDomain);
			topCategoryData.addChild(root);
		});
	}

	private CategoryData buildTreeData(Category categoryDomain) {
		CategoryData root = new CategoryData(categoryDomain);
		String parentID = categoryDomain.getUniqueID();
		Set<Category> children = categoryService.getCategoriesByParent(parentID);
		if (children.size() == 0)
			return root;
		children.parallelStream().forEach((subCategoryDomain) -> {
			CategoryData child = buildTreeData(subCategoryDomain);
			root.addChild(child);
		});
		return root;
	}

	private TreeItem<CategoryData> buildTreeView(CategoryData categoryData) {
		TreeItem<CategoryData> item = new TreeItem<>();
		item.setValue(categoryData);
		if (categoryData == null || categoryData.childrenSize() == 0) {
			return item;
		}
		categoryData.getChildren().forEach((cateData) -> {
			TreeItem<CategoryData> subItem = buildTreeView(cateData);
			item.getChildren().add(subItem);
		});
		return item;
	}

}
