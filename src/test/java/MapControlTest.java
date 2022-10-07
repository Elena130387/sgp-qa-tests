import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ShapeCalcPage;
import util.JunitExtension;
import util.Util;

import java.io.IOException;

@ExtendWith(JunitExtension.class)
public class MapControlTest {
    private final String SHAPE_LINK = "?showAside=true&shape=215&detailed=true";
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlTest\\";
    ShapeCalcPage shapeCalcPage;

    @BeforeEach
    void openMainPage() {
        shapeCalcPage = new ShapeCalcPage().openShapeCalcPageWithMapWait(SHAPE_LINK);
        shapeCalcPage.selectDefaultSettings();
    }

    @Test
    void checkZoom() throws IOException {
        shapeCalcPage.mapControl.clickZoomIn(1);
        Util.checkScreenshot(
                "actMainPageZoomIn",
                "expMainPageZoomOut",
                "checkMainPageZoomIn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        shapeCalcPage.mapControl.clickZoomOut(2);
        Util.checkScreenshot(
                "actMainPageZoomOut",
                "expMainPageZoomOut",
                "checkMainPageZoomOut",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
