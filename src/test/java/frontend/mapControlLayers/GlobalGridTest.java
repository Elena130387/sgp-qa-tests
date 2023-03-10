package frontend.mapControlLayers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static util.Constants.MAP_ZOOM_STANDARD;

@ExtendWith(JunitExtension.class)
public class GlobalGridTest {

    DetailedShapePage detailedShowPage;
    private final int SHAPE_ID = 611;
    private final String EXP_SCREENSHOTS_TEST_CLASS_DIR = "MapControlLayers/";
    private final String mapZoom = MAP_ZOOM_STANDARD;
    private final String GLOBAL_GRID_LINES_SHOW_TOOLTIP = "Show global grid";
    private final String GLOBAL_GRID_LINES_HIDE_TOOLTIP = "Hide global grid";

    @Test
    void checkGlobalGrid() {
        detailedShowPage = new DetailedShapePage().openPageWithAsideFalseAndMapWait(SHAPE_ID, mapZoom);
        detailedShowPage.selectDefaultSettings();

        detailedShowPage.mapControl.clickZoomOut(6);
        detailedShowPage.mapControl.getGlobalGridBtn().click();

        //      Remove focus from the button so that the tooltip is not displayed
        detailedShowPage.mapControl.getGeoSearch().click();
        Util.checkScreenshotForElementWithWait(
                detailedShowPage.mapBlock.getMap(),
                "actGlobalGridOn",
                "expGlobalGridOn",
                "checkGlobalGridOn",
                EXP_SCREENSHOTS_TEST_CLASS_DIR);

        detailedShowPage.mapControl.getGlobalGridBtn().click();

        //      Remove focus from the button so that the tooltip is not displayed
        detailedShowPage.mapControl.getGeoSearch().click();
        Util.checkScreenshotForElementWithWait(
                detailedShowPage.mapBlock.getMap(),
                "actGlobalGridOff",
                "expGlobalGridOff",
                "checkGlobalGridOff",
                EXP_SCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkGridLinesBtnTooltips() {
        detailedShowPage = new DetailedShapePage().openPageWithAsideTrueAndCoverageGridBarWait(SHAPE_ID);

        assertThat(detailedShowPage.mapControl.getGlobalGridBtn()).isEnabled();
        detailedShowPage.mapControl.getGlobalGridBtn().click();
        detailedShowPage.mapControl.checkGlobalGridHideTooltip(GLOBAL_GRID_LINES_HIDE_TOOLTIP);


        detailedShowPage.mapControl.getGlobalGridBtn().click();
        detailedShowPage.mapControl.checkGlobalGridShowTooltip(GLOBAL_GRID_LINES_SHOW_TOOLTIP);
    }
}
