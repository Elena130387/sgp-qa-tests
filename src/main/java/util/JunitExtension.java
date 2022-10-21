package util;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;
import static util.Constants.FAILURE_SCREENSHOTS_DIR;
import static util.Constants.STORAGE_PATH;

public class JunitExtension implements BeforeAllCallback, AfterEachCallback, AfterAllCallback {
    public static Playwright playwright;
    public static Browser browser;

    public static BrowserContext browserContext;
    public static Page page;

    public static final Logger LOG = LoggerFactory.getLogger("[TEST INFO]");

    public static String BROWSER = Util.getVariable("BROWSER");

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        playwright = Playwright.create();
        if (!Objects.isNull(BROWSER) && BROWSER.equals("CHROME")) {
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(true)
                            .setSlowMo(100)
            );
        } else {
            BROWSER = "FIREFOX";
            browser = playwright.firefox().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(true)
                            .setSlowMo(100)
            );
        }

        if (!Files.exists(STORAGE_PATH)) {
            try {
                Files.createFile(STORAGE_PATH);
            } catch (IOException e) {
                // no problem
            }
        }

        browserContext = browser.newContext(
                    new Browser.NewContextOptions().setStorageStatePath(STORAGE_PATH)); // View size 1280X720
            page = browserContext.newPage();

        try {
            MsHelper.login();
        } catch (TimeoutError error) {
            doScreenshotFor("BEFORE_ALL");
            fail(error.getMessage());
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        context.getExecutionException().ifPresent(e -> {
            Path path = doScreenshotFor(context.getRequiredTestMethod().getName());
        });

//        page.waitForTimeout(10000);
    }

    public static Path doScreenshotFor(String methodName) {
        String localPath = FAILURE_SCREENSHOTS_DIR + BROWSER + "/";
        Path SCREENSHOTS_PATH = Paths.get(localPath + methodName
                + "_" + Util.getTimestampNowAsString() + ".png");
        Page.ScreenshotOptions screenshotPath = new Page.ScreenshotOptions().setPath(SCREENSHOTS_PATH);
        page.screenshot(screenshotPath);
        return SCREENSHOTS_PATH;
    }

    @Override
    public void afterAll(ExtensionContext context) {
        browserContext.close();
        browser.close();
    }
}
