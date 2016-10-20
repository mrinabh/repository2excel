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

import org.apache.commons.collections4.map.HashedMap;

/**
 * 
 * @author mrinabh
 *
 */
public class Property {

	public String name;
	public String dataType;
	public String readable;
	public String writeable;
	public String hidden;
	public HashSet<HashedMap<String, String>> attributes;

	public String table;
	public String column;

	public Property() {
	}

	public Property(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String isReadable() {
		return readable;
	}

	public void setReadable(String readable) {
		this.readable = readable;
	}

	public String isWriteable() {
		return writeable;
	}

	public void setWriteable(String writeable) {
		this.writeable = writeable;
	}

	public String isHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public HashSet<HashedMap<String, String>> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashSet<HashedMap<String, String>> attributes) {
		this.attributes = attributes;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
}
