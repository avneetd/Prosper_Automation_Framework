package com.prosper.utilityClasses;

import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	@SuppressWarnings("static-access")
	public static Object[][] readExcelData(String excelFileName,
			String wSheetName) {
		try {
			// get input Stream
			InputStream is = ClassLoader.getSystemClassLoader()
					.getSystemResourceAsStream(excelFileName);
			// Create te object of workbook

			XSSFWorkbook excelFile = new XSSFWorkbook(is);
			// get The worksheet
			XSSFSheet workSheet = excelFile.getSheet(wSheetName);

			System.out.println(workSheet.getLastRowNum());
			int noOfRows = workSheet.getLastRowNum() + 1;
			int noOfCol = workSheet.getRow(0).getLastCellNum();

			Object[][] data = new Object[noOfRows - 1][noOfCol];

			for (int i = 0; i < noOfRows - 1; i++) {

				XSSFRow row = workSheet.getRow(i + 1);

				Object[] tempData = new Object[noOfCol];
				boolean isRowContainValue = false;
				for (int j = 0; j < noOfCol; j++) {
					XSSFCell cell = row.getCell(j);
					Object value = cellToObject(cell);
					tempData[j] = value;

					if (cell == null) {
						continue;
					}
					isRowContainValue = true;
					;

				}

				if (isRowContainValue) {
					data[i] = tempData;
				}

			}

			return data;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	private static Object cellToObject(XSSFCell cell) {
		int type;
		Object result;
		if (cell == null) {
			return null;
		}
		type = cell.getCellType();

		switch (type) {
		case XSSFCell.CELL_TYPE_NUMERIC: // 0
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			// result = cell.getNumericCellValue();
			result = cell.getStringCellValue();

			if ("-".equals((String) result)) {
				result = null;
			}
			break;
		case XSSFCell.CELL_TYPE_STRING: // 1
			result = cell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_FORMULA: // 2
			throw new RuntimeException("We can't evaluate formulas in Java");
		case XSSFCell.CELL_TYPE_BLANK: // 3
			result = "-";
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN: // 4
			result = cell.getBooleanCellValue();
			break;
		case XSSFCell.CELL_TYPE_ERROR: // 5
			throw new RuntimeException("This cell has an error");
		default:
			throw new RuntimeException("We don't support this cell type: "
					+ type);
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object[][] dataObj = readExcelData("calcdata.xlsx", "Sheet1");
		int noRows = dataObj.length;
		int noOfCols = dataObj[0].length;
		for (int i = 0; i < noRows; i++) {
			for (int j = 0; j < noOfCols; j++) {
				System.out.print(dataObj[i][j] + "  ");
			}
			System.out.println();
		}
	}

}

