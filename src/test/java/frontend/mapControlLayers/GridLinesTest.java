package frontend.mapControlLayers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ExtendWith(JunitExtension.class)
public class GridLinesTest {
    private final int SHAPE_ID = 611;
    DetailedShapePage detailedShowPage;
    private final String EXP_SCREENSHOTS_TEST_CLASS_DIR = "MapControlLayers/";
    private final String GRID_LINES_SHOW_TOOLTIP = "Show grid lines";
    private final String GRID_LINES_HIDE_TOOLTIP = "Hide grid lines";

    @BeforeEach
    void openSgpMainPage() {
        detailedShowPage = new DetailedShapePage().openPageWithEconomicTabAndCoverageGridBarWait(SHAPE_ID);
    }

    @Test
    void checkGridLines() {
        detailedShowPage.selectDefaultSettings();

        detailedShowPage.mapControl.getGridLinesBtn().click();

        //      Remove focus from the button so that the tooltip is not displayed
        detailedShowPage.mapControl.getGeoSearch().click();
        Util.checkScreenshotForElementWithWait(
                detailedShowPage.mapBlock.getMap(),
                "actGridLinesOff",
                "expGridLinesOff",
                "checkGridLinesOff",
                EXP_SCREENSHOTS_TEST_CLASS_DIR);

        detailedShowPage.mapControl.getGridLinesBtn().click();

        //      Remove focus from the button so that the tooltip is not displayed
        detailedShowPage.mapControl.getGeoSearch().click();
        Util.checkScreenshotForElementWithWait(
                detailedShowPage.mapBlock.getMap(),
                "actGridLinesOn",
                "expGridLinesOn",
                "checkGridLinesOn",
                EXP_SCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkGridLinesBtnTooltips() {
        assertThat(detailedShowPage.mapControl.getGridLinesBtn()).isEnabled();
        detailedShowPage.mapControl.getGridLinesBtn().click();
        detailedShowPage.mapControl.checkGridLinesShowTooltip(GRID_LINES_SHOW_TOOLTIP);

        detailedShowPage.mapControl.getGridLinesBtn().click();
        detailedShowPage.mapControl.checkGridLinesHideTooltip(GRID_LINES_HIDE_TOOLTIP);
    }
}
