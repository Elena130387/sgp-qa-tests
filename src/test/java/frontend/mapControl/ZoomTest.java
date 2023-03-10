package frontend.mapControl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;
import util.Util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static util.Constants.MAP_ZOOM_STANDARD;
import static util.Util.assertTooltipInAtrTitle;

@ExtendWith(JunitExtension.class)
public class ZoomTest {
    DetailedShapePage detailedShowPage;
    private final int SHAPE_ID = 611;
    private final String EXP_SCREENSHOTS_TEST_CLASS_DIR = "MapControl/";
    private final String mapZoom = MAP_ZOOM_STANDARD;
    private final String ZOOM_IN_BTN_TOOLTIP = "Zoom In";
    private final String ZOOM_OUT_BTN_TOOLTIP = "Zoom Out";
    private final String RESET_VIEWPORT_BTN_TOOLTIP = "Reset viewport";

    @BeforeEach
    void openDetailedShowPage() {
        detailedShowPage = new DetailedShapePage().openPageWithAsideFalseAndMapWait(SHAPE_ID, mapZoom);
        detailedShowPage.selectDefaultSettings();
    }

    @Test
    void checkZoomBtAndResetViewportBtnTooltips() {
        assertTooltipInAtrTitle(detailedShowPage.mapControl.getZoomOutBtn(), ZOOM_OUT_BTN_TOOLTIP);
        assertTooltipInAtrTitle(detailedShowPage.mapControl.getZoomInBtn(), ZOOM_IN_BTN_TOOLTIP);
        detailedShowPage.mapControl.checkResetViewportBtnTooltip(RESET_VIEWPORT_BTN_TOOLTIP);
    }

    @Test
    void checkZoom() {
        detailedShowPage.mapControl.clickZoomIn(1);
        Util.checkScreenshotForElementWithWait(
                detailedShowPage.mapBlock.getMap(),
                "actZoomIn",
                "expZoomIn",
                "checkZoomIn",
                EXP_SCREENSHOTS_TEST_CLASS_DIR);

        detailedShowPage.mapControl.clickZoomOut(2);
        Util.checkScreenshotForElementWithWait(
                detailedShowPage.mapBlock.getMap(),
                "actZoomOut",
                "expZoomOut",
                "checkZoomOut",
                EXP_SCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkResetViewportFromZoomOut() {
        detailedShowPage.mapControl.clickZoomOut(2);
        String actualZoom = detailedShowPage.mapBlock.getActualMapZoomWithoutSpace();
        String EXPECT_ZOOM_WITHOUT_SPACE = mapZoom.replaceAll("\\s+", "");
        assertNotEquals(
                EXPECT_ZOOM_WITHOUT_SPACE,
                actualZoom,
                "?????????????????????? ?????????? ???? ??????????????????");

        detailedShowPage.mapControl.getViewportBtn().click();
        detailedShowPage.getPage().waitForTimeout(1000);

        actualZoom = detailedShowPage.mapBlock.getActualMapZoomWithoutSpace();
        assertEquals(
                EXPECT_ZOOM_WITHOUT_SPACE,
                actualZoom,
                "?????????????? ?? ???????????????????????? zoom ?????????? ???? ????????????????");
    }

    @Test
    void checkResetViewportFromZoomIn() {
        detailedShowPage.mapControl.clickZoomIn(2);
        String actualZoom = detailedShowPage.mapBlock.getActualMapZoomWithoutSpace();
        String EXPECT_ZOOM_WITHOUT_SPACE = mapZoom.replaceAll("\\s+", "");
        assertNotEquals(
                EXPECT_ZOOM_WITHOUT_SPACE,
                actualZoom,
                "?????????????????? ?????????? ???? ??????????????????");

        detailedShowPage.mapControl.getViewportBtn().click();
        detailedShowPage.getPage().waitForTimeout(1000);

        actualZoom = detailedShowPage.mapBlock.getActualMapZoomWithoutSpace();
        assertEquals(
                EXPECT_ZOOM_WITHOUT_SPACE,
                actualZoom,
                "?????????????? ?? ???????????????????????? zoom ?????????? ???? ????????????????");
    }
}
