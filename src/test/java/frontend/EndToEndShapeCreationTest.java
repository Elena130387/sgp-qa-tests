package frontend;

import com.microsoft.playwright.FileChooser;
import elements.ShapeCreationPanel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.GeneralizedShapePage;
import pages.SgpMainPage;
import pages.ShapeCreationPage;
import util.JunitExtension;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static util.Constants.SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL;
import static util.JunitExtension.page;

@ExtendWith(JunitExtension.class)
public class EndToEndShapeCreationTest {
    private static final String CITY = "Detroit";
    private static final String EXPECTED_ZOOM = "3 km";
    private static final String SHAPE_NAME_ONE_MANUAL_POLYGON = "Auto-test: shape with one manual polygon";
    private static final String SHAPE_NAME_TWO_MANUAL_POLYGONS = "Auto-test: shape with two manual polygons";
    private static final double TIMEOUT_FOR_MAP_FOCUSING = 4000;
    private static final String SHAPE_NAME_ONE_POLYGON_FROM_JSON = "Auto-test: json with one polygon";
    private static final String SHAPE_NAME_TWO_POLYGONS_FROM_JSON = "Auto-test: json with two polygons";
    private static final String ONE_POLYGON_JSON = "./src/test/resources/shapeInput/onePolygonForUiTests.json";
    private static final String TWO_POLYGONS_JSON = "./src/test/resources/shapeInput/twoPolygonsForUiTests.json";

    ShapeCreationPage shapeCreationPage;
    ShapeCreationPanel shapeCreationPanel;
    GeneralizedShapePage generalizedShapePage = new GeneralizedShapePage();

    @BeforeEach
    void openShapeCreationPage() {
        shapeCreationPage = new SgpMainPage().openShapeCreationPageAsUser();
        shapeCreationPanel = shapeCreationPage.shapeCreationPanel;
    }

    @Test
    void checkOneManualPolygonCreation() {
        shapeCreationPage.mapControl.getGeoSearch().click();
        shapeCreationPage.mapControl.getGeoSearch().type(CITY);
        shapeCreationPage.mapControl.getGeoSearchList().locator("text=" + CITY).first().click();
        shapeCreationPage.getPage().waitForTimeout(TIMEOUT_FOR_MAP_FOCUSING);

        shapeCreationPage.mapBlock.waitForMapZoom(EXPECTED_ZOOM);

        shapeCreationPage.mapControl.clickZoomIn(2);

        shapeCreationPanel.getShapeNameInput().click();
        shapeCreationPanel.getShapeNameInput().type(SHAPE_NAME_ONE_MANUAL_POLYGON);

        shapeCreationPanel.getDrawPolygonBtn().click();
        shapeCreationPage.drawRectangleByScreenPositions(800, 300, 900, 400);

        shapeCreationPanel.getCreateProcessBtn().click();

        generalizedShapePage.generalizedShapePanel.waitForProgressBar();

        assertAll(
                () -> assertTrue(generalizedShapePage.getPage().url().startsWith(SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL)),
                () -> assertThat(generalizedShapePage.changeShapeMenuBtn.getMenuBtn()).isVisible(),
                () -> assertThat(generalizedShapePage.generalizedShapePanel.getStopCalculationBtn()).isVisible(),
                () -> assertEquals("1", generalizedShapePage.generalizedShapePanel.getPolygonsCountNumber().textContent(),
                        "Ожидается, что в области один полигон")
        );
    }

