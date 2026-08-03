package com.eaf.tests;

import com.eaf.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void verifyApplicationLaunch() {

        Assert.assertTrue(
                driver.getTitle().length() > 0);

    }
}