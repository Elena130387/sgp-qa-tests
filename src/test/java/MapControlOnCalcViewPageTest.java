import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ShapeCalcPage;
import util.JunitExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static util.Constants.MAP_ZOOM_STANDART;

@ExtendWith(JunitExtension.class)
public class MapControlOnCalcViewPageTest {

    private final int SHAPE_ID = 215;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlOnCalcViewPageTest\\";
    ShapeCalcPage shapeCalcPage;

    @BeforeEach
    void openShapeCalcPage() {
        shapeCalcPage = new ShapeCalcPage().openShapeCalcPageWithMapWait(SHAPE_ID);
        shapeCalcPage.selectDefaultSettings();
    }

    @Test
    void checkResetViewport() {
        shapeCalcPage.mapControl.clickZoomOut(2);
        String actualZoom = shapeCalcPage.mapBlock.getActualMapZoomWithoutSpace();
        String STANDART_ZOOM_WITHOUT_SPACE = MAP_ZOOM_STANDART.replaceAll("\\s+", "");
        assertNotEquals(
                STANDART_ZOOM_WITHOUT_SPACE,
                actualZoom,
                "Приближение карты не выполнено");

        shapeCalcPage.mapControl.getViewportBtn().click();
        shapeCalcPage.getPage().waitForTimeout(1000);

        actualZoom = shapeCalcPage.mapBlock.getActualMapZoomWithoutSpace();
        assertEquals(
                STANDART_ZOOM_WITHOUT_SPACE,
                actualZoom,
                "Возрат к стандартному zoom карты не выполнен");
    }
}
