package com.openthinks.secretkeeper.client.model;

import java.util.Map;

import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

import com.openthinks.libs.utilities.logger.ProcessLogger;
import com.openthinks.secretkeeper.client.controller.BaseController;
import com.openthinks.secretkeeper.client.controller.ContentLeftPanelController;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class TransferData {
	private final Property<ObservableList<ItemData>> itemDataProperty = new SimpleObjectProperty<>(
			FXCollections.observableArrayList());

	private final Property<CategoryData> selectedCategoryProperty = new SimpleObjectProperty<>();

	private final Map<Class<? extends BaseController>, BaseController> controllerMap = new ConcurrentHashMap<>();

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

	public <T extends BaseController> TransferData registerController(T controller) {
		controllerMap.put(controller.getClass(), controller);
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseController> T getController(Class<T> controllerClazz) {
		return (T) controllerMap.get(controllerClazz);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public TransferData setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		return this;
	}

	public TransferData setSelectedCategoryPropertyValue(CategoryData categoryData) {
		selectedCategoryProperty.setValue(categoryData);
		return this;
	}

	public Property<CategoryData> getSelectedCategoryProperty() {
		return selectedCategoryProperty;
	}

}
