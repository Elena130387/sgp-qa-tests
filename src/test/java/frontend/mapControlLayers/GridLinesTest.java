package frontend.mapControlLayers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;
import util.Util;

@ExtendWith(JunitExtension.class)
public class GridLinesTest {
    private final int SHAPE_ID = 611;
    DetailedShapePage detailedShowPage;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlLayersTest/";

    @Test
    void checkGridLines() {
        detailedShowPage = new DetailedShapePage().openPageWithEconomicTabAndGridLinesBtnWait(SHAPE_ID);
        detailedShowPage.selectDefaultSettings();

        detailedShowPage.mapControl.getGridLinesBtn().click();
        Util.checkScreenshotForElementWithWait(
                detailedShowPage.mapBlock.getMap(),
                "actGridLinesOff",
                "expGridLinesOff",
                "checkGridLinesOff",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        detailedShowPage.mapControl.getGridLinesBtn().click();
        Util.checkScreenshotForElementWithWait(
                detailedShowPage.mapBlock.getMap(),
                "actGridLinesBtnOn",
                "expGridLinesBtnOn",
                "checkGridLinesBtnOn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
