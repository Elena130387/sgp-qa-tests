package frontend.mapControl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static util.Constants.MAP_ZOOM_STANDART;

@ExtendWith(JunitExtension.class)
public class ResetViewportBtnTest {

    DetailedShapePage detailedShapePage;
    private final int SHAPE_ID = 215;
    private final String mapZoom = MAP_ZOOM_STANDART;


    @BeforeEach
    void openShapeCalcPage() {
        detailedShapePage = new DetailedShapePage().openPageWithAsideTrueAndMapWait(SHAPE_ID, mapZoom);
        detailedShapePage.selectDefaultSettings();
    }

    @Test
    void checkResetViewport() {
        detailedShapePage.mapControl.clickZoomOut(2);
        String actualZoom = detailedShapePage.mapBlock.getActualMapZoomWithoutSpace();
        String EXPECT_ZOOM_WITHOUT_SPACE = mapZoom.replaceAll("\\s+", "");
        assertNotEquals(
                EXPECT_ZOOM_WITHOUT_SPACE,
                actualZoom,
                "Приближение карты не выполнено");

        detailedShapePage.mapControl.getViewportBtn().click();
        detailedShapePage.getPage().waitForTimeout(1000);

        actualZoom = detailedShapePage.mapBlock.getActualMapZoomWithoutSpace();
        assertEquals(
                EXPECT_ZOOM_WITHOUT_SPACE,
                actualZoom,
                "Возврат к стандартному zoom карты не выполнен");
    }
}
