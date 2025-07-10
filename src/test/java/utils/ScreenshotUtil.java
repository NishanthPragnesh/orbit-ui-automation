package utils;

import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String name) {
        try {
            // Scroll to top before capturing full screen
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            // Format timestamp for filename
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filename = "screenshot_" + name.replaceAll("\\s+", "_") + "_" + timestamp + ".png";

            // Save to target/screenshots/
            File targetDir = new File("target/screenshots");
            if (!targetDir.exists()) targetDir.mkdirs();

            Files.copy(src.toPath(), new File(targetDir, filename).toPath());
            System.out.println("📸 Screenshot saved at: " + new File(targetDir, filename).getAbsolutePath());

        } catch (IOException e) {
            System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
        }
    }
}
