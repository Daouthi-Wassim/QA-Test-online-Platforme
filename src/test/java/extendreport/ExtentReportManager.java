package extendreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportPath;

    public static ExtentReports createInstance() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        reportPath = System.getProperty("user.dir") + "target/extent-report";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Test Execution Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Tester", "Automation Team");
        extent.setSystemInfo("Environment", "QA");

        return extent;
    }

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    public static ExtentTest createTest(String testName, String description) {
        test = extent.createTest(testName, description);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
            System.out.println("Report generated at: " + reportPath);
        }
    }
}