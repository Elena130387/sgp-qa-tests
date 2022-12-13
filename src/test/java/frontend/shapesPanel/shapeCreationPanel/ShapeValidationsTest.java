package frontend.shapesPanel.shapeCreationPanel;

import elements.ShapeCreationPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ShapeCreationPage;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//  Класс не запускать целиком на выполнение до выполнения todo!
@ExtendWith(JunitExtension.class)
public class ShapeValidationsTest {
    private static final String NAME_ERROR_TEXT = "Введите название области";
    private static final String POLYGONS_ERROR_TEXT = "Добавьте хотя бы один сегмент";
    ShapeCreationPage shapeCreationPage;
    ShapeCreationPanel shapeCreationPanel;

    @BeforeEach
    void openShapeCreationPage() {
        shapeCreationPage = new ShapeCreationPage().openShapeCreationPageWithPanelWait();
        shapeCreationPanel = shapeCreationPage.shapeCreationPanel;
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
//    TODO: Когда на кнопку "Отмена" добавят очистку формы создания области, добавить эту очистку сюда

}