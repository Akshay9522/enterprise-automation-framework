package com.eaf.testdata.provider;

import com.eaf.config.ConfigReader;
import com.eaf.testdata.LoginData;
import com.eaf.utils.ExcelReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginDataProvider {

    @DataProvider(name = "invalidLoginData")
    public static Object[][] getInvalidLoginData() throws IOException {

        String filePath =
                ConfigReader.getProperty("test.data.file");

        List<LoginData> invalidLoginData =
                new ArrayList<>();

        try (ExcelReader excelReader =
                     new ExcelReader(filePath)) {

            int rowCount =
                    excelReader.getRowCount("Login");

            for (int row = 1; row <= rowCount; row++) {

                String testCase =
                        excelReader.getCellData(
                                "Login",
                                row,
                                "TestCase"
                        );

                String username =
                        excelReader.getCellData(
                                "Login",
                                row,
                                "Username"
                        );

                String password =
                        excelReader.getCellData(
                                "Login",
                                row,
                                "Password"
                        );

                String expectedResult =
                        excelReader.getCellData(
                                "Login",
                                row,
                                "ExpectedResult"
                        );

                if (expectedResult.equalsIgnoreCase("Failure")) {

                    invalidLoginData.add(
                            new LoginData(
                                    testCase,
                                    username,
                                    password,
                                    expectedResult
                            )
                    );
                }
            }
        }

        Object[][] data =
                new Object[invalidLoginData.size()][1];

        for (int i = 0;
             i < invalidLoginData.size();
             i++) {

            data[i][0] =
                    invalidLoginData.get(i);
        }

        return data;
    }
}