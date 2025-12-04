package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("=== STARTING ALL TESTS ===");
        System.out.println("Timestamp: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("\n=== STARTING SCENARIO: " + scenario.getName() + " ===");
        System.out.println("Tags: " + scenario.getSourceTagNames());
        BaseTest.setup();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        // Prendre une capture d'écran à chaque étape (optionnel)
        try {
            byte[] screenshot = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Step Screenshot");
        } catch (Exception e) {
            System.out.println("Could not take step screenshot: " + e.getMessage());
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("=== SCENARIO RESULT: " + scenario.getStatus() + " ===");

        if (scenario.isFailed()) {
            System.out.println("FAILED: " + scenario.getName());
            try {
                // Capture d'écran en cas d'échec
                TakesScreenshot ts = (TakesScreenshot) BaseTest.getDriver();
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

                // Attacher au rapport Cucumber
                scenario.attach(screenshot, "image/png", "Failed Screenshot");

                // Sauvegarder localement
                String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
                String filename = "target/screenshots/failed_" + scenario.getName().replace(" ", "_") + "_" + timestamp + ".png";
                Files.write(Paths.get(filename), screenshot);
                System.out.println("Screenshot saved: " + filename);
            } catch (Exception e) {
                System.out.println("Failed to save screenshot: " + e.getMessage());
            }
        }

        BaseTest.tearDown();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("\n=== ALL TESTS COMPLETED ===");
        System.out.println("Timestamp: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}