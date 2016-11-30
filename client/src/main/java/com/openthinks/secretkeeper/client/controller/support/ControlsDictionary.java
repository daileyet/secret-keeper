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
* @Title: ControlsDictionary.java 
* @Package com.openthinks.secretkeeper.client.controller 
* @Description: TODO
* @author dailey.yet@outlook.com  
* @date Nov 28, 2016
* @version V1.0   
*/
package com.openthinks.secretkeeper.client.controller.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.openthinks.libs.utilities.logger.ProcessLogger;
import com.openthinks.secretkeeper.client.controller.support.ctvpc.ContextMenuActionList;
import com.openthinks.secretkeeper.client.controller.support.ctvpc.MenuActionList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.stage.WindowEvent;

/**
 * @author dailey.yet@outlook.com
 *
 */
public final class ControlsDictionary {
	private static final Map<String, ControlEventMap> allEventMap = new ConcurrentHashMap<>();
	/**
	 * initial event map
	 */
	static {
		//control id <=> event map
		ControlEventMap.valueOf("mi_new_notebook").append(ActionEvent.ACTION, MenuActionList::createNoteBook);

		ControlEventMap.valueOf("cm_tree_menus")
				.append(WindowEvent.WINDOW_SHOWING, ContextMenuActionList::contextMenuOnShowing)
				.append(WindowEvent.WINDOW_SHOWN, ContextMenuActionList::contextMenuOnShown);

		//ControlEventMap.valueOf("tv_categories")
	}// end of initial event map

	public static final <T extends Event> void register(String eventCode, EventType<T> eventType,
			EventHandler<T> handler) {
		ControlEventMap.valueOf(eventCode).append(eventType, handler);
	}

	@SuppressWarnings("unchecked")
	public static final <T extends Event> EventHandler<T> getEventHandler(String eventCode, EventType<T> eventType) {
		ControlEventMap controlEventMap = allEventMap.get(eventCode);
		if (controlEventMap != null) {
			return (EventHandler<T>) controlEventMap.get(eventType);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static final <T extends Event> void fireEventHandler(String eventCode, T event) {
		EventHandler<T> eventHandler = (EventHandler<T>) getEventHandler(eventCode, event.getEventType());
		if (eventHandler != null) {
			try {
				eventHandler.handle(event);
			} catch (Exception e) {
				ProcessLogger.error(e);
			}
		}
	}

	static class ControlEventMap extends ConcurrentHashMap<EventType<? extends Event>, EventHandler<? extends Event>> {
		private static final long serialVersionUID = -2710468918315709724L;
		private String eventCode;

		public static final ControlEventMap valueOf(String eventCode) {
			ControlEventMap controlEventMap = null;
			if (eventCode != null && !eventCode.isEmpty()) {
				controlEventMap = ControlsDictionary.allEventMap.get(eventCode);
				if (controlEventMap != null) {
					return controlEventMap;
				} else {
					controlEventMap = new ControlEventMap();
					controlEventMap.eventCode = eventCode;
					ControlsDictionary.allEventMap.put(eventCode, controlEventMap);
				}
			}
			return controlEventMap;
		}

		public String getEventCode() {
			return eventCode;
		}

		public <T extends Event> ControlEventMap append(EventType<T> eventType, EventHandler<T> handler) {
			this.put(eventType, handler);
			return this;
		}
	}
}
