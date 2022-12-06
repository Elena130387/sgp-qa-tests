package frontend.mapControl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ShapeShowPage;
import util.JunitExtension;
import util.Util;

import static util.Constants.MAP_ZOOM_STANDART;

@ExtendWith(JunitExtension.class)
public class GlobalGridTest {

    ShapeShowPage shapeShowPage;
    private final int SHAPE_ID = 215;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlTest/";
    private final String mapZoom = MAP_ZOOM_STANDART;


    @Test
    void checkGlobalGrid() {
        shapeShowPage = new ShapeShowPage().openPageWithAsideFalseAndMapWait(SHAPE_ID, mapZoom);
        shapeShowPage.selectDefaultSettings();

        shapeShowPage.mapControl.clickZoomOut(6);
        shapeShowPage.mapControl.getGlobalGridBtn().click();
        Util.checkScreenshotForElementWithWait(
                shapeShowPage.mapBlock.getMap(),
                "actGlobalGridOn",
                "expGlobalGridOn",
                "checkGlobalGridOn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        shapeShowPage.mapControl.getGlobalGridBtn().click();
        Util.checkScreenshotForElementWithWait(
                shapeShowPage.mapBlock.getMap(),
                "actGlobalGridOff",
                "expGlobalGridOff",
                "checkGlobalGridOff",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
