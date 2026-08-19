package com.eaf.testdata.provider;

import com.eaf.config.ConfigReader;
import com.eaf.testdata.LoginData;
import com.eaf.utils.ExcelReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() throws IOException {

        String filePath =
                ConfigReader.getProperty("test.data.file");

        try (ExcelReader excelReader = new ExcelReader(filePath)) {

            int rowCount =
                    excelReader.getRowCount("Login");

            Object[][] data =
                    new Object[rowCount][1];

            for (int row = 1; row <= rowCount; row++) {

                String testCase =
                        excelReader.getCellData("Login", row, 0);

                String username =
                        excelReader.getCellData("Login", row, 1);

                String password =
                        excelReader.getCellData("Login", row, 2);

                String expectedResult =
                        excelReader.getCellData("Login", row, 3);

                data[row - 1][0] =
                        new LoginData(
                                testCase,
                                username,
                                password,
                                expectedResult
                        );
            }

            return data;
        }
    }
}