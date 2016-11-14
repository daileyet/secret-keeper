package com.openthinks.secretkeeper.client.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.openthinks.secretkeeper.client.model.ItemData;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ContentLeftPanelController extends BaseController {

	@FXML
	private ListView<ItemData> lv_conetnts;

	@Override
	protected void initUI() throws IOException {
		super.initUI();
		List<ItemData> itemsObj = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			itemsObj.add(new ItemData("item ----------------------------:" + i));
		}
		lv_conetnts.getItems().addAll(itemsObj);
	}
}
