package frontend.shapesPanel;

import com.microsoft.playwright.FileChooser;
import elements.ShapeCreationPanel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.BasePage;
import pages.SgpMainPage;
import pages.ShapeCreationPage;
import pages.ShapeShowPage;
import util.JunitExtension;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static util.Constants.SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL;
import static util.Constants.SHOW_ASIDE_TRUE_URL;
import static util.JunitExtension.page;

//  Класс не запускать целиком на выполнение до выполнения todo!
@ExtendWith(JunitExtension.class)
public class ShapeCreationPanelTest {
    private static final String PANEL_NAME = "Создать новую область";
    private static final String SHAPE_NAME_INPUT_HEADER = "Название области";
    private static final String SHAPE_NAME_INPUT_PLACEHOLDER = "Введите название";
    private static final String CHOOSE_FILE_BTN_TEXT = "Выбрать файл";
    private static final String CANCEL_BTN_TEXT = "Отменить";
    private static final String CALCULATE_BTN_TEXT = "Создать и Запустить расчет";
    private static final String DRAW_POLYGON_TOOLTIP = "Создать вручную1";
    private static final String ONE_POLYGON_JSON = "./src/test/resources/shapeInput/onePolygonForUiTests.json";
    private static final String SHAPE_NAME_ONE_POLYGON_FROM_JSON = "Auto-test: json shape creation";
    private static final String TWO_POLYGONS_JSON = "./src/test/resources/shapeInput/twoPolygonsForUiTests.json";
    private static final String SHAPE_NAME_TWO_POLYGONS_FROM_JSON = "Auto-test: json with two polygons";
    private static final String SEGMENT_ONE_TEXT = "Сегмент 1";
    private static final String SEGMENT_TWO_TEXT = "Сегмент 2";
    private static final String TOTAL_AREA_TEXT = "Общая площадь";
    private static final String NAME_ERROR_TEXT = "Введите название области";
    private static final String POLYGONS_ERROR_TEXT = "Добавьте хотя бы один сегмент";
    private static final String CITY = "Detroit";
    private static final String EXPECTED_ZOOM = "3 km";
    private static final String SHAPE_NAME_ONE_MANUAL_POLYGON = "Auto-test: manual shape creation";
    private static final String SHAPE_NAME_TWO_MANUAL_POLYGONS = "Auto-test: shape with two manual polygons";
    private static final double DOUBLE_COMPARER_ALLOWABLE_DIFF = 0.01;
    ShapeCreationPage shapeCreationPage;
    ShapeCreationPanel shapeCreationPanel;
    ShapeShowPage shapeShowPage = new ShapeShowPage();

    @BeforeEach
    void openShapeCreationPage() {
        shapeCreationPage = new ShapeCreationPage().openShapeCreationPageWithPanelWait();
        shapeCreationPanel = shapeCreationPage.shapeCreationPanel;
    }

    @Test
    void checkShapeCreationPanelCommonView() {
        assertAll(
                () -> assertEquals(PANEL_NAME, shapeCreationPanel.getPanelName().textContent(),
                        "Неверное название панели"),
                () -> assertEquals(SHAPE_NAME_INPUT_HEADER, shapeCreationPanel.getShapeNameInputHeader().textContent(),
                        "Неверный заголовок для поля ввода названия области"),
                () -> assertThat(shapeCreationPanel.getShapeNameInput())
                        .hasAttribute("placeholder", SHAPE_NAME_INPUT_PLACEHOLDER),
                () -> assertEquals(CHOOSE_FILE_BTN_TEXT, shapeCreationPanel.getChooseFileBtn().textContent(),
                        "Неверное название кнопки для выбора файла"),
                () -> assertEquals(CANCEL_BTN_TEXT, shapeCreationPanel.getCancelBtn().textContent(),
                        "Неверное название кнопки отмены создания области"),
                () -> assertEquals(CALCULATE_BTN_TEXT, shapeCreationPanel.getCreateProcessBtn().textContent(),
                        "Неверное название кнопки запуска расчета"),
                () -> assertThat(shapeCreationPanel.getDrawPolygonBtn()).isVisible()
        );

        shapeCreationPanel.checkDrawPolygonBtnTooltip(DRAW_POLYGON_TOOLTIP);
    }

