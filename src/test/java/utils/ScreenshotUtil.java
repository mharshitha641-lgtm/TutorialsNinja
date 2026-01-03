package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    /**
     * Takes a screenshot of the current browser window.
     *
     * @param driver   The WebDriver instance
     * @param testName The test method name (used for filename)
     * @return The path of the saved screenshot
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            System.out.println("Driver is null. Cannot take screenshot.");
            return null;
        }

        // Create folder path
        String dir = System.getProperty("user.dir") + "/screenshots/";
        File destDir = new File(dir);

        // Create folder if it doesn't exist
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        // Create timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        // File name: testName + timestamp
        String fileName = testName + "_" + timestamp + ".png";

        // Full file path
        String path = dir + fileName;

        // Take screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(path);
        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved at: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}
