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
package com.openthinks.secretkeeper.client.controller.support;

import javafx.event.ActionEvent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

/**
 * @author dailey.yet@outlook.com
 *
 */
public final class MenuActionList {

	public static void createNoteBook(ActionEvent event) {
		System.out.println("create notebook");
	}

	public static void contextMenuOnShowing(WindowEvent event) {
		event.consume();
		System.out.println("context menu showing");
	}

	public static void contextMenuOnShown(WindowEvent event) {
		event.consume();
		System.out.println("context menu shown");
	}

	public static void contextMenuOnRequested(ContextMenuEvent event) {
		System.out.println("context menu on requested");
		event.consume();
	}

	public static void treeViewOnMouseClicked(MouseEvent event) {
		System.out.println("tree view on mouse clicked");
		event.consume();
	}

}
