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
    private final String EXP_SCREENSHOTS_TEST_CLASS_DIR = "MapControlLayers/";
    private final String COVERAGE_HIGHLIGHTING_SHOW_TOOLTIP = "Highlight tiles with no derivative rights";
    private final String COVERAGE_HIGHLIGHTING_HIDE_TOOLTIP = "Disable highlighting tiles with no derivative rights";

    @Test
    void checkCoverageHighlighting() {
        detailedShapePage = new DetailedShapePage().openPageWithEstimatedTab(SHAPE_ID);
        detailedShapePage.selectDefaultSettings();

        detailedShapePage.mapControl.getCoverageHighlightingBtn().click();
        //      Remove focus from the button so that the tooltip is not displayed
        detailedShapePage.mapControl.getGeoSearch().click();
        Util.checkScreenshotForElementWithLongWait(
                detailedShapePage.mapBlock.getMap(),
                "actCoverageHighlightingIn",
                "expCoverageHighlightingIn",
                "checkCoverageHighlightingIn",
                EXP_SCREENSHOTS_TEST_CLASS_DIR);

        detailedShapePage.mapControl.getCoverageHighlightingBtn().click();
        //      Remove focus from the button so that the tooltip is not displayed
        detailedShapePage.mapControl.getGeoSearch().click();
        Util.checkScreenshotForElementWithLongWait(
                detailedShapePage.mapBlock.getMap(),
                "actCoverageHighlightingOut",
                "expCoverageHighlightingOut",
                "checkCoverageHighlightingOut",
                EXP_SCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkCoverageHighlightingTooltips() {
        detailedShapePage = new DetailedShapePage().openPageWithAsideTrueAndCoverageGridBarWait(SHAPE_ID);

        assertThat(detailedShapePage.mapControl.getCoverageHighlightingBtn()).isEnabled();
        detailedShapePage.mapControl.getCoverageHighlightingBtn().click();
        detailedShapePage.mapControl.checkCoverageHighlightHideTooltip(COVERAGE_HIGHLIGHTING_HIDE_TOOLTIP);


        detailedShapePage.mapControl.getCoverageHighlightingBtn().click();
        detailedShapePage.mapControl.checkCoverageHighlightShowTooltip(COVERAGE_HIGHLIGHTING_SHOW_TOOLTIP);
    }
}
