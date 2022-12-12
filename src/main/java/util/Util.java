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
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static util.Constants.EXPECTED_SCREENSHOTS_DIR;
import static util.Constants.FAILURE_SCREENSHOTS_DIR;
import static util.JunitExtension.*;

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

    public static boolean timeoutIsReached(long start, int timeoutInSeconds, int durationInSeconds) {
        return (System.currentTimeMillis() - start) / TimeUnit.SECONDS.toMillis(durationInSeconds) > timeoutInSeconds;
    }

    private static void imageComparison(Path imgNow, Path imgExpect, String testName) {
        BufferedImage expected = ImageComparisonUtil.readImageFromResources(String.valueOf(imgExpect));
        BufferedImage actual = ImageComparisonUtil.readImageFromResources(String.valueOf(imgNow));

        File diffFile = new File(FAILURE_SCREENSHOTS_DIR + BROWSER + "/" + "image_comparison_" + testName + Util.getTimestampNowAsString() + ".png");
        ImageComparison comparison = new ImageComparison(expected, actual, diffFile);
        ImageComparisonResult result = comparison.compareImages();

        float diffPercentage = result.getDifferencePercent();
        try {
            Files.delete(imgNow);
        } catch (IOException exception) {
            System.out.println("Не удалось удалить файла  " + imgNow);
        }

        if (diffPercentage > 0.5) {
            BufferedImage resultImage = result.getResult();
            ImageComparisonUtil.saveImage(diffFile, resultImage);

            fail("Comparison failed, difference is " + diffPercentage);
        }
    }

    public static void checkScreenshotWithWait(String actual, String expected, String testName, String expDir) {
        page.waitForTimeout(3000);
        Path screenshot = doScreenshotFor(actual);
        Path expectedScreenshot = Paths.get(EXPECTED_SCREENSHOTS_DIR + BROWSER + "/" + expDir + expected + ".png");
        imageComparison(screenshot, expectedScreenshot, testName);
    }

    public static void checkScreenshotForElement(Locator element, String actual, String expected, String testName, String expDir) {
        Path screenshot = doScreenshotForElement(element, actual);
        Path expectedScreenshot = Paths.get(EXPECTED_SCREENSHOTS_DIR + BROWSER + "/" + expDir + expected + ".png");
        imageComparison(screenshot, expectedScreenshot, testName);
    }

    public static void checkScreenshotForElementWithWait(Locator element, String actual, String expected, String testName, String expDir) {
        page.waitForTimeout(3500);
        Path screenshot = doScreenshotForElement(element, actual);
        Path expectedScreenshot = Paths.get(EXPECTED_SCREENSHOTS_DIR + BROWSER + "/" + expDir + expected + ".png");
        imageComparison(screenshot, expectedScreenshot, testName);
    }

    /*    For the map service Mapbox, there was an attempt to replace a simple wait with waiting for the
     end of the request, but during the tests run, the system does not see this request, although when
     you click F12 on the UI, the request is displayed in the spool. Therefore, we decided to leave
     the method with a simple wait.
     If you need to return to checking for the completion of loading the request, then instead of the
     usual waiting, you need to write:

     Predicate<Request> getImg = request ->request.url().contains("sprite@2x.png");
          Request request1 = page.waitForRequest(
                getImg,
                 () -> {});
     */
    public static void checkScreenshotForElementWithLongWait(Locator element, String actual, String expected, String testName, String expDir) {
        page.waitForTimeout(5000);
        Path screenshot = doScreenshotForElement(element, actual);
        Path expectedScreenshot = Paths.get(EXPECTED_SCREENSHOTS_DIR + BROWSER + "/" + expDir + expected + ".png");
        imageComparison(screenshot, expectedScreenshot, testName);
    }

    public static void assertTooltipInAtrTitle(Locator element, String text) {
        element.hover();
        String tooltipLocator = element.getAttribute("title");
        Locator tooltipItself = element.page().locator(String.format("#%s", tooltipLocator));
        assertEquals(text, tooltipItself.textContent(), "Неверный тултип");
    }

    public static void assertTooltip(Locator element, Locator tooltip, String expText) {
        element.hover();
        String tooltipText = tooltip.textContent();
        assertEquals(expText, tooltipText);
    }
}
