import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ShapeShowPage;
import util.JunitExtension;
import util.Util;

import java.io.IOException;

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
    void checkResetNorth() throws IOException {
        shapeShowPage.mapBlock.turnMapToTheLeft(2);
        Util.checkScreenshot(
                "actTurnMapToLeft",
                "expTurnMapToLeft",
                "checkTurnMapToLeft",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
        shapeShowPage.mapControl.getCompassBtn().click();
        Util.checkScreenshot(
                "actResetNorth",
                "expResetNorth",
                "checkResetNorth",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
