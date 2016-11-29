package com.openthinks.secretkeeper.client.controller;

import java.io.IOException;

import com.openthinks.secretkeeper.client.model.TransferData;
import com.openthinks.secretkeeper.common.utils.BeanLoader;

import javafx.fxml.FXML;
import javafx.scene.web.HTMLEditor;

public class ContentRightPanelController extends BaseController {

	@FXML
	private HTMLEditor he_content;

	@Override
	protected void initModel() {
		super.initModel();
		BeanLoader.loadBean(TransferData.class).registerController(this);
	}

	@Override
	protected void initUI() throws IOException {
		super.initUI();
	}

	public void setContent(String htmlText) {
		he_content.setHtmlText(htmlText);
	}

}
