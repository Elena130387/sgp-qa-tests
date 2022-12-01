package frontend.mapControl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ShapeShowPage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static util.Constants.MAP_COMPASS_IMG_STANDART;
import static util.Constants.MAP_ZOOM_STANDART;

@ExtendWith(JunitExtension.class)
public class MapControlTest {

    ShapeShowPage shapeShowPage;
    private final int SHAPE_ID = 215;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlTest/";
    private final String standartMapZoom = MAP_ZOOM_STANDART;

    @BeforeEach
    void openShapeShowPage() {
        shapeShowPage = new ShapeShowPage().openPageWithAsideFalseAndMapWait(SHAPE_ID, standartMapZoom);
        shapeShowPage.selectDefaultSettings();
    }

    @Test
    void checkZoom() {
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

    @Test
    void checkResetNorth() {
        shapeShowPage.mapBlock.turnMapToTheLeft(2);
        String compassState = shapeShowPage.mapControl.getCompassState();
        assertNotEquals(
                MAP_COMPASS_IMG_STANDART,
                compassState,
                "Поворот карты влево не выполнен");

        shapeShowPage.mapControl.getCompassBtn().click();
        shapeShowPage.getPage().waitForTimeout(1000);

        compassState = shapeShowPage.mapControl.getCompassState();
        assertEquals(
                MAP_COMPASS_IMG_STANDART,
                compassState,
                "Поворот карты к базовому расположению не выполнен");
    }

    @Test
    void checkGlobalGrid() {
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


    @Test
    void checkGeoSearch() {
        shapeShowPage.mapControl.getGeoSearch().click();
        shapeShowPage.mapControl.getGeoSearch().type("Moscow");
        assertThat(shapeShowPage.mapControl.getGeoSearchList()).isVisible();
        shapeShowPage.mapControl.getGeoSearchList().locator("text=Moscow").first().click();
        Util.checkScreenshotForElementWithWait(
                shapeShowPage.mapBlock.getMap(),
                "actGeoSearchResult",
                "expGeoSearchResult",
                "checkGeoSearchResult",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
