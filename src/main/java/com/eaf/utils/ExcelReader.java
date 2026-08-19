package com.eaf.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader implements AutoCloseable {

    private final Workbook workbook;

    public ExcelReader(String filePath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            this.workbook = WorkbookFactory.create(fileInputStream);
        }
    }

    public String getCellData(String sheetName, int rowNumber, int columnNumber) {

        Sheet sheet = getSheet(sheetName);

        Row row = sheet.getRow(rowNumber);

        if (row == null) {
            throw new IllegalArgumentException(
                    "Row " + rowNumber + " does not exist in sheet: " + sheetName
            );
        }

        Cell cell = row.getCell(columnNumber);

        if (cell == null) {
            throw new IllegalArgumentException(
                    "Cell at row " + rowNumber +
                            ", column " + columnNumber +
                            " does not exist in sheet: " + sheetName
            );
        }

        return getCellValue(cell);
    }

    public int getRowCount(String sheetName) {

        Sheet sheet = getSheet(sheetName);

        return sheet.getLastRowNum();
    }

    public int getColumnCount(String sheetName) {

        Sheet sheet = getSheet(sheetName);

        Row headerRow = sheet.getRow(0);

        if (headerRow == null) {
            return 0;
        }

        return headerRow.getLastCellNum();
    }

    private Sheet getSheet(String sheetName) {

        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new IllegalArgumentException(
                    "Sheet '" + sheetName + "' does not exist in Excel workbook."
            );
        }

        return sheet;
    }

    private String getCellValue(Cell cell) {

        DataFormatter dataFormatter = new DataFormatter();

        return dataFormatter.formatCellValue(cell);
    }

    @Override
    public void close() throws IOException {
        workbook.close();
    }
}