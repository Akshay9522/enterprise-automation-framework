package com.eaf.testdata.provider;

import com.eaf.config.ConfigReader;
import com.eaf.testdata.EmployeeData;
import com.eaf.utils.ExcelReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class EmployeeDataProvider {

    @DataProvider(name = "employeeData")
    public static Object[][] getEmployeeData() throws IOException {

        String filePath =
                ConfigReader.getProperty("test.data.file");

        try (ExcelReader excelReader = new ExcelReader(filePath)) {

            int rowCount =
                    excelReader.getRowCount("Employee");

            Object[][] data =
                    new Object[rowCount][1];

            for (int row = 1; row <= rowCount; row++) {

                String testCase =
                        excelReader.getCellData("Employee", row, 0);

                String firstName =
                        excelReader.getCellData("Employee", row, 1);

                String middleName =
                        excelReader.getCellData("Employee", row, 2);

                String lastName =
                        excelReader.getCellData("Employee", row, 3);

                String employeeId =
                        excelReader.getCellData("Employee", row, 4);

                String expectedResult =
                        excelReader.getCellData("Employee", row, 5);

                data[row - 1][0] =
                        new EmployeeData(
                                testCase,
                                firstName,
                                middleName,
                                lastName,
                                employeeId,
                                expectedResult
                        );
            }

            return data;
        }
    }
}