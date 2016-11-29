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
* @Title: CategoryNewPanelController.java 
* @Package com.openthinks.secretkeeper.client.controller 
* @Description: TODO
* @author dailey.yet@outlook.com  
* @date Nov 29, 2016
* @version V1.0   
*/
package com.openthinks.secretkeeper.client.controller;

import java.io.IOException;

import com.openthinks.secretkeeper.client.controller.support.ControlsDictionary;
import com.openthinks.secretkeeper.client.model.CategoryData;
import com.openthinks.secretkeeper.client.model.TransferData;
import com.openthinks.secretkeeper.client.model.support.ModelUtils;
import com.openthinks.secretkeeper.common.domain.Category;
import com.openthinks.secretkeeper.common.service.CategoryService;
import com.openthinks.secretkeeper.common.utils.BeanLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class CategoryNewPanelController extends BaseController {
	@FXML
	private AnchorPane ap_category_dialog_panel;
	@FXML
	private TextField txt_category_title;
	@FXML
	private Button btn_category_dialog_save;
	@FXML
	private Button btn_category_dialog_cancel;

	private CategoryService categoryService = BeanLoader.loadBean(CategoryService.class);;

	@Override
	protected void initEvents() {
		super.initEvents();
		ControlsDictionary.register(btn_category_dialog_save.getId(), ActionEvent.ACTION, (event) -> {
			String title = txt_category_title.getText();
			CategoryData categoryData = BeanLoader.loadBean(TransferData.class).getSelectedCategoryProperty()
					.getValue();
			if (categoryData != null) {
				Category category = new Category(title, categoryData.getLevel() + 1);
				Category parentCategory = categoryData.getPreload();
				if (parentCategory.getUniqueID() != null)
					category.setParentID(parentCategory.getUniqueID());
				category = categoryService.create(category);
				if (category != null) {//create successfully
					ModelUtils.toCategoryData(category);
				}
			}
		});
		ControlsDictionary.register(btn_category_dialog_cancel.getId(), ActionEvent.ACTION, (event) -> {
			Stage stage = (Stage) ap_category_dialog_panel.getScene().getWindow();
			stage.close();
		});
	}

	@Override
	protected void initUI() throws IOException {
		super.initUI();
		btn_category_dialog_save.disableProperty().bind(txt_category_title.textProperty().isEmpty());
	}
}
