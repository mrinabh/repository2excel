/*
 * Licensed to the Apache Software Foundation (ASF) under one
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
 */

package com.repository2excel.beans;

import java.util.HashSet;

/**
 * 
 * @author mrinabh
 *
 */
public class Item {

	public String name;
	public String cacheMode;
	public String writeable;
	public String hidden;
	public String queryCacheSize;
	public String itemCacheSize;
	public HashSet<Property> properties;

	public Item() {
	}

	public Item(String name) {
		this.name = name;
	}

	public String getCacheMode() {
		return cacheMode;
	}

	public void setCacheMode(String cacheMode) {
		this.cacheMode = cacheMode;
	}

	public HashSet<Property> getProperties() {
		return properties;
	}

	public void setProperties(HashSet<Property> properties) {
		this.properties = properties;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWriteable() {
		return writeable;
	}

	public void setWriteable(String writeable) {
		this.writeable = writeable;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getQueryCacheSize() {
		return queryCacheSize;
	}

	public void setQueryCacheSize(String queryCacheSize) {
		this.queryCacheSize = queryCacheSize;
	}

	public String getItemCacheSize() {
		return itemCacheSize;
	}

	public void setItemCacheSize(String itemCacheSize) {
		this.itemCacheSize = itemCacheSize;
	}
}
