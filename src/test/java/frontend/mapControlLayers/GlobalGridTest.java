package frontend.mapControlLayers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static util.Constants.MAP_ZOOM_STANDART;

@ExtendWith(JunitExtension.class)
public class GlobalGridTest {

    DetailedShapePage detailedShowPage;
    private final int SHAPE_ID = 215;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlLayers/";
    private final String mapZoom = MAP_ZOOM_STANDART;

    @Test
    void checkGlobalGrid() {
        detailedShowPage = new DetailedShapePage().openPageWithAsideFalseAndMapWait(SHAPE_ID, mapZoom);
        detailedShowPage.selectDefaultSettings();

        detailedShowPage.mapControl.clickZoomOut(6);
        detailedShowPage.mapControl.getGlobalGridBtn().click();
        Util.checkScreenshotForElementWithWait(
                detailedShowPage.mapBlock.getMap(),
                "actGlobalGridOn",
                "expGlobalGridOn",
                "checkGlobalGridOn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        detailedShowPage.mapControl.getGlobalGridBtn().click();
        Util.checkScreenshotForElementWithWait(
                detailedShowPage.mapBlock.getMap(),
                "actGlobalGridOff",
                "expGlobalGridOff",
                "checkGlobalGridOff",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkGridLinesBtnTooltips() {
        detailedShowPage = new DetailedShapePage().openPageWithAsideTrueAndCoverageGridBarWait(SHAPE_ID);

        assertThat(detailedShowPage.mapControl.getGlobalGridBtn()).isEnabled();
        detailedShowPage.mapControl.getGlobalGridBtn().click();
        detailedShowPage.mapControl.checkGlobalGridHideTooltip("Hide global grid");


        detailedShowPage.mapControl.getGlobalGridBtn().click();
        detailedShowPage.mapControl.checkGlobalGridShowTooltip("Show global grid");
    }
}
