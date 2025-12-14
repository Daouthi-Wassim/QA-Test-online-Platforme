package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void initializeDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    public static WebDriverWait getWait() {
        if (wait == null && driver != null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return wait;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }

    public static String captureScreenshot(String scenarioName) {
        WebDriver currentDriver = getDriver();
        if (currentDriver == null) {
            System.err.println("Driver non initialisé, impossible de capturer le screenshot");
            return "";
        }

        try {
            File screenshotsDir = new File("target/screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }

            String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());

            String fileName = scenarioName.replaceAll("[^a-zA-Z0-9_-]", "_") + "_" + timeStamp + ".png";
            File screenshotFile = new File(screenshotsDir, fileName);

            TakesScreenshot ts = (TakesScreenshot) currentDriver;
            byte[] screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);

            FileUtils.writeByteArrayToFile(screenshotFile, screenshotBytes);

            String absolutePath = screenshotFile.getAbsolutePath();
            System.out.println("Screenshot enregistré: " + absolutePath);

            return absolutePath;

        } catch (IOException e) {
            System.err.println("Erreur lors de la capture d'écran: " + e.getMessage());
            return "";
        }
    }




    @BeforeEach
    void setupTest(){
        initializeDriver();
    }

    @AfterEach
    void teardownTest(){
        closeDriver();
    }
}