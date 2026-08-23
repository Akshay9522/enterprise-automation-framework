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
                        excelReader.getCellData(
                                "Employee",
                                row,
                                "TestCase"
                        );

                String firstName =
                        excelReader.getCellData(
                                "Employee",
                                row,
                                "FirstName"
                        );

                String middleName =
                        excelReader.getCellData(
                                "Employee",
                                row,
                                "MiddleName"
                        );

                String lastName =
                        excelReader.getCellData(
                                "Employee",
                                row,
                                "LastName"
                        );

                String employeeId =
                        excelReader.getCellData(
                                "Employee",
                                row,
                                "EmployeeId"
                        );

                String expectedResult =
                        excelReader.getCellData(
                                "Employee",
                                row,
                                "ExpectedResult"
                        );

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