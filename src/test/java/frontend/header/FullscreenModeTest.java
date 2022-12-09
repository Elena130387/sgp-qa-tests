package frontend.header;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMainPage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ExtendWith(JunitExtension.class)
public class FullscreenModeTest {
    SgpMainPage sgpMainPage;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MainPageHeader/";

    @BeforeEach
    void openSgpMainPage() {
        sgpMainPage = new SgpMainPage().openMainPageWithHeaderWait();
    }

    /*  FIREFOX: When running a test with authorization through an account
    Microsoft (i.e without a file storage-state.json), the button FullScreenMod is not pressed,
    although the element locator is determined correctly, the test falls. When starting a test
    with an already generated file storage-state.json, the button FullScreenMod is pressed,
    the test does not fall.

    CHROME: When starting the test in the mode setHeadless(true), the button FullScreenMod is not
    pressed, the test falls. When starting the test in the mode setHeadless(false), the button
    FullScreenMod is pressed, the test does not fall. The presence or absence of the file
    storage-state.json does not matter when running the test in this browser.

    A task has been created to solve the problem:
    https://dev.azure.com/Syncretis/SmartGeoPlatform-Ecomonitoring/_workitems/edit/23939
    */

    /* In the browser Firefox, when running the test checkFullscreenMode(), the screenshot
   for the mode FullScreenModeOn does not fit on the screen. For this reason, for the browser
   Firefox, screen sizes are increased relative to standard sizes.
   */

    @Test
    void checkFullscreenMode() {
        assertThat(sgpMainPage.header.getFullScreenBtn()).isEnabled();
        sgpMainPage.header.getFullScreenBtn().click();
        Util.checkScreenshotForElement(
                sgpMainPage.header.getFullScreenBtn(),
                "actFullScreenModeOn",
                "expFullScreenModeOn",
                "checkFullScreenModeOn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        sgpMainPage.header.getFullScreenBtn().click();
        Util.checkScreenshotForElement(
                sgpMainPage.header.getFullScreenBtn(),
                "actFullScreenModeOff",
                "expFullScreenModeOff",
                "checkFullScreenModeOff",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkFullscreenModeOffByEsc() {
        // FullscreenMod On
        assertThat(sgpMainPage.header.getFullScreenBtn()).isEnabled();
        sgpMainPage.header.getFullScreenBtn().click();

        sgpMainPage.getPage().keyboard().press("Escape");
        sgpMainPage.header.getColorModeBtn().hover();
        Util.checkScreenshotForElement(
                sgpMainPage.header.getFullScreenBtn(),
                "actFullScreenModeOffEsc",
                "expFullScreenModeOff",
                "checkFullScreenModeOffEsc",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
