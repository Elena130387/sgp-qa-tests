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
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MainPageHeader/";
    SgpMainPage sgpMainPage;

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

    @Test
    void checkColorModeTooltips() {
        assertThat(sgpMainPage.header.getColorModeBtn()).isEnabled();
        sgpMainPage.header.getColorModeBtn().click();
        sgpMainPage.header.checkColorModeTooltip("Цветовой режим");

        sgpMainPage.header.getColorModeBtn().click();
        sgpMainPage.header.checkColorModeTooltip("Цветовой режим");
    }
}
