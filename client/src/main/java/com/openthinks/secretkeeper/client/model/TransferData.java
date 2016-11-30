package com.openthinks.secretkeeper.client.model;

import com.openthinks.libs.utilities.logger.ProcessLogger;
import com.openthinks.secretkeeper.client.controller.ContentLeftPanelController;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

public class TransferData {
	private final Property<ObservableList<ItemData>> itemDataProperty = new SimpleObjectProperty<>(
			FXCollections.observableArrayList());

	private final Property<TreeItem<CategoryData>> selectedCategoryProperty = new SimpleObjectProperty<>();

	private Stage primaryStage;

	public TransferData() {
		itemDataProperty.addListener((observable, oldVal, newVal) -> {
			ProcessLogger.debug("Change from " + oldVal + " to " + newVal);
		});
	}

	/**
	 * get item property from control ListView lv_conetnts in {@link ContentLeftPanelController}
	 * @return property object
	 */
	public Property<ObservableList<ItemData>> getItemDataProperty() {
		return itemDataProperty;
	}

	public TransferData setItemDataPropertyValue(ObservableList<ItemData> itemDataObservableList) {
		itemDataProperty.setValue(itemDataObservableList);
		return this;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public TransferData setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		return this;
	}

	public TransferData setSelectedCategoryPropertyValue(TreeItem<CategoryData> treeItem) {
		selectedCategoryProperty.setValue(treeItem);
		return this;
	}

	public Property<TreeItem<CategoryData>> getSelectedCategoryProperty() {
		return selectedCategoryProperty;
	}

	public TreeItem<CategoryData> getSelectedCategory() {
		return selectedCategoryProperty.getValue();
	}

	public CategoryData getSelectedCategoryData() {
		TreeItem<CategoryData> treeItem = selectedCategoryProperty.getValue();
		return treeItem == null ? null : treeItem.getValue();
	}

}
