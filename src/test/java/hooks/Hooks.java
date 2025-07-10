package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import test.IndexSteps;
import utils.ScreenshotUtil;

public class Hooks {

    @AfterStep
    public void takeScreenshotAfterStep(Scenario scenario) {
        WebDriver driver = IndexSteps.driver;

        if (scenario.isFailed() && driver != null) {
            ScreenshotUtil.takeScreenshot(driver, scenario.getName());
            System.out.println("📸 Screenshot captured for: " + scenario.getName());
        }
    }

    @After
    public void tearDown() {
        WebDriver driver = IndexSteps.driver;
        if (driver != null) {
            driver.quit();
        }
    }
}
