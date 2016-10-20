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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.repository2excel.beans.Item;
import com.repository2excel.beans.Property;

/**
 * @author mrinabh
 *
 */
public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		String xmlRepositoryDefFilePath = "";

		/** Read user input */
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter fully qualified path to customCatalog.xml:");
		try {
			xmlRepositoryDefFilePath = scnr.next();
		} catch (InputMismatchException e) {
			// TODO:
		} finally {
			scnr.close();
		}

		RepositoryDefinitionReader reader = new RepositoryDefinitionReader();
		System.out.println("Begin reading XML Repository definition file...");
		HashSet<Item> items = reader.loadRepositoryDefinition(new File(xmlRepositoryDefFilePath));
		System.out.println("Finished reading XML file!");
		if (items != null && items.size() > 0) {
			System.out.println("Preparing to export " + items.size() + " items into Excel Spreadsheet...");
			SXSSFWorkbook wb = new SXSSFWorkbook(100);
			Sheet sh = wb.createSheet();

			/** Create cell styles */
			CellStyle style = wb.createCellStyle();
			style.setBorderBottom(CellStyle.BORDER_THIN);
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setBorderRight(CellStyle.BORDER_THIN);

			Iterator<Item> iter = items.iterator();
			int rownum = 0;
			while (iter.hasNext()) {
				Item item = iter.next();
				Row row = sh.createRow(rownum);
				row.createCell(0, CellType.STRING).setCellValue("Item");
				row.createCell(1, CellType.STRING).setCellValue(item.getName());
				rownum++;

				row = sh.createRow(rownum);
				row.createCell(0, CellType.STRING).setCellValue("Query Cache Size");
				row.createCell(1, CellType.STRING).setCellValue(item.getQueryCacheSize());
				rownum++;

				row = sh.createRow(rownum);
				row.createCell(0, CellType.STRING).setCellValue("Item Cache Size");
				row.createCell(1, CellType.STRING).setCellValue(item.getItemCacheSize());
				rownum++;
				HashSet<Property> properties = item.getProperties();
				if (properties != null && properties.size() > 0) {
					Cell cell;
					row = sh.createRow(rownum);
					cell = row.createCell(0, CellType.STRING);
					cell.setCellStyle(style);
					cell.setCellValue("Property");

					cell = row.createCell(1, CellType.STRING);
					cell.setCellStyle(style);
					cell.setCellValue("Type");

					cell = row.createCell(2, CellType.STRING);
					cell.setCellStyle(style);
					cell.setCellValue("Readable");

					cell = row.createCell(3, CellType.STRING);
					cell.setCellStyle(style);
					cell.setCellValue("Writable");

					cell = row.createCell(4, CellType.STRING);
					cell.setCellStyle(style);
					cell.setCellValue("Hidden");

					cell = row.createCell(5, CellType.STRING);
					cell.setCellStyle(style);
					cell.setCellValue("Table");

					cell = row.createCell(6, CellType.STRING);
					cell.setCellStyle(style);
					cell.setCellValue("Column");

					Iterator<Property> pIter = properties.iterator();
					while (pIter.hasNext()) {
						rownum++;
						row = sh.createRow(rownum);
						Property property = pIter.next();
						/** 0. Name */
						cell = row.createCell(0, CellType.STRING);
						cell.setCellStyle(style);
						cell.setCellValue(property.getName());

						/** 1. Data Type */
						cell = row.createCell(1, CellType.STRING);
						cell.setCellStyle(style);
						cell.setCellValue(property.getDataType());

						/** 2. Is Readable */
						cell = row.createCell(2, CellType.STRING);
						cell.setCellStyle(style);
						cell.setCellValue(property.isReadable());

						/** 3. Is Writable */
						cell = row.createCell(3, CellType.STRING);
						cell.setCellStyle(style);
						cell.setCellValue(property.isWriteable());

						/** 4. Is Hidden */
						cell = row.createCell(4, CellType.STRING);
						cell.setCellStyle(style);
						cell.setCellValue(property.isHidden());

						/** 5. Table */
						cell = row.createCell(5, CellType.STRING);
						cell.setCellStyle(style);
						cell.setCellValue(property.getTable());

						/** 6. Column */
						cell = row.createCell(6, CellType.STRING);
						cell.setCellStyle(style);
						cell.setCellValue(property.getColumn());
					}
				}
				rownum++;
				rownum++;
			}

			try {
				File f = new File("test.xlsx");
				FileOutputStream out = new FileOutputStream(f);
				wb.write(out);
				out.close();
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// dispose of temporary files backing this workbook on disk
				wb.dispose();
			}
		}
	}
}