    @Test
    void checkTwoManualPolygonsCreation() {
        shapeCreationPage.mapControl.getGeoSearch().click();
        shapeCreationPage.mapControl.getGeoSearch().type(CITY);
        shapeCreationPage.mapControl.getGeoSearchList().locator("text=" + CITY).first().click();
        shapeCreationPage.getPage().waitForTimeout(TIMEOUT_FOR_MAP_FOCUSING);

        shapeCreationPage.mapBlock.waitForMapZoom(EXPECTED_ZOOM);

        shapeCreationPage.mapControl.clickZoomIn(2);

        shapeCreationPanel.getShapeNameInput().click();
        shapeCreationPanel.getShapeNameInput().type(SHAPE_NAME_TWO_MANUAL_POLYGONS);

        shapeCreationPanel.getDrawPolygonBtn().click();
        shapeCreationPage.drawRectangleByScreenPositions(800, 300, 900, 400);
        shapeCreationPage.drawRectangleByScreenPositions(600, 500, 700, 600);

        shapeCreationPanel.getCreateProcessBtn().click();

        generalizedShapePage.generalizedShapePanel.waitForProgressBar();

        assertAll(
                () -> assertTrue(generalizedShapePage.getPage().url().startsWith(SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL)),
                () -> assertThat(generalizedShapePage.changeShapeMenuBtn.getMenuBtn()).isVisible(),
                () -> assertThat(generalizedShapePage.generalizedShapePanel.getStopCalculationBtn()).isVisible(),
                () -> assertEquals("2", generalizedShapePage.generalizedShapePanel.getPolygonsCountNumber().textContent(),
                        "Ожидается, что в области два полигона")
        );
    }

    @Test
    void checkOnePolygonFromJsonCreation() {
        FileChooser fileChooser = page.waitForFileChooser(() -> shapeCreationPanel.getChooseFileBtn().click());
        fileChooser.setFiles(Paths.get(ONE_POLYGON_JSON));

        shapeCreationPanel.getCreateProcessBtn().click();

        generalizedShapePage.generalizedShapePanel.waitForProgressBar();

        assertAll(
                () -> assertTrue(generalizedShapePage.getPage().url().startsWith(SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL)),
                () -> assertThat(generalizedShapePage.changeShapeMenuBtn.getMenuBtn()).isVisible(),
                () -> assertThat(generalizedShapePage.generalizedShapePanel.getStopCalculationBtn()).isVisible(),
                () -> assertEquals("1", generalizedShapePage.generalizedShapePanel.getPolygonsCountNumber().textContent(),
                        "Ожидается, что в области один полигон")
        );
    }

    @Test
    void checkTwoPolygonsFromJsonCreation() {
        FileChooser fileChooser = page.waitForFileChooser(() -> shapeCreationPanel.getChooseFileBtn().click());
        fileChooser.setFiles(Paths.get(TWO_POLYGONS_JSON));

        shapeCreationPanel.getCreateProcessBtn().click();

        generalizedShapePage.generalizedShapePanel.waitForProgressBar();

        assertAll(
                () -> assertTrue(generalizedShapePage.getPage().url().startsWith(SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL)),
                () -> assertThat(generalizedShapePage.changeShapeMenuBtn.getMenuBtn()).isVisible(),
                () -> assertThat(generalizedShapePage.generalizedShapePanel.getStopCalculationBtn()).isVisible(),
                () -> assertEquals("2", generalizedShapePage.generalizedShapePanel.getPolygonsCountNumber().textContent(),
                        "Ожидается, что в области два полигона")
        );
    }

    @AfterEach
    void stopCalculation() {
        generalizedShapePage.generalizedShapePanel.getStopCalculationBtn().click();
        generalizedShapePage.generalizedShapePanel.getStopCalculationYes().click();
    }

    @AfterAll
    static void cleanUp() {
        new SgpMainPage().openMainPageWithHeaderWait();
        SgpMainPage.deleteShapeByName(SHAPE_NAME_ONE_MANUAL_POLYGON);
        SgpMainPage.deleteShapeByName(SHAPE_NAME_TWO_MANUAL_POLYGONS);
        SgpMainPage.deleteShapeByName(SHAPE_NAME_ONE_POLYGON_FROM_JSON);
        SgpMainPage.deleteShapeByName(SHAPE_NAME_TWO_POLYGONS_FROM_JSON);
    }

}