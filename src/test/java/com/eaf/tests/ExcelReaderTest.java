package com.eaf.tests;

import com.eaf.config.ConfigReader;
import com.eaf.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ExcelReaderTest {

    @Test
    public void verifyExcelReader() throws IOException {

        String filePath =
                ConfigReader.getProperty("test.data.file");

        try (ExcelReader excelReader = new ExcelReader(filePath)) {

            String username =
                    excelReader.getCellData("Login", 1, 1);

            Assert.assertEquals(
                    username,
                    "Admin",
                    "Username does not match expected value"
            );
        }
    }
}