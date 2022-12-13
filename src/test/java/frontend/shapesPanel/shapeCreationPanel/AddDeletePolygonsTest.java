package frontend.shapesPanel.shapeCreationPanel;

import com.microsoft.playwright.FileChooser;
import elements.ShapeCreationPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ShapeCreationPage;
import util.JunitExtension;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static util.JunitExtension.page;

//  Класс не запускать целиком на выполнение до выполнения todo!
@ExtendWith(JunitExtension.class)
public class AddDeletePolygonsTest {
    private static final String SHAPE_NAME_ONE_POLYGON_FROM_JSON = "Auto-test: json shape creation";
    private static final String SHAPE_NAME_TWO_POLYGONS_FROM_JSON = "Auto-test: json with two polygons";
    private static final String ONE_POLYGON_JSON = "./src/test/resources/shapeInput/onePolygonForUiTests.json";
    private static final String TWO_POLYGONS_JSON = "./src/test/resources/shapeInput/twoPolygonsForUiTests.json";
    private static final String SEGMENT_ONE_TEXT = "Сегмент 1";
    private static final String SEGMENT_TWO_TEXT = "Сегмент 2";
    private static final String TOTAL_AREA_TEXT = "Общая площадь";
    private static final double DOUBLE_COMPARER_ALLOWABLE_DIFF = 0.01;
    ShapeCreationPage shapeCreationPage;
    ShapeCreationPanel shapeCreationPanel;

    @BeforeEach
    void openShapeCreationPage() {
        shapeCreationPage = new ShapeCreationPage().openShapeCreationPageWithPanelWait();
        shapeCreationPanel = shapeCreationPage.shapeCreationPanel;
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

//    @AfterEach
//    TODO: Когда на кнопку "Отмена" добавят очистку формы создания области, добавить эту очистку сюда

}