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
* @Title: ModelUtils.java 
* @Package com.openthinks.secretkeeper.client.model.support 
* @Description: TODO
* @author dailey.yet@outlook.com  
* @date Nov 21, 2016
* @version V1.0   
*/
package com.openthinks.secretkeeper.client.model.support;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.openthinks.secretkeeper.client.model.CategoryData;
import com.openthinks.secretkeeper.client.model.ItemData;
import com.openthinks.secretkeeper.common.domain.Category;
import com.openthinks.secretkeeper.common.domain.Item;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class ModelUtils {

	public static Set<CategoryData> toCategoryData(Set<Category> cateroies) {
		Set<CategoryData> sets = new HashSet<>();
		if (cateroies != null) {
			sets = cateroies.parallelStream().map((domain) -> {
				return new CategoryData(domain);
			}).collect(Collectors.toSet());
		}
		return sets;
	}

	public static Set<ItemData> toItemData(Set<Item> items) {
		Set<ItemData> sets = new HashSet<>();
		if (items != null) {
			sets = items.parallelStream().map((domain) -> {
				return new ItemData(domain);
			}).collect(Collectors.toSet());
		}
		return sets;
	}

	public static ItemData toItemData(Item item) {
		ItemData data = null;
		if (item != null) {
			data = new ItemData(item);
		}
		return data;
	}

	public static CategoryData toCategoryData(Category category) {
		CategoryData data = null;
		if (category != null) {
			data = new CategoryData(category);
		}
		return data;
	}
}