    @Test
    void checkOnePolygonManualAdding() {
        shapeCreationPanel.getDrawPolygonBtn().click();
        shapeCreationPage.drawRectangleByScreenPositions(800, 300, 900, 400);

        assertAll(
                () -> assertEquals(SEGMENT_ONE_TEXT, shapeCreationPanel.getSegmentOneName().textContent(),
                        "Неверный текст для первого сегмента"),
                () -> assertThat(shapeCreationPanel.getDeleteSegmentBtn()).isVisible(),
                () -> assertEquals(TOTAL_AREA_TEXT, shapeCreationPanel.getTotalAreaName().textContent(),
                        "Неверный текст для общей площади")
        );

        String segmentOneInfo = shapeCreationPanel.getSegmentOneArea().textContent();
        String totalAreaInfo = shapeCreationPanel.getTotalAreaValue().textContent();
        double segmentOneArea = shapeCreationPanel.segmentAreaValue(segmentOneInfo);
        double totalArea = shapeCreationPanel.totalAreaValue(totalAreaInfo);

        assertAll(
                () -> assertTrue(shapeCreationPanel.isSegmentInformationMatchPattern(segmentOneInfo)),
                () -> assertTrue(shapeCreationPanel.isTotalAreaInformationMatchPattern(totalAreaInfo)),
                () -> assertEquals(segmentOneArea, totalArea, "Общая площадь не совпадает с площадью сегмента")
        );
    }

    @Test
    void checkTwoPolygonsManualAdding() {
        shapeCreationPanel.getDrawPolygonBtn().click();
        shapeCreationPage.drawRectangleByScreenPositions(800, 300, 900, 400);
        shapeCreationPage.drawRectangleByScreenPositions(600, 500, 700, 600);

        assertAll(
                () -> assertEquals(SEGMENT_ONE_TEXT, shapeCreationPanel.getSegmentOneName().textContent(),
                        "Неверный текст для первого сегмента"),
                () -> assertEquals(SEGMENT_TWO_TEXT, shapeCreationPanel.getSegmentTwoName().textContent(),
                        "Неверный текст для второго сегмента"),
                () -> assertEquals(2, shapeCreationPanel.getDeleteSegmentBtn().count(),
                        "На странице должно быть две кнопки удаления сегмента"),
                () -> assertEquals(TOTAL_AREA_TEXT, shapeCreationPanel.getTotalAreaName().textContent(),
                        "Неверный текст для общей площади")
        );

        String segmentOneInfo = shapeCreationPanel.getSegmentOneArea().textContent();
        String segmentTwoInfo = shapeCreationPanel.getSegmentTwoArea().textContent();
        String totalAreaInfo = shapeCreationPanel.getTotalAreaValue().textContent();

        double segmentOneArea = shapeCreationPanel.segmentAreaValue(segmentOneInfo);
        double segmentTwoArea = shapeCreationPanel.segmentAreaValue(segmentTwoInfo);
        double totalArea = shapeCreationPanel.totalAreaValue(totalAreaInfo);

        assertAll(
                () -> assertTrue(shapeCreationPanel.isSegmentInformationMatchPattern(segmentOneInfo)),
                () -> assertTrue(shapeCreationPanel.isSegmentInformationMatchPattern(segmentTwoInfo)),
                () -> assertTrue(shapeCreationPanel.isTotalAreaInformationMatchPattern(totalAreaInfo)),
                () -> assertTrue(Math.abs(totalArea - (segmentOneArea + segmentTwoArea)) < DOUBLE_COMPARER_ALLOWABLE_DIFF,
                        "Общая площадь не совпадает с суммой площадей сегментов")
        );
    }

    @Test
    void checkOnePolygonFromJsonAdding() {
        FileChooser fileChooser = page.waitForFileChooser(() -> shapeCreationPanel.getChooseFileBtn().click());
        fileChooser.setFiles(Paths.get(ONE_POLYGON_JSON));

        assertAll(
                () -> assertEquals(SHAPE_NAME_ONE_POLYGON_FROM_JSON, shapeCreationPanel.getShapeNameInput().inputValue(),
                        "Неверное название области"),
                () -> assertEquals(SEGMENT_ONE_TEXT, shapeCreationPanel.getSegmentOneName().textContent(),
                        "Неверный текст для первого сегмента"),
                () -> assertThat(shapeCreationPanel.getDeleteSegmentBtn()).isVisible(),
                () -> assertEquals(TOTAL_AREA_TEXT, shapeCreationPanel.getTotalAreaName().textContent(),
                        "Неверный текст для общей площади")
        );

        String segmentOneInfo = shapeCreationPanel.getSegmentOneArea().textContent();
        String totalAreaInfo = shapeCreationPanel.getTotalAreaValue().textContent();
        double segmentOneArea = shapeCreationPanel.segmentAreaValue(segmentOneInfo);
        double totalArea = shapeCreationPanel.totalAreaValue(totalAreaInfo);

        assertAll(
                () -> assertTrue(shapeCreationPanel.isSegmentInformationMatchPattern(segmentOneInfo)),
                () -> assertTrue(shapeCreationPanel.isTotalAreaInformationMatchPattern(totalAreaInfo)),
                () -> assertEquals(segmentOneArea, totalArea, "Общая площадь не совпадает с площадью сегмента")
        );
    }

    @Test
    void checkTwoPolygonsFromJsonAdding() {
        FileChooser fileChooser = page.waitForFileChooser(() -> shapeCreationPanel.getChooseFileBtn().click());
        fileChooser.setFiles(Paths.get(TWO_POLYGONS_JSON));

        assertAll(
                () -> assertEquals(SHAPE_NAME_TWO_POLYGONS_FROM_JSON, shapeCreationPanel.getShapeNameInput().inputValue(),
                        "Неверное название области"),
                () -> assertEquals(SEGMENT_ONE_TEXT, shapeCreationPanel.getSegmentOneName().textContent(),
                        "Неверный текст для первого сегмента"),
                () -> assertEquals(SEGMENT_TWO_TEXT, shapeCreationPanel.getSegmentTwoName().textContent(),
                        "Неверный текст для второго сегмента"),
                () -> assertEquals(2, shapeCreationPanel.getDeleteSegmentBtn().count(),
                        "На странице должно быть две кнопки удаления сегмента"),
                () -> assertEquals(TOTAL_AREA_TEXT, shapeCreationPanel.getTotalAreaName().textContent(),
                        "Неверный текст для общей площади")
        );

        String segmentOneInfo = shapeCreationPanel.getSegmentOneArea().textContent();
        String segmentTwoInfo = shapeCreationPanel.getSegmentTwoArea().textContent();
        String totalAreaInfo = shapeCreationPanel.getTotalAreaValue().textContent();

        double segmentOneArea = shapeCreationPanel.segmentAreaValue(segmentOneInfo);
        double segmentTwoArea = shapeCreationPanel.segmentAreaValue(segmentTwoInfo);
        double totalArea = shapeCreationPanel.totalAreaValue(totalAreaInfo);

        assertAll(
                () -> assertTrue(shapeCreationPanel.isSegmentInformationMatchPattern(segmentOneInfo)),
                () -> assertTrue(shapeCreationPanel.isSegmentInformationMatchPattern(segmentTwoInfo)),
                () -> assertTrue(shapeCreationPanel.isTotalAreaInformationMatchPattern(totalAreaInfo)),
                () -> assertTrue(Math.abs(totalArea - (segmentOneArea + segmentTwoArea)) < DOUBLE_COMPARER_ALLOWABLE_DIFF,
                        "Общая площадь не совпадает с суммой площадей сегментов")
        );
    }

