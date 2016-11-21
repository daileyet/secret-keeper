package com.openthinks.secretkeeper.client.model;

import java.util.Map;

import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

import com.openthinks.libs.utilities.logger.ProcessLogger;
import com.openthinks.secretkeeper.client.controller.BaseController;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TransferData {
	private final Property<ObservableList<ItemData>> itemDataProperty = new SimpleObjectProperty<>(
			FXCollections.observableArrayList());

	private final Map<Class<? extends BaseController>, BaseController> controllerMap = new ConcurrentHashMap<>();

	public TransferData() {
		itemDataProperty.addListener((observable, oldVal, newVal) -> {
			ProcessLogger.debug("Change from " + oldVal + " to " + newVal);
		});
	}

	public Property<ObservableList<ItemData>> getItemDataProperty() {
		return itemDataProperty;
	}

	public void setItemDataPropertyValue(ObservableList<ItemData> itemDataObservableList) {
		itemDataProperty.setValue(itemDataObservableList);
	}

	public <T extends BaseController> void registerController(T controller) {
		controllerMap.put(controller.getClass(), controller);
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseController> T getController(Class<T> controllerClazz) {
		return (T) controllerMap.get(controllerClazz);
	}

}
