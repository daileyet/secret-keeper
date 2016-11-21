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
* @Title: BaseController.java 
* @Package com.openthinks.secretkeeper.client.controller 
* @Description: TODO
* @author dailey.yet@outlook.com  
* @date Nov 8, 2016
* @version V1.0   
*/
package com.openthinks.secretkeeper.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.openthinks.libs.i18n.I18n;
import com.openthinks.libs.i18n.I18nApplicationLocale;
import com.openthinks.secretkeeper.client.ResourceLoader;

import javafx.fxml.Initializable;

/**
 * @author dailey.yet@outlook.com
 *
 */
public abstract class BaseController implements Initializable, Observer {
	protected ResourceBundle resourceBundle;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		I18nApplicationLocale.getInstance().addObserver(this);
		this.resourceBundle = resources;
		this.initModel();
		this.initEvents();
		try {
			this.initUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void initEvents() {
	}

	protected void initModel() {
	}

	protected void initUI() throws IOException {
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object newlocale) {
		this.resourceBundle = I18n.getResourceBundle(ResourceLoader.Bundles.UI, (Locale) newlocale);
	}

}
