package frontend.shapesPanel.shapeCreationPanel;

import elements.ShapeCreationPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ShapeCreationPage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.SHOW_ASIDE_TRUE_URL;

@ExtendWith(JunitExtension.class)
public class ShapeCreationPanelTest {
    private static final String PANEL_NAME = "Создать новую область";
    private static final String SHAPE_NAME_INPUT_HEADER = "Название области";
    private static final String SHAPE_NAME_INPUT_PLACEHOLDER = "Введите название";
    private static final String CHOOSE_FILE_BTN_TEXT = "Выбрать файл";
    private static final String CANCEL_BTN_TEXT = "Отменить";
    private static final String CALCULATE_BTN_TEXT = "Создать и Запустить расчет";
    private static final String DRAW_POLYGON_TOOLTIP = "Создать вручную";

    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "ShapeCreationPanel/";

    ShapeCreationPage shapeCreationPage;
    ShapeCreationPanel shapeCreationPanel;

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
    }

    @Test
    void checkDrawPolygonSwitchedOffIconAndItsTooltip() {
        Util.checkScreenshotForElement(
                shapeCreationPanel.getDrawPolygonBtn(),
                "actDrawPolygonSwitchedOffIcon",
                "expDrawPolygonSwitchedOffIcon",
                "checkDrawPolygonSwitchedOffIcon",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        shapeCreationPanel.checkDrawPolygonBtnTooltip(DRAW_POLYGON_TOOLTIP);
    }

    @Test
    void checkDrawPolygonSwitchedOnIconAndItsTooltip() {
        shapeCreationPanel.getDrawPolygonBtn().click();

        Util.checkScreenshotForElement(
                shapeCreationPanel.getDrawPolygonBtn(),
                "actDrawPolygonSwitchedOnIcon",
                "expDrawPolygonSwitchedOnIcon",
                "checkDrawPolygonSwitchedOnIcon",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        shapeCreationPanel.checkDrawPolygonBtnTooltip(DRAW_POLYGON_TOOLTIP);
    }

    @Test
    void checkCancelButton() {
        shapeCreationPanel.getCancelBtn().click();
        assertAll(
                () -> assertEquals(SHOW_ASIDE_TRUE_URL, shapeCreationPage.getPage().url(), "Неверный URL"),
                () -> assertThat(shapeCreationPage.header.getNewShape()).isVisible()
        );
    }

}
