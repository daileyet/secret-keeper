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

	@FXML
	private AnchorPane sp_content_left;

	@FXML
	private AnchorPane sp_content_right;

	@Override
	protected void initUI() throws IOException {
		super.initUI();
		initCategoryTreeViewPanel();
		initContentLeftPanel();
		initContentRightPanel();
	}

	private void initContentRightPanel() throws IOException {
		FXMLLoader loader = new FXMLLoader(ResourceLoader.FXML_HTMLEDITOR, this.getResourceBundle());
		AnchorPane htmlEditorPanel = loader.load();
		sp_content_right.getChildren().add(htmlEditorPanel);
		AnchorPane.setLeftAnchor(htmlEditorPanel, 0D);
		AnchorPane.setRightAnchor(htmlEditorPanel, 0D);
		AnchorPane.setTopAnchor(htmlEditorPanel, 0D);
		AnchorPane.setBottomAnchor(htmlEditorPanel, 0D);
	}

	private void initContentLeftPanel() throws IOException {
		FXMLLoader loader = new FXMLLoader(ResourceLoader.FXML_LISTVIEW, this.getResourceBundle());
		AnchorPane listViewPanel = loader.load();
		sp_content_left.getChildren().add(listViewPanel);
		AnchorPane.setLeftAnchor(listViewPanel, 0D);
		AnchorPane.setRightAnchor(listViewPanel, 0D);
		AnchorPane.setTopAnchor(listViewPanel, 0D);
		AnchorPane.setBottomAnchor(listViewPanel, 0D);
	}

	private void initCategoryTreeViewPanel() throws IOException {
		FXMLLoader loader = new FXMLLoader(ResourceLoader.FXML_TREEVIEW, this.getResourceBundle());
		ScrollPane treeViewPanel = loader.load();
		sp_main_left.getChildren().add(treeViewPanel);
		AnchorPane.setLeftAnchor(treeViewPanel, 0D);
		AnchorPane.setRightAnchor(treeViewPanel, 0D);
		AnchorPane.setTopAnchor(treeViewPanel, 0D);
		AnchorPane.setBottomAnchor(treeViewPanel, 0D);
	}
}
