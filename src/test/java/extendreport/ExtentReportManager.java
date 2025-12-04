package extendreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import base.BaseTest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-reports/extent-report.html");
        sparkReporter.config().setDocumentTitle("Automation Exercise Test Report");
        sparkReporter.config().setReportName("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Automation Exercise");
        extent.setSystemInfo("Tester", "QA Team");
    }

    public static void createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    public static void logInfo(String message) {
        test.get().info(message);
    }

    public static void logPass(String message) {
        test.get().pass(message);
    }

    public static void logFail(String message) {
        test.get().fail(message);
    }

    public static void addScreenshot() {
        if (BaseTest.getDriver() != null) {
            String screenshotPath = captureScreenshot();
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    }

    private static String captureScreenshot() {
        try {
            // Utilisez BaseTest.getDriver() ici aussi
            File screenshot = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.FILE);
            String path = "target/screenshots/" + System.currentTimeMillis() + ".png";
            Files.createDirectories(Paths.get("target/screenshots/"));
            Files.copy(screenshot.toPath(), Paths.get(path));
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}