package frontend.shapesPanel;

import com.microsoft.playwright.FileChooser;
import elements.GeneralizedShapePanel;
import elements.ShapeCreationPanel;
import elements.ShapesPanel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.GeneralizedShapePage;
import pages.SgpMainPage;
import pages.ShapeCreationPage;
import util.JunitExtension;
import util.Util;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.*;
import static util.JunitExtension.page;

@ExtendWith(JunitExtension.class)
public class GeneralizedShapePanelTest {
    private static final int SHAPE_ID = 1557;
    private static final String SHAPE_NAME = "(НЕ УДАЛЯТЬ) Авто-тест: GeneralizedShapePanelTest";
    private static final String POLYGONS_COUNT_TEXT = "# сегм.";
    private static final String CREATED_DATE = "2022-12-23";
    private static final String CREATED_DATE_TEXT = "Создано";
    private static final String SHAPE_SIZE = "5.00 кв. км";
    private static final String SHAPE_SIZE_TEXT = "Пл. области";
    private static final String ONE_POLYGON_JSON = "./src/test/resources/shapeInput/onePolygonForUiTests.json";
    private static final String SHAPE_NAME_FOR_PROGRESS_BAR_CHECK = "DeleteMe";
    private static final int SHAPE_WITH_STOP_ICON_ID = 1383;
    private static final String STOPPED_ICON_TOOLTIP = "Расчет был остановлен";
    private static final int SHAPE_WITH_FAIL_ICON_ID = 1260;
    private static final String FAILED_ICON_TOOLTIP = "Ошибка при расчете";

    private static final String EXPSCREENSHOTS_TEST_CLASS_DIR = "GeneralizedShapePanel/";


    GeneralizedShapePage generalizedShapePage;
    GeneralizedShapePanel generalizedShapePanel;
    ShapeCreationPage shapeCreationPage;
    ShapeCreationPanel shapeCreationPanel;

    @BeforeEach
    void openGeneralizedShapePage() {
        generalizedShapePage = new GeneralizedShapePage().openGeneralizedShapePageWithAsideTrue(SHAPE_ID);
        generalizedShapePanel = generalizedShapePage.generalizedShapePanel;
    }


    @Test
    void checkGeneralizedShapePanelCommonView() {
        assertAll(
                () -> assertEquals(SHAPE_NAME, generalizedShapePage.getPage().getByText(SHAPE_NAME).textContent(),
                        "Неверное имя панели"),
                () -> assertThat(generalizedShapePanel.getRenameShapeBtn()).isVisible(),
                () -> assertThat(generalizedShapePanel.getDeleteShapeBtn()).isVisible(),
                () -> assertThat(generalizedShapePanel.getExpandBtn()).isHidden(),
                () -> assertThat(generalizedShapePanel.getCollapseBtn()).isVisible(),
                () -> assertEquals("1", generalizedShapePanel.getPolygonsCountNumber().textContent()),
                () -> assertEquals(POLYGONS_COUNT_TEXT, generalizedShapePanel.getPolygonsCountText().textContent()),
                () -> assertEquals(CREATED_DATE, generalizedShapePanel.getCreatedDate().textContent()),
                () -> assertEquals(CREATED_DATE_TEXT, generalizedShapePanel.getCreatedDateText().textContent()),
                () -> assertEquals(SHAPE_SIZE, generalizedShapePanel.getShapeSize().textContent()),
                () -> assertEquals(SHAPE_SIZE_TEXT, generalizedShapePanel.getShapeSizeText().textContent())
        );
    }

    @Test
    void checkRenameIconAndItsTooltipOnGeneralizedShapePanel() {
        generalizedShapePanel.checkRenameIconAndItsTooltip("checkRenameIconAndItsTooltipOnGeneralizedShapePanel");
    }

    @Test
    void checkDeleteIconAndItsTooltipOnGeneralizedShapePanel() {
        generalizedShapePanel.checkDeleteIconAndItsTooltip("checkDeleteIconAndItsTooltipOnGeneralizedShapePanel");
    }

