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
import com.openthinks.libs.utilities.logger.ProcessLogger;
import com.openthinks.secretkeeper.client.ResourceLoader;
import com.openthinks.secretkeeper.client.controller.context.ControllerContext;
import com.openthinks.secretkeeper.client.controller.context.ControllerContextFactory;
import com.openthinks.secretkeeper.client.controller.support.ControlsDictionary;

import javafx.css.Styleable;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

/**
 * @author dailey.yet@outlook.com
 *
 */
public abstract class BaseController implements Initializable, Observer {
	protected ResourceBundle resourceBundle;
	protected final ControllerContext context = ControllerContextFactory.get();
	{
		context.registerController(this);
	}

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

	public ControllerContext getContext() {
		return this.context;
	}

	public <T extends BaseController> T getController(Class<T> otherControllerClass) {
		return this.context.getController(otherControllerClass);
	}

	/**
	 * dispatch event to bind registered event handler 
	 * @param event {@link Event}
	 */
	public void dispatchEventHandler(Event event) {
		String eventCode = "";
		Object styleObj = null;
		if (event instanceof ContextMenuEvent || event instanceof MouseEvent) {
			styleObj = event.getSource();
		} else {
			styleObj = event.getTarget();
			//
		}
		if (styleObj instanceof Styleable) {
			eventCode = ((Styleable) styleObj).getId();
			ControlsDictionary.fireEventHandler(eventCode, event);
		} else {
			ProcessLogger.error("Event dispatch not successed! Unregistered control:" + event);
		}

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
