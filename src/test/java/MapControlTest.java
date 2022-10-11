import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ShapeShowPage;
import util.JunitExtension;
import util.Util;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static util.Constants.MAP_COMPASS_IMG_STANDART;

@ExtendWith(JunitExtension.class)
public class MapControlTest {
    private final int SHAPE_ID = 215;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlTest\\";
    ShapeShowPage shapeShowPage;

    @BeforeEach
    void openShapeShowPage() {
        shapeShowPage = new ShapeShowPage().openShapeShowPageWithMapWait(SHAPE_ID);
        shapeShowPage.selectDefaultSettings();
    }

    @Test
    void checkZoom() throws IOException {
        shapeShowPage.mapControl.clickZoomIn(1);
        Util.checkScreenshot(
                "actMainPageZoomIn",
                "expMainPageZoomIn",
                "checkMainPageZoomIn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        shapeShowPage.mapControl.clickZoomOut(2);
        Util.checkScreenshot(
                "actMainPageZoomOut",
                "expMainPageZoomOut",
                "checkMainPageZoomOut",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkResetNorth() {
        shapeShowPage.mapBlock.turnMapToTheLeft(2);
        String compassState = shapeShowPage.mapControl.getCompassState();
        assertNotEquals (
                MAP_COMPASS_IMG_STANDART,
                compassState,
                "Поворот карты влево не выполнен");

        shapeShowPage.mapControl.getCompassBtn().click();
        shapeShowPage.getPage().waitForTimeout(1000);

        compassState = shapeShowPage.mapControl.getCompassState();
        assertEquals (
                MAP_COMPASS_IMG_STANDART,
                compassState,
                "Поворот карты к базовому расположению не выполнен");
    }

    @Test
    void checkGlobalGrid() throws IOException {
        shapeShowPage.mapControl.clickZoomOut(6);
        shapeShowPage.mapControl.getGlobalGridBtn().click();
        Util.checkScreenshot(
                "actGlobalGridOn",
                "expGlobalGridOn",
                "checkGlobalGridOn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
        shapeShowPage.mapControl.getGlobalGridBtn().click();
        Util.checkScreenshot(
                "actGlobalGridOff",
                "expGlobalGridOff",
                "checkGlobalGridOff",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
