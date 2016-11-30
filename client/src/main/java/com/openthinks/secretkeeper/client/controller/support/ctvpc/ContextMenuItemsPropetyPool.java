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
* @Title: ContextMenuItemsPropetyPool.java 
* @Package com.openthinks.secretkeeper.client.controller.support.ctvpc 
* @Description: TODO
* @author dailey.yet@outlook.com  
* @date Nov 29, 2016
* @version V1.0   
*/
package com.openthinks.secretkeeper.client.controller.support.ctvpc;

import java.util.Map;

import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

import com.openthinks.libs.utilities.Checker;
import com.openthinks.secretkeeper.client.controller.CategoryTreeViewPanelController;
import com.openthinks.secretkeeper.client.model.CategoryData;
import com.openthinks.secretkeeper.client.model.TransferData;
import com.openthinks.secretkeeper.common.StaticDict;
import com.openthinks.secretkeeper.common.utils.BeanLoader;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.MenuItem;

/**
 * ContextMenu item visable management for {@link CategoryTreeViewPanelController}
 * @author dailey.yet@outlook.com
 *
 */
public final class ContextMenuItemsPropetyPool {
	private static final Map<String, ObservableValue<Boolean>> visableMap = new ConcurrentHashMap<>();

	static {
		visableMap.put("mi_new_notebook", new SimpleBooleanProperty(true));
		visableMap.put("smi_create", new SimpleBooleanProperty(true));
		visableMap.put("mi_rename_notebook", new SimpleBooleanProperty(true));
		visableMap.put("mi_delete_notebook", new SimpleBooleanProperty(true));
		visableMap.put("m_in_notebook", new SimpleBooleanProperty(true));
		visableMap.put("m_out_notebook", new SimpleBooleanProperty(true));
		visableMap.put("smi_operate", new SimpleBooleanProperty(true));
		visableMap.put("mi_properties_notebook", new SimpleBooleanProperty(true));

	}

	public static ObservableValue<Boolean> getVisableObservableValue(MenuItem menuItem) {
		Checker.require(menuItem).notNull();
		return getVisableObservableValue(menuItem.getId());
	}

	private static SimpleBooleanProperty getVisableObservableValue(String menuItemID) {
		Checker.require(menuItemID).notNull();
		SimpleBooleanProperty observableValue = (SimpleBooleanProperty) visableMap.get(menuItemID);
		if (observableValue == null) {
			observableValue = new SimpleBooleanProperty(true);
			visableMap.put(menuItemID, observableValue);
		}
		return observableValue;
	}

	public static void compute() {
		CategoryData categoryData = BeanLoader.loadBean(TransferData.class).getSelectedCategoryData();
		compute(categoryData);
	}

	public static void compute(CategoryData categoryData) {
		Checker.require(categoryData).notNull();
		//ProcessLogger.debug(categoryData.toString());

		reset();
		if (categoryData.getLevel() == StaticDict.CATEGORY_ROOT_LEVEL) {
			getVisableObservableValue("smi_create").set(false);
			getVisableObservableValue("mi_rename_notebook").set(false);
			getVisableObservableValue("mi_delete_notebook").set(false);
			getVisableObservableValue("m_in_notebook").set(false);
			getVisableObservableValue("m_out_notebook").set(false);
			getVisableObservableValue("smi_operate").set(false);
			getVisableObservableValue("mi_properties_notebook").set(false);
		} else if (categoryData.getLevel() == StaticDict.CATEGORY_CHILD_LEVEL_1) {
			getVisableObservableValue("m_out_notebook").set(false);
			getVisableObservableValue("smi_operate").set(false);
			getVisableObservableValue("mi_properties_notebook").set(false);
		} else if (categoryData.getLevel() == StaticDict.CATEGORY_CHILD_LEVEL_2) {
			getVisableObservableValue("mi_new_notebook").set(false);
			getVisableObservableValue("smi_create").set(false);
			getVisableObservableValue("m_in_notebook").set(false);
		}

		if (categoryData.isLeafLevel()) {
			getVisableObservableValue("mi_new_notebook").set(false);
			getVisableObservableValue("smi_create").set(false);
		}
	}

	private static void reset() {
		visableMap.forEach((key, value) -> {
			((SimpleBooleanProperty) value).set(true);
		});
	}
}
