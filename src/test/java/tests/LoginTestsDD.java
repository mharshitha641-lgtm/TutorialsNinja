package tests;

import base.Basetest;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.ExcelUtils;

import java.io.IOException;

public class LoginTestsDD extends Basetest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setupTest() {
        loginPage = new LoginPage(driver);
        driver.manage().deleteAllCookies();
        loginPage.goToLoginPage();
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {
        ExcelUtils.setExcelFile("src/test/resources/TestData.xlsx", "Sheet1");
        int rows = ExcelUtils.getRowCount();
        int cols = ExcelUtils.getColumnCount();
        Object[][] data = new Object[rows - 1][cols]; // skip header
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = ExcelUtils.getCellData(i, j);
            }
        }
        return data;
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String testCase, String username, String password) {
        loginPage.login(username, password);

        String warning = loginPage.getWarningMessage();
        if (!warning.isEmpty()) {
            System.out.println(testCase + " warning: " + warning);
        }
    }
}
