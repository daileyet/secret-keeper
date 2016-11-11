package com.openthinks.secretkeeper.client.controller;

import java.io.IOException;

import com.openthinks.secretkeeper.client.model.CategoryData;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class CategoryTreeViewPanelController extends BaseController {

	@FXML
	private TreeView<CategoryData> tv_categories;

	@Override
	protected void initUI() throws IOException {
		super.initUI();
		TreeItem<CategoryData> rootItem = createRootItem();
		tv_categories.setRoot(rootItem);
		tv_categories.setShowRoot(false);
	}

	private TreeItem<CategoryData> createRootItem() {
		TreeItem<CategoryData> rootItem = new TreeItem<>();
		rootItem.setValue(new CategoryData("Categories"));
		loadSubs(rootItem);
		return rootItem;
	}

	private void loadSubs(TreeItem<CategoryData> rootItem) {
		for (int i = 0; i < 100; i++) {
			TreeItem<CategoryData> item = new TreeItem<>(new CategoryData("Category " + i));
			if (i % 10 == 0)
				for (int j = 0; j < 10; j++) {
					TreeItem<CategoryData> subitem = new TreeItem<>(new CategoryData("Category " + j));
					item.getChildren().add(subitem);
				}
			rootItem.getChildren().add(item);
		}

	}

}
