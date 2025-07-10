package test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import io.cucumber.java.en.*;
import utils.ScreenshotUtil;

import java.time.Duration;
import java.util.List;

public class IndexSteps {

    public static WebDriver driver;
    WebDriverWait wait;

    @Given("I launch the e-commerce site")
    public void launchSite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://orbit-automation-test-site.s3-website-us-east-1.amazonaws.com/index.html");
    }

    @When("I navigate to {string}")
    public void navigateTo(String page) {
        driver.get("http://orbit-automation-test-site.s3-website-us-east-1.amazonaws.com/" + page);
    }

    @Then("The page title should be {string}")
    public void validateTitle(String expectedTitle) {
        try {
            String actualTitle = driver.getTitle();
            if (!actualTitle.contains(expectedTitle)) {
                ScreenshotUtil.takeScreenshot(driver, "TitleMismatch");
                Assert.fail("❌ Title mismatch: Expected to contain '" + expectedTitle + "', but got '" + actualTitle + "'");
            }
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, "TitleError");
            Assert.fail("❌ Exception while validating title: " + e.getMessage());
        }
    }

    @Then("The {string} button should be visible")
    public void verifyButtonVisible(String buttonText) {
        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='" + buttonText + "']")));
            scrollToElement(button);
            Assert.assertTrue(button.isDisplayed(), "❌ Button '" + buttonText + "' is not visible!");
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, "Button_" + buttonText.replace(" ", "_") + "_Error");
            Assert.fail("❌ Exception while checking button '" + buttonText + "': " + e.getMessage());
        }
    }

    @Then("The first product title should be {string}")
    public void verifyFirstProductTitle(String expectedTitle) {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pro .des h5")));
            scrollToElement(title);
            String actual = title.getText().trim();
            if (!actual.equals(expectedTitle)) {
                ScreenshotUtil.takeScreenshot(driver, "ProductTitleMismatch");
                Assert.fail("❌ Product title mismatch: Expected '" + expectedTitle + "', but got '" + actual + "'");
            }
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, "ProductTitleError");
            Assert.fail("❌ Exception in verifying product title: " + e.getMessage());
        }
    }

    @Then("There should be at least {int} products listed")
    public void verifyProductCount(int expectedCount) {
        try {
            List<WebElement> products = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".pro"), expectedCount - 1));
            if (!products.isEmpty()) scrollToElement(products.get(0));
            Assert.assertTrue(products.size() >= expectedCount,
                    "❌ Expected at least " + expectedCount + " products, but found " + products.size());
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, "ProductCountError");
            Assert.fail("❌ Exception in verifying product count: " + e.getMessage());
        }
    }

    @Then("The section with title {string} should be visible")
    public void validateSectionVisible(String sectionTitle) {
        try {
            WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + sectionTitle + "']")));
            scrollToElement(section);
            Assert.assertTrue(section.isDisplayed(), "❌ Section with title '" + sectionTitle + "' is not visible");
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, "Section_" + sectionTitle.replace(" ", "_") + "_Error");
            Assert.fail("❌ Section '" + sectionTitle + "' check failed: " + e.getMessage());
        }
    }

    @Then("The first product should display the price {string}")
    public void validateFirstProductPrice(String expectedPrice) {
        try {
            WebElement price = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pro .des h4")));
            scrollToElement(price);
            String actualPrice = price.getText().trim();
            if (!actualPrice.equals(expectedPrice)) {
                ScreenshotUtil.takeScreenshot(driver, "PriceMismatch");
                System.out.println("❌ Price mismatch: Expected '" + expectedPrice + "', Found '" + actualPrice + "'");
                Assert.fail("❌ Product price doesn't match. Expected: " + expectedPrice + ", Found: " + actualPrice);
            }
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, "PriceCheckError");
            Assert.fail("❌ Exception while validating price: " + e.getMessage());
        }
    }

    @Then("The footer should contain {string}")
    public void validateFooterContent(String expectedText) {
        try {
            WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("footer")));
            scrollToElement(footer);
            Assert.assertTrue(footer.getText().contains(expectedText), "❌ Footer does not contain: " + expectedText);
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, "FooterError");
            Assert.fail("❌ Exception while verifying footer content: " + e.getMessage());
        }
    }

    // 🔁 Scroll element to center
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'instant', block:'center'});", element);
    }
}
