package frontendTests;

import elements.DetailedShapePanel;
import elements.ShapeData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMain;
import pages.ShapeCalcPage;
import util.Constants;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(JunitExtension.class)
public class GeneralShapeData {
    SgpMain mainPage;
    ShapeCalcPage shapeCalcPage;
    private final int SHAPE_ID = 611;
    private final String SHAPE_CALC_PAGE_URL = Constants.SHAPE_CHOOSE_URL_MID + SHAPE_ID + Constants.SHAPE_DETAILS_URL_END;
    private final String TEST_SHAPE_NAME = "Парадайз, Калифорния - оценка повреждений зданий после лесного пожара 2018 года";
    private final String SHAPE_COUNT_NUM = "1";
    private final String SHAPE_COUNT = "# сегм.";
    private final String SHAPE_DATE = "2022-11-03";
    private final String SHAPE_CREATED = "Создано";
    private final String SHAPE_SIZE_NUM = "2.00 кв. км";
    private final String SHAPE_SIZE = "Пл. области";
    private final ShapeData shapeData = new ShapeData();
    private final DetailedShapePanel detailedShapePanel = new DetailedShapePanel();


    @BeforeEach
    void openMainPage() {
        mainPage = new SgpMain().openMainPage();
        mainPage.selectDefaultSettings();
    }

    @Test
    void checkChooseAndOpenCalcPolygonByName() {
        assertThat(mainPage.mapControl.getPolygonsSectionBtn()).isVisible();
        assertThat(mainPage.shapesPanel.getShapesPanelVisible()).isVisible();
        mainPage.openShapeWithName(TEST_SHAPE_NAME);
        assertThat(shapeData.getOpenDetailsBtn()).isVisible();
        shapeData.getOpenDetailsBtn().click();
        assertEquals(SHAPE_CALC_PAGE_URL,
                mainPage.getPage().url(), "Неверный URL");

    }

    @Test
    void checkDetailedShapeName() {
        shapeCalcPage = new ShapeCalcPage().openShapeCalcPage(SHAPE_ID);
        shapeCalcPage.selectDefaultSettings();
        assertEquals(TEST_SHAPE_NAME,
                detailedShapePanel.getShapeName().textContent(),
                "Неверное имя шейпа");

    }

    @Test
    void checkShapeGeneralData() {
        shapeCalcPage = new ShapeCalcPage().openShapeCalcPage(SHAPE_ID);
        shapeCalcPage.selectDefaultSettings();
        assertEquals(TEST_SHAPE_NAME,
                detailedShapePanel.getShapeName().textContent(),
                "Неверное имя шейпа");
        assertEquals(SHAPE_COUNT_NUM,
                detailedShapePanel.getShapeCountNum().textContent(),
                "Неверное количество полигонов");
        assertEquals(SHAPE_COUNT,
                detailedShapePanel.getShapeCount().textContent(),
                "Неверное название параметра");
        assertEquals(SHAPE_DATE,
                detailedShapePanel.getShapeDate().textContent(),
                "Неверная дата создания шейпа");
        assertEquals(SHAPE_CREATED,
                detailedShapePanel.getShapeCreated().textContent(),
                "Неверное название параметра");
        assertEquals(SHAPE_SIZE_NUM,
                detailedShapePanel.getShapeSizeNum().textContent(),
                "Неверный размер шейпа");
        assertEquals(SHAPE_SIZE,
                detailedShapePanel.getShapeSize().textContent(),
                "Неверное название параметра");
    }
}
