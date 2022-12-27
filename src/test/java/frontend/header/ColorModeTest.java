package frontend.header;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMainPage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ExtendWith(JunitExtension.class)
public class ColorModeTest {
    SgpMainPage sgpMainPage;
    private final String EXP_SCREENSHOTS_TEST_CLASS_DIR = "MainPageHeader/";
    private final String COLOR_MODE_ON_TOOLTIP = "Цветовой режим";
    private final String COLOR_MODE_OFF_TOOLTIP = "Цветовой режим";

    @BeforeEach
    void openSgpMainPage() {
        sgpMainPage = new SgpMainPage().openMainPageWithDefaultSettings();
    }

    @Test
    void checkColorMode() {
        //close the list of created shapes if it is necessary
        if (sgpMainPage.shapesPanel.getShapesPanelVisible().isVisible()) {
            sgpMainPage.mapControl.getShapesPanelHideShowBtn().click();
        }

        assertThat(sgpMainPage.header.getColorModeBtn()).isEnabled();
        sgpMainPage.header.getColorModeBtn().click();

        //      Remove focus from the button so that the tooltip is not displayed
        sgpMainPage.mapControl.getGeoSearch().click();
        Util.checkScreenshotWithWait(
                "actColorModeOn",
                "expColorModeOn",
                "checkColorModeOn",
                EXP_SCREENSHOTS_TEST_CLASS_DIR);

        sgpMainPage.header.getColorModeBtn().click();
        //      Remove focus from the button so that the tooltip is not displayed
        sgpMainPage.mapControl.getGeoSearch().click();
        Util.checkScreenshotWithWait(
                "actColorModeOff",
                "expColorModeOff",
                "checkColorModeOff",
                EXP_SCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkColorModeTooltips() {
        assertThat(sgpMainPage.header.getColorModeBtn()).isEnabled();
        sgpMainPage.header.getColorModeBtn().click();
        sgpMainPage.header.checkColorModeTooltip(COLOR_MODE_ON_TOOLTIP);

        sgpMainPage.header.getColorModeBtn().click();
        sgpMainPage.header.checkColorModeTooltip(COLOR_MODE_OFF_TOOLTIP);
    }
}
