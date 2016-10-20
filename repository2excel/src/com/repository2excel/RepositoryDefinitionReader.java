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

package com.repository2excel;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.repository2excel.beans.Item;
import com.repository2excel.beans.Property;

/**
 * @author mrinabh
 *
 */
public class RepositoryDefinitionReader {

	public HashSet<Item> items;

	/**
	 * 
	 * @param file
	 */
	public HashSet<Item> loadRepositoryDefinition(File xmlFile) {
		items = new HashSet<Item>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbf.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			NodeList items = doc.getElementsByTagName("item-descriptor");
			for (int i = 0; i < items.getLength(); i++) {
				addItem(items.item(i));
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}

	private void addItem(Node node) {
		if (node != null) {
			String itemName = node.getAttributes().getNamedItem("name").getNodeValue();
			System.out.println("- Found item descriptor: " + itemName);
			Item item = new Item(itemName);
			NamedNodeMap nodeMap = node.getAttributes();
			for (int i = 0; i < nodeMap.getLength(); i++) {
				String nodeName = nodeMap.item(i).getNodeName();
				String nodeValue = nodeMap.item(i).getNodeValue();
				switch (nodeName) {
				case "cache-mode":
					item.setCacheMode(nodeValue);
					break;
				case "hidden":
					item.setHidden(nodeValue);
					break;
				case "writable":
					item.setWriteable(nodeValue);
					break;
				case "query-cache-size":
					item.setQueryCacheSize(nodeValue);
					break;
				case "item-cache-size":
					item.setItemCacheSize(nodeValue);
					break;
				default:
					// TODO:
				}
			}

			populateItemProperties(node, item);
			items.add(item);
		}
	}

	private void populateItemProperties(Node node, Item item) {
		NodeList children = node.getChildNodes();
		if (children != null) {
			HashSet<Property> properties = new HashSet<Property>();
			for (int i = 0; i < children.getLength(); i++) {
				Node current = children.item(i);
				if (current.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) current;
					switch (element.getTagName()) {
					case "table":
						System.out.println("-- Found table: " + element.getAttribute("name"));
						extractPropertiesFromTable(element, properties);
						break;
					case "attribute":
						// TODO:
						break;
					}
				}
			}
			item.setProperties(properties);
		}
	}

	private void extractPropertiesFromTable(Element table, HashSet<Property> properties) {
		NodeList children = table.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node current = children.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) current;
				switch (element.getTagName()) {
				case "property":
					System.out.println("--- Adding property: " + element.getAttribute("name"));
					Property property = new Property(element.getAttribute("name"));
					property.setTable(table.getAttribute("name"));
					property.setReadable(element.getAttribute("readable"));
					property.setHidden(element.getAttribute("hidden"));
					property.setWriteable(element.getAttribute("writable"));
					property.setDataType(element.getAttribute("data-type"));
					property.setColumn(element.getAttribute("column-name"));
					properties.add(property);
					break;
				default:
					// TODO:
					break;
				}
			}
		}

	}
}