    @Test
    void checkOnePolygonDeletion() {
        shapeCreationPanel.getDrawPolygonBtn().click();
        shapeCreationPage.drawRectangleByScreenPositions(800, 300, 900, 400);
        shapeCreationPage.drawRectangleByScreenPositions(600, 500, 700, 600);

        String segmentTwoInfo = shapeCreationPanel.getSegmentTwoArea().textContent();
        double segmentTwoArea = shapeCreationPanel.segmentAreaValue(segmentTwoInfo);

        shapeCreationPanel.getDeleteSegmentBtn().nth(0).click();

        String segmentOneInfo = shapeCreationPanel.getSegmentOneArea().textContent();
        double segmentOneArea = shapeCreationPanel.segmentAreaValue(segmentOneInfo);

        assertAll(
                () -> assertEquals(SEGMENT_ONE_TEXT, shapeCreationPanel.getSegmentOneName().textContent(),
                        "Неверный текст для первого сегмента"),
                () -> assertThat(shapeCreationPanel.getSegmentTwoName()).isHidden(),
                () -> assertThat(shapeCreationPanel.getSegmentTwoArea()).isHidden(),
                () -> assertEquals(segmentTwoArea, segmentOneArea, "Ошибка при удалении сегмента")

        );
    }

    @Test
    void checkOneManualPolygonCreation() {
        shapeCreationPage.mapControl.getGeoSearch().click();
        shapeCreationPage.mapControl.getGeoSearch().type(CITY);
        shapeCreationPage.mapControl.getGeoSearchList().locator("text=" + CITY).first().click();

        shapeCreationPage.mapBlock.waitForMapZoom(EXPECTED_ZOOM);

        shapeCreationPage.mapControl.clickZoomIn(2);

        shapeCreationPanel.getShapeNameInput().click();
        shapeCreationPanel.getShapeNameInput().type(SHAPE_NAME_ONE_MANUAL_POLYGON);

        shapeCreationPanel.getDrawPolygonBtn().click();
        shapeCreationPage.drawRectangleByScreenPositions(800, 300, 900, 400);

        shapeCreationPanel.getCreateProcessBtn().click();

        shapeShowPage.shapeData.waitForProgressBar();

        assertAll(
                () -> assertTrue(shapeShowPage.getPage().url().startsWith(SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL)),
                () -> assertThat(shapeShowPage.shapeData.getStopCalculationBtn()).isVisible(),
                () -> assertEquals("1", shapeShowPage.shapeData.getPolygonsCountNumber().textContent(),
                        "Ожидается, что в области один полигон")
        );
    }

    @Test
    void checkTwoManualPolygonsCreation() {
        shapeCreationPage.mapControl.getGeoSearch().click();
        shapeCreationPage.mapControl.getGeoSearch().type(CITY);
        shapeCreationPage.mapControl.getGeoSearchList().locator("text=" + CITY).first().click();

        shapeCreationPage.mapBlock.waitForMapZoom(EXPECTED_ZOOM);

        shapeCreationPage.mapControl.clickZoomIn(2);

        shapeCreationPanel.getShapeNameInput().click();
        shapeCreationPanel.getShapeNameInput().type(SHAPE_NAME_TWO_MANUAL_POLYGONS);

        shapeCreationPanel.getDrawPolygonBtn().click();
        shapeCreationPage.drawRectangleByScreenPositions(800, 300, 900, 400);
        shapeCreationPage.drawRectangleByScreenPositions(600, 500, 700, 600);

        shapeCreationPanel.getCreateProcessBtn().click();

        shapeShowPage.shapeData.waitForProgressBar();

        assertAll(
                () -> assertTrue(shapeShowPage.getPage().url().startsWith(SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL)),
                () -> assertThat(shapeShowPage.shapeData.getStopCalculationBtn()).isVisible(),
                () -> assertEquals("2", shapeShowPage.shapeData.getPolygonsCountNumber().textContent(),
                        "Ожидается, что в области два полигона")
        );
    }

    @Test
    void checkOnePolygonFromJsonCreation() {
        FileChooser fileChooser = page.waitForFileChooser(() -> shapeCreationPanel.getChooseFileBtn().click());
        fileChooser.setFiles(Paths.get(ONE_POLYGON_JSON));
        shapeCreationPanel.getCreateProcessBtn().click();

        shapeShowPage.shapeData.waitForProgressBar();

        assertAll(
                () -> assertTrue(shapeShowPage.getPage().url().startsWith(SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL)),
                () -> assertThat(shapeShowPage.shapeData.getStopCalculationBtn()).isVisible(),
                () -> assertEquals("1", shapeShowPage.shapeData.getPolygonsCountNumber().textContent(),
                        "Ожидается, что в области один полигон")
        );
    }

    @Test
    void checkTwoPolygonsFromJsonCreation() {
        FileChooser fileChooser = page.waitForFileChooser(() -> shapeCreationPanel.getChooseFileBtn().click());
        fileChooser.setFiles(Paths.get(TWO_POLYGONS_JSON));
        shapeCreationPanel.getCreateProcessBtn().click();

        shapeShowPage.shapeData.waitForProgressBar();

        assertAll(
                () -> assertTrue(shapeShowPage.getPage().url().startsWith(SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL)),
                () -> assertThat(shapeShowPage.shapeData.getStopCalculationBtn()).isVisible(),
                () -> assertEquals("2", shapeShowPage.shapeData.getPolygonsCountNumber().textContent(),
                        "Ожидается, что в области два полигона")
        );
    }

    @Test
    void checkCancelButton() {
        shapeCreationPanel.getCancelBtn().click();
        assertAll(
                () -> assertEquals(SHOW_ASIDE_TRUE_URL, shapeCreationPage.getPage().url(), "Неверный URL"),
                () -> assertThat(shapeCreationPage.header.getNewShape()).isVisible()
        );
    }

    @Test
    void checkShapeNameShouldBeMandatory() {
        shapeCreationPanel.getDrawPolygonBtn().click();
        shapeCreationPage.drawRectangleByScreenPositions(800, 300, 900, 400);
        shapeCreationPanel.getCreateProcessBtn().click();

        assertAll(
                () -> assertThat(shapeCreationPanel.getNameError()).isVisible(),
                () -> assertEquals(NAME_ERROR_TEXT, shapeCreationPanel.getNameError().textContent(),
                        "Неверный текст ошибки для области без названия"),
                () -> assertThat(shapeCreationPanel.getCreateProcessBtn()).isVisible()
        );
    }

    @Test
    void checkShapeShouldContainAtLeastOnePolygon() {
        shapeCreationPanel.getShapeNameInput().click();
        shapeCreationPanel.getShapeNameInput().type("Test: shape must contain at least one polygon");
        shapeCreationPanel.getCreateProcessBtn().click();

        assertAll(
                () -> assertThat(shapeCreationPanel.getPolygonsError()).isVisible(),
                () -> assertEquals(POLYGONS_ERROR_TEXT, shapeCreationPanel.getPolygonsError().textContent(),
                        "Неверный текст ошибки для области без полигонов"),
                () -> assertThat(shapeCreationPanel.getCreateProcessBtn()).isVisible()
        );
    }

//    @AfterEach
//    // TODO: Когда на кнопку "Отмена" добавят очистку формы создания области, добавить эту очистку сюда

    @AfterAll
    static void cleanUp() {
        new SgpMainPage().openMainPageWithHeaderWait();
        BasePage.deleteShapeByName(SHAPE_NAME_ONE_MANUAL_POLYGON);
        BasePage.deleteShapeByName(SHAPE_NAME_TWO_MANUAL_POLYGONS);
        BasePage.deleteShapeByName(SHAPE_NAME_ONE_POLYGON_FROM_JSON);
        BasePage.deleteShapeByName(SHAPE_NAME_TWO_POLYGONS_FROM_JSON);
    }

}