    @Test
    void checkCollapseIconAndItsTooltipOnGeneralizedShapePanel() {
        generalizedShapePanel.checkCollapseIconAndItsTooltip("checkCollapseIconAndItsTooltipOnGeneralizedShapePanel");
    }

    @Test
    void checkRenameWindowCommonViewFromGeneralizedShapePanel() {
        generalizedShapePanel.checkRenameWindowCommonView(SHAPE_NAME);
    }

    @Test
    void checkDeleteWindowCommonViewFromGeneralizedShapePanel(){
        generalizedShapePanel.checkDeleteWindowCommonView(SHAPE_NAME);
    }

    @Test
    void checkShapeCollapsing(){
        generalizedShapePanel.getCollapseBtn().click();
        assertEquals(SHOW_ASIDE_TRUE_URL, generalizedShapePage.getPage().url(), "Неверный URL");
    }

    @Test
    void checkClickByShapeName(){
        generalizedShapePage.getPage().getByText(SHAPE_NAME).click();
        assertEquals(GENERALIZED_SHAPE_PAGE_WITH_ASIDE_TRUE_URL + SHAPE_ID + SHAPE_DETAILS_URL_END,
                generalizedShapePage.getPage().url(), "Неверный URL");
    }

    @Test
    void checkProgressBarAndCalculationStopping(){
        shapeCreationPage = new ShapeCreationPage().openShapeCreationPageWithPanelWait();
        shapeCreationPanel = shapeCreationPage.shapeCreationPanel;

        FileChooser fileChooser = page.waitForFileChooser(() -> shapeCreationPanel.getChooseFileBtn().click());
        fileChooser.setFiles(Paths.get(ONE_POLYGON_JSON));

        shapeCreationPanel.getShapeNameInput().click();
        shapeCreationPanel.getShapeNameInput().clear();
        shapeCreationPanel.getShapeNameInput().type(SHAPE_NAME_FOR_PROGRESS_BAR_CHECK);

        shapeCreationPanel.getCreateProcessBtn().click();

        generalizedShapePanel.waitForProgressBar();
        assertThat(generalizedShapePanel.getStopCalculationBtn()).isVisible();

        generalizedShapePage.generalizedShapePanel.getStopCalculationBtn().click();
        generalizedShapePage.generalizedShapePanel.getStopCalculationYes().click();
        generalizedShapePage.generalizedShapePanel.waitForStoppedCalculationIcon();
    }

    @Test
    void checkStoppedCalculationIconAndItsTooltip(){
        generalizedShapePage = new GeneralizedShapePage().openGeneralizedShapePageWithAsideTrue(SHAPE_WITH_STOP_ICON_ID);
        Util.checkScreenshotForElement(
                generalizedShapePanel.getStoppedCalculationIcon(),
                "actStoppedCalculationIcon",
                "expStoppedCalculationIcon",
                "checkStoppedCalculationIconAndItsTooltip",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        generalizedShapePanel.checkStoppedCalculationIconTooltip(STOPPED_ICON_TOOLTIP);
    }

    @Test
    void checkFailedCalculationIconAndItsTooltip(){
        generalizedShapePage = new GeneralizedShapePage().openGeneralizedShapePageWithAsideTrue(SHAPE_WITH_FAIL_ICON_ID);
        Util.checkScreenshotForElement(
                generalizedShapePanel.getFailedCalculationIcon(),
                "actFailedCalculationIcon",
                "expFailedCalculationIcon",
                "checkFailedCalculationIconAndItsTooltip",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        generalizedShapePanel.checkFailedCalculationIconTooltip(FAILED_ICON_TOOLTIP);
    }

    @AfterAll
    static void cleanUp() {
        SgpMainPage sgpMainPage = new SgpMainPage().openMainPageWithHeaderWait();
        ShapesPanel shapesPanel = sgpMainPage.shapesPanel;
        shapesPanel.deleteShapeByName(SHAPE_NAME_FOR_PROGRESS_BAR_CHECK);
    }

}
