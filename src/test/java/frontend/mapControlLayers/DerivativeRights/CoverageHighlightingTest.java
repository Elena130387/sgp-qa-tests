package frontend.mapControlLayers.DerivativeRights;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ExtendWith(JunitExtension.class)
public class CoverageHighlightingTest {

    DetailedShapePage detailedShapePage;
    private final int SHAPE_ID = 1277;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlLayers/";


    @Test
    void checkCoverageHighlighting() {
        detailedShapePage = new DetailedShapePage().openPageWithEstimatedTab(SHAPE_ID);
        detailedShapePage.selectDefaultSettings();

        detailedShapePage.mapControl.getCoverageHighlightingBtn().click();
        Util.checkScreenshotForElementWithLongWait(
                detailedShapePage.mapBlock.getMap(),
                "actCoverageHighlightingIn",
                "expCoverageHighlightingIn",
                "checkCoverageHighlightingIn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        detailedShapePage.mapControl.getCoverageHighlightingBtn().click();
        Util.checkScreenshotForElementWithLongWait(
                detailedShapePage.mapBlock.getMap(),
                "actCoverageHighlightingOut",
                "expCoverageHighlightingOut",
                "checkCoverageHighlightingOut",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkGridLinesBtnTooltips() {
        detailedShapePage = new DetailedShapePage().openPageWithAsideTrueAndCoverageGridBarWait(SHAPE_ID);

        assertThat(detailedShapePage.mapControl.getCoverageHighlightingBtn()).isEnabled();
        detailedShapePage.mapControl.getCoverageHighlightingBtn().click();
        detailedShapePage.mapControl.checkCoverageHighlightHideTooltip("Disable highlighting tiles with no derivative rights");


        detailedShapePage.mapControl.getCoverageHighlightingBtn().click();
        detailedShapePage.mapControl.checkCoverageHighlightShowTooltip("Highlight tiles with no derivative rights");
    }
}