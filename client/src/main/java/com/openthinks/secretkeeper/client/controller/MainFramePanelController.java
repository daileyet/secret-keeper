package com.openthinks.secretkeeper.client.controller;

import java.io.IOException;

import com.openthinks.secretkeeper.client.ResourceLoader;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainFramePanelController extends BaseController {

	@FXML
	private BorderPane bp_root;
	@FXML
	private AnchorPane sp_main_left;

	@Override
	protected void initUI() throws IOException {
		super.initUI();
		FXMLLoader loader = new FXMLLoader(ResourceLoader.FXML_TREEVIEW, this.getResourceBundle());
		ScrollPane treeViewPanel = loader.load();
		sp_main_left.getChildren().add(treeViewPanel);
		AnchorPane.setLeftAnchor(treeViewPanel, 0D);
		AnchorPane.setRightAnchor(treeViewPanel, 0D);
		AnchorPane.setTopAnchor(treeViewPanel, 0D);
		AnchorPane.setBottomAnchor(treeViewPanel, 0D);
	}
}
