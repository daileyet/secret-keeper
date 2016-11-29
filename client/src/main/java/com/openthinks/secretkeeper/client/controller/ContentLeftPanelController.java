package com.openthinks.secretkeeper.client.controller;

import java.io.IOException;

import com.openthinks.libs.utilities.logger.ProcessLogger;
import com.openthinks.secretkeeper.client.model.ItemData;
import com.openthinks.secretkeeper.client.model.TransferData;
import com.openthinks.secretkeeper.common.utils.BeanLoader;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ContentLeftPanelController extends BaseController {

	@FXML
	private ListView<ItemData> lv_conetnts;

	private ChangeListener<ItemData> listViewChangeListener;

	private final Property<ObservableList<ItemData>> itemDataProperty = BeanLoader.loadBean(TransferData.class)
			.getItemDataProperty();

	@Override
	protected void initModel() {
		super.initModel();
		BeanLoader.loadBean(TransferData.class).registerController(this);
	}

	@Override
	protected void initEvents() {
		super.initEvents();
		listViewChangeListener = (observable, oldValue, newValue) -> {
			ProcessLogger.debug("List view: Change selected item from " + oldValue + " to " + newValue);
			if (newValue != null) {
				String htmlText = newValue.getPreload().getContent();
				BeanLoader.loadBean(TransferData.class).getController(ContentRightPanelController.class)
						.setContent(htmlText);
			}
		};
	}

	@Override
	protected void initUI() throws IOException {
		super.initUI();
		lv_conetnts.getSelectionModel().selectedItemProperty().addListener(listViewChangeListener);
		lv_conetnts.itemsProperty().bindBidirectional(itemDataProperty);
	}
}
