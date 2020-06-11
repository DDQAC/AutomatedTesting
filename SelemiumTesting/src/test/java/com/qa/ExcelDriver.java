package com.qa;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriver {

	FileInputStream file;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	public FileInputStream getFile() {
		return file;
	}

	public void setFile(FileInputStream file) {
		this.file = file;
	}

	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public XSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}

	public ExcelDriver(String filePath) {
		try {
			this.file = new FileInputStream(filePath);
			this.workbook = new XSSFWorkbook(file);
			this.sheet = workbook.getSheetAt(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(int rowNum, int columnIndex, String result) {
		XSSFRow row = sheet.getRow(rowNum);
		XSSFCell cell = row.getCell(columnIndex);
		if (cell == null) {
			cell = row.createCell(columnIndex);
		}
		cell.setCellValue(result);
	}

	public void writeAuthentication(int rowNum, String result) {
		write(rowNum, 9, result);
	}

	public void writeResult(int rowNum, String result) {
		write(rowNum, 10, result);
	}

	public void writeAdded(int rowNum, String added) {
		write(rowNum, 5, added);
	}

}
