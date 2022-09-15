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
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;
import static util.Constants.STORAGE_PATH;

public class JunitExtension implements BeforeAllCallback, AfterEachCallback, AfterAllCallback {
    public static Playwright playwright;
    public static Browser browser;

    public static BrowserContext browserContext;
    public static Page page;

    public static final Logger LOG = LoggerFactory.getLogger("[TEST INFO]");

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        playwright = Playwright.create();
        browser = playwright.firefox().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(100)
        );

        if(!Files.exists(STORAGE_PATH)){
            try {
                Files.createFile(STORAGE_PATH);
            } catch (IOException e){
                // no problem
            }
        }
        browserContext = browser.newContext(
                new Browser.NewContextOptions().setStorageStatePath(STORAGE_PATH));
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
            doScreenshotFor(context.getRequiredTestMethod().getName());
        });

//        page.waitForTimeout(10000);
    }

    private void doScreenshotFor(String methodName) {
        var localPath = "C:\\Users\\ElenaShapoval\\IdeaProjects\\sgp-qa-tests\\screenshots\\";
        var SCREENSHOTS_PATH = Paths.get(localPath + methodName
                + "_" + Util.getTimestampNowAsString() + ".png");
        var screenshotPath = new Page.ScreenshotOptions().setPath(SCREENSHOTS_PATH);
        page.screenshot(screenshotPath);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {}
}
