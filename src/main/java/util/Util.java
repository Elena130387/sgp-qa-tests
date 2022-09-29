package util;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.microsoft.playwright.Locator;
import io.github.cdimascio.dotenv.Dotenv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static util.Constants.EXPECTED_SCREENSHOTS_DIR;
import static util.Constants.FAILURE_SCREENSHOTS_DIR;
import static util.JunitExtension.doScreenshotFor;
import static util.JunitExtension.page;

public class Util {
    public static String getTimestampNowAsString() {
        var now = LocalDateTime.now();
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return now.format(formatter);
    }

    public static String getVariable(String variableName) {
        String systemVariable = System.getenv(variableName);
        if (systemVariable != null) {
            return systemVariable;
        } else {
            return Dotenv.configure().load().get(variableName);
        }
    }

    private static void imageComparison(Path imgNow, Path imgExpect, String testName) throws IOException {
        BufferedImage expected = ImageComparisonUtil.readImageFromResources(String.valueOf(imgExpect));
        BufferedImage actual = ImageComparisonUtil.readImageFromResources(String.valueOf(imgNow));

        File diffFile = new File(FAILURE_SCREENSHOTS_DIR + "image_comparison_" + testName + Util.getTimestampNowAsString() + ".png");
        ImageComparison comparison = new ImageComparison(expected, actual, diffFile);
        ImageComparisonResult result = comparison.compareImages();

        Files.delete(imgNow);
        float diffPercentage = result.getDifferencePercent();
        if (diffPercentage > 0.01) {
            BufferedImage resultImage = result.getResult();
            ImageComparisonUtil.saveImage(diffFile, resultImage);

            fail("Comparison failed, difference is " + diffPercentage);
        }
    }

    public static void checkScreenshot(String actual, String expected, String testName) throws IOException {
        page.waitForTimeout(4000);
        Path screenshot = doScreenshotFor(actual);
        Path expectedScreenshot = Paths.get(EXPECTED_SCREENSHOTS_DIR + expected + ".png");
        imageComparison(screenshot, expectedScreenshot, testName);
    }

    public static void assertTooltip(Locator element, String text) {
        element.hover();
        String tooltipLocator = element.getAttribute("aria-describedby");
        Locator tooltipItself = element.page().locator(String.format("#%s", tooltipLocator));
        assertEquals(text, tooltipItself.textContent());
    }
}
