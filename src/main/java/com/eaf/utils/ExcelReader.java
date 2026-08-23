package com.eaf.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader implements AutoCloseable {

    private final Workbook workbook;
    private final DataFormatter dataFormatter;

    public ExcelReader(String filePath) throws IOException {

        try (FileInputStream fileInputStream =
                     new FileInputStream(filePath)) {

            this.workbook =
                    WorkbookFactory.create(fileInputStream);
        }

        this.dataFormatter = new DataFormatter();
    }


    // Read cell using column index
    public String getCellData(
            String sheetName,
            int rowNumber,
            int columnNumber) {

        Sheet sheet = getSheet(sheetName);

        Row row = sheet.getRow(rowNumber);

        if (row == null) {
            throw new IllegalArgumentException(
                    "Row " + rowNumber
                            + " does not exist in sheet: "
                            + sheetName
            );
        }

        Cell cell = row.getCell(columnNumber);

        if (cell == null) {
            throw new IllegalArgumentException(
                    "Cell at row " + rowNumber
                            + ", column " + columnNumber
                            + " does not exist in sheet: "
                            + sheetName
            );
        }

        return getCellValue(cell);
    }


    // Read cell using Excel header name
    public String getCellData(
            String sheetName,
            int rowNumber,
            String columnName) {

        Sheet sheet = getSheet(sheetName);

        Row headerRow = sheet.getRow(0);

        if (headerRow == null) {
            throw new IllegalArgumentException(
                    "Header row does not exist in sheet: "
                            + sheetName
            );
        }

        int columnNumber = findColumnNumber(
                headerRow,
                columnName
        );

        if (columnNumber == -1) {
            throw new IllegalArgumentException(
                    "Column '" + columnName
                            + "' does not exist in sheet: "
                            + sheetName
            );
        }

        return getCellData(
                sheetName,
                rowNumber,
                columnNumber
        );
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


    private int findColumnNumber(
            Row headerRow,
            String columnName) {

        String expectedHeader =
                normalizeHeader(columnName);

        for (Cell cell : headerRow) {

            String actualHeader =
                    normalizeHeader(
                            getCellValue(cell)
                    );

            if (actualHeader.equals(expectedHeader)) {

                return cell.getColumnIndex();
            }
        }

        return -1;
    }


    private String normalizeHeader(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replaceAll("\\s+", "")
                .trim()
                .toLowerCase();
    }


    private Sheet getSheet(String sheetName) {

        Sheet sheet =
                workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new IllegalArgumentException(
                    "Sheet '" + sheetName
                            + "' does not exist in Excel workbook."
            );
        }

        return sheet;
    }


    private String getCellValue(Cell cell) {

        return dataFormatter.formatCellValue(cell);
    }


    @Override
    public void close() throws IOException {

        workbook.close();
    }
}