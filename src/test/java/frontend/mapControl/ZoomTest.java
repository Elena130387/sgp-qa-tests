package frontend.mapControl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ShapeShowPage;
import util.JunitExtension;
import util.Util;

import static util.Constants.MAP_ZOOM_STANDART;

@ExtendWith(JunitExtension.class)
public class ZoomTest {
    ShapeShowPage shapeShowPage;
    private final int SHAPE_ID = 215;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlTest/";
    private final String mapZoom = MAP_ZOOM_STANDART;


    @Test
    void checkZoom() {
        shapeShowPage = new ShapeShowPage().openPageWithAsideFalseAndMapWait(SHAPE_ID, mapZoom);
        shapeShowPage.selectDefaultSettings();

        shapeShowPage.mapControl.clickZoomIn(1);
        Util.checkScreenshotForElementWithWait(
                shapeShowPage.mapBlock.getMap(),
                "actZoomIn",
                "expZoomIn",
                "checkZoomIn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        shapeShowPage.mapControl.clickZoomOut(2);
        Util.checkScreenshotForElementWithWait(
                shapeShowPage.mapBlock.getMap(),
                "actZoomOut",
                "expZoomOut",
                "checkZoomOut",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
