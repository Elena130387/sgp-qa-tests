package frontend.header;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMainPage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ExtendWith(JunitExtension.class)
public class ColorModeTest {
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MainPageHeaderTest/";
    SgpMainPage sgpMainPage;

    @Test
    void checkColorMode() {
        sgpMainPage = new SgpMainPage().openMainPageWithDefaultSettings();

        //close the list of created shapes if it is necessary
        if (sgpMainPage.shapesPanel.getShapesPanelVisible().isVisible()) {
            sgpMainPage.mapControl.getPolygonsSectionBtn().click();
        }

        assertThat(sgpMainPage.header.getColorModeBtn()).isEnabled();
        sgpMainPage.header.getColorModeBtn().click();
        Util.checkScreenshotWithWait(
                "actColorModeOn",
                "expColorModeOn",
                "checkColorModeOn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        sgpMainPage.header.getColorModeBtn().click();
        Util.checkScreenshotWithWait(
                "actColorModeOff",
                "expColorModeOff",
                "checkColorModeOff",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
