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
* @Title: BeanLoader.java 
* @Package com.openthinks.secretkeeper.client 
* @Description: TODO
* @author dailey.yet@outlook.com  
* @date Nov 16, 2016
* @version V1.0   
*/
package com.openthinks.secretkeeper.common.utils;

import com.openthinks.libs.utilities.pools.object.SharedContext;

/**
 * @author dailey.yet@outlook.com
 *
 */
public abstract class BeanLoader {

	public final static <T> T loadBean(Class<T> beanClass) {
		return SharedContext.get().lookup(beanClass);
	}

	/**
	 * @param <T> super class/interface type
	 * @param <E> child class/implement class type
	 * @param beanSuperClass super class/interface
	 * @param beanImplClass child class/implement class
	 * @return bean instance
	 */
	public final static <T, E extends T> T loadBean(Class<T> beanSuperClass, Class<E> beanImplClass) {
		E implInstance = SharedContext.get().lookup(beanImplClass);
		SharedContext.get().register(beanSuperClass, implInstance);
		return implInstance;
	}

}
