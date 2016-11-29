/**   
 *  Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
* @Title: MenuActionList.java 
* @Package com.openthinks.secretkeeper.client.controller.support 
* @Description: TODO
* @author dailey.yet@outlook.com  
* @date Nov 28, 2016
* @version V1.0   
*/
package com.openthinks.secretkeeper.client.controller.support.ctvpc;

import java.io.IOException;

import com.openthinks.libs.i18n.I18n;
import com.openthinks.libs.utilities.logger.ProcessLogger;
import com.openthinks.secretkeeper.client.ResourceLoader;
import com.openthinks.secretkeeper.client.model.TransferData;
import com.openthinks.secretkeeper.common.utils.BeanLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author dailey.yet@outlook.com
 *
 */
public final class MenuActionList {

	/**
	 * handler for click menu item "Create Notebook"
	 * @param event ActionEvent
	 */
	public static void createNoteBook(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceLoader.FXML_CATEGORYDIALOG);
		loader.setResources(I18n.getResourceBundle(ResourceLoader.Bundles.UI));
		try {
			AnchorPane anchorPane = loader.load();
			Stage stage = new Stage();
			//stage.getIcons().add(ResourceLoader.APP_ICON);
			stage.initOwner(BeanLoader.loadBean(TransferData.class).getPrimaryStage());
			stage.setTitle(I18n.getMessage(ResourceLoader.Bundles.UI, "stage.category.newdialog.title"));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setResizable(false);
			Scene scene = new Scene(anchorPane);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			ProcessLogger.error(e);
		}
	}

}
