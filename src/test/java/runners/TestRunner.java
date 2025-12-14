package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
                "src/test/resources/features/01_Signup.feature",
                "src/test/resources/features/02_Cart.feature",
                "src/test/resources/features/03_Products.feature",
                "src/test/resources/features/04_Payment.feature",
                "src/test/resources/features/05_Contact.feature",
                "src/test/resources/features/06_Logout.feature",
                "src/test/resources/features/07_DeleteAccount.feature"
}, glue = { "stepdefinitions" }, plugin = {
                "pretty",
                "html: target/extent-report/report.html"
}, monochrome = true, dryRun = false)
public class TestRunner {
}