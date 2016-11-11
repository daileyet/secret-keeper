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
* @Title: App.java 
* @Package com.openthinks.secretkeeper.client 
* @Description: TODO
* @author dailey.yet@outlook.com  
* @date Nov 2, 2016
* @version V1.0   
*/
package com.openthinks.secretkeeper.client;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.openthinks.libs.i18n.I18n;
import com.openthinks.libs.i18n.I18nApplicationLocale;
import com.openthinks.secretkeeper.client.controller.MainFramePanelController;
import com.openthinks.secretkeeper.client.model.TransferData;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class App extends Application implements Observer {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		I18nApplicationLocale.getInstance().addObserver(this);
		//primaryStage.getIcons().add(ResourceLoader.APP_ICON);
		primaryStage.setTitle(I18n.getMessage(ResourceLoader.Bundles.UI, "app.title"));
		primaryStage.setOnCloseRequest((event) -> {
			System.exit(0);
		});
		TransferData data = TransferData.build(primaryStage);
		Scene scene = getMainScene(data);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Scene getMainScene(TransferData data) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceLoader.FXML_MAINFRAME);
		loader.setResources(I18n.getResourceBundle(ResourceLoader.Bundles.UI));
		Parent root = loader.load();
		MainFramePanelController controller = loader.getController();
		controller.setTransferData(data);
		Scene mainScene = new Scene(root);
		return mainScene;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
