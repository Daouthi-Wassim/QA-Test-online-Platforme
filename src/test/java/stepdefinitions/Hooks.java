package stepdefinitions;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import extendreport.ExtentReportManager;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks extends BaseTest {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @BeforeAll
    public static void beforeAll() {
        extent = ExtentReportManager.createInstance();
        System.out.println("=".repeat(80));
        System.out.println("STARTING TEST EXECUTION - All Scenarios (Pass & Fail)");
        System.out.println("=".repeat(80));
    }

    @AfterAll
    public static void afterAll() {
        if (extent != null) {
            extent.flush();
            System.out.println("=".repeat(80));
            System.out.println("TEST EXECUTION COMPLETED");
            System.out.println("=".repeat(80));
        }
    }

    @Before
    public void setUp(Scenario scenario) {
        initializeDriver();
        ExtentTest test = extent.createTest(scenario.getName());
        extentTest.set(test);
        if (!scenario.getSourceTagNames().isEmpty()) {
            test.assignCategory(scenario.getSourceTagNames().toArray(new String[0]));
        }

        test.info("Starting scenario: " + scenario.getName());
        System.out.println("\n" + "=".repeat(80));
        System.out.println("▶ Starting: " + scenario.getName());
        System.out.println("Tags: " + scenario.getSourceTagNames());
        System.out.println("=".repeat(80));
    }

    @After
    public void tearDown(Scenario scenario) {
        ExtentTest test = extentTest.get();

        if (scenario.isFailed()) {
            test.log(Status.FAIL, MarkupHelper.createLabel("SCENARIO FAILED: " + scenario.getName(), ExtentColor.RED));
            System.out.println(" Test FAILED: " + scenario.getName());

            if (getDriver() != null) {
                try {
                    String screenshotPath = takeScreenshot(scenario.getName());
                    System.out.println("Screenshot saved at: " + screenshotPath);

                    byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", scenario.getName());
                    test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
                } catch (Exception e) {
                    System.err.println("Failed to capture screenshot: " + e.getMessage());
                    test.warning("Failed to capture screenshot: " + e.getMessage());
                }
            }
        } else {
            test.log(Status.PASS,
                    MarkupHelper.createLabel("SCENARIO PASSED: " + scenario.getName(), ExtentColor.GREEN));
            System.out.println(" Test PASSED: " + scenario.getName());
            if (getDriver() != null) {
                try {
                    String screenshotPath = takeScreenshot(scenario.getName());
                    test.addScreenCaptureFromPath(screenshotPath, "Success Screenshot");
                } catch (Exception e) {
                    test.warning("Failed to capture screenshot: " + e.getMessage());
                }
            }
        }

        System.out.println("=".repeat(80) + "\n");
        closeDriver();
        extentTest.remove();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        ExtentTest test = extentTest.get();
        if (test != null && scenario.isFailed()) {
            test.log(Status.WARNING, "Step failed in scenario: " + scenario.getName());
        }
    }

    private String takeScreenshot(String scenarioName) {
        if (getDriver() == null) {
            System.err.println("Driver is null, cannot capture screenshot");
            return "";
        }

        try {
            File screenshotsDir = new File("target/screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }

            String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            String fileName = sanitizeFileName(scenarioName) + "_" + timeStamp + ".png";
            File screenshotFile = new File(screenshotsDir, fileName);

            TakesScreenshot ts = (TakesScreenshot) getDriver();
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, screenshotFile);

            String absolutePath = screenshotFile.getAbsolutePath();
            System.out.println("Screenshot enregistré: " + absolutePath);

            return absolutePath;

        } catch (IOException e) {
            System.err.println("Erreur lors de la capture d'écran: " + e.getMessage());
            return "";
        }
    }

    private String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9_-]", "_");
    }
}
