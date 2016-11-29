package com.openthinks.secretkeeper.client;

import java.net.URL;

import com.openthinks.libs.i18n.implement.bundle.IBundleMessageType;

import javafx.scene.image.Image;

/**
 * Resource management
 * @author dailey.yet@outlook.com
 */
public class ResourceLoader {
	public static final Image APP_ICON;
	public static final URL FXML_MAINFRAME;
	public static final URL FXML_TREEVIEW;
	public static final URL FXML_LISTVIEW;
	public static final URL CSS_APP;
	public static final String BASE_PACK_DIR = "resources/lang/";
	public static final URL FXML_HTMLEDITOR;
	public static final URL FXML_CATEGORYDIALOG;

	static {
		// UI layout FXML
		FXML_MAINFRAME = ResourceLoader.class
				.getResource("/com/openthinks/secretkeeper/client/view/fxml/MainFramePanel.fxml");
		FXML_TREEVIEW = ResourceLoader.class
				.getResource("/com/openthinks/secretkeeper/client/view/fxml/CategoryTreeViewPanel.fxml");
		FXML_LISTVIEW = ResourceLoader.class
				.getResource("/com/openthinks/secretkeeper/client/view/fxml/ContentLeftPanel.fxml");
		FXML_HTMLEDITOR = ResourceLoader.class
				.getResource("/com/openthinks/secretkeeper/client/view/fxml/ContentRightPanel.fxml");

		FXML_CATEGORYDIALOG = ResourceLoader.class
				.getResource("/com/openthinks/secretkeeper/client/view/fxml/CategoryNewPanel.fxml");
		// app icon and css style
		CSS_APP = ResourceLoader.class.getResource("");
		APP_ICON = new Image(ResourceLoader.class.getResourceAsStream(""));

	}

	public static enum Bundles implements IBundleMessageType {
		UI;

		@Override
		public String value() {
			return toString() + ":" + BASE_PACK_DIR + toString();
		}

		@Override
		public String getMessageType() {
			String packType = null;
			String val = value();
			if (val != null) {
				int split = val.indexOf(":");
				if (-1 != split) {
					packType = val.substring(0, split);
				}
			}
			return packType;
		}

		@Override
		public String getPackName() {
			String packName = null;
			String val = value();
			if (val != null) {
				int split = val.indexOf(":");
				if (-1 != split) {
					packName = val.substring(split + 1);
				}
			}
			return packName;
		}

	}
}