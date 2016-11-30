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
* @Title: ControllerContextFactory.java 
* @Package com.openthinks.secretkeeper.client.controller.context 
* @Description: TODO
* @author dailey.yet@outlook.com  
* @date Nov 30, 2016
* @version V1.0   
*/
package com.openthinks.secretkeeper.client.controller.context;

import java.util.Map;

import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

import com.openthinks.libs.utilities.Checker;
import com.openthinks.secretkeeper.client.controller.BaseController;

/**
 * @author dailey.yet@outlook.com
 *
 */
public final class ControllerContextFactory {

	public static final ControllerContext get() {
		return DefaultControllerContext.INSTANCE;
	}

	static class DefaultControllerContext implements ControllerContext {
		private static final ControllerContext INSTANCE = new DefaultControllerContext();
		private final Map<Class<? extends BaseController>, BaseController> controllerMap = new ConcurrentHashMap<>();

		@SuppressWarnings("unchecked")
		@Override
		public <T extends BaseController> T getController(Class<T> controllerClass) {
			Checker.require(controllerClass).notNull();
			return (T) controllerMap.get(controllerClass);
		}

		@Override
		public <T extends BaseController> void registerController(T controller) {
			controllerMap.put(controller.getClass(), controller);
		}
	}
}
