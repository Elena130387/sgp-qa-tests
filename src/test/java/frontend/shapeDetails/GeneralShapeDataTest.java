package frontend.shapeDetails;

import elements.DetailedShapePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL;


@ExtendWith(JunitExtension.class)
public class GeneralShapeDataTest {
    private final int SHAPE_ID = 611;
    private final String SHAPE_CALC_PAGE_URL = SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL + SHAPE_ID;
    private final String TEST_SHAPE_NAME = "Парадайз, Калифорния - оценка повреждений зданий после лесного пожара 2018 года";
    private final String SHAPE_COUNT_NUM = "1";
    private final String SHAPE_COUNT = "# сегм.";
    private final String SHAPE_DATE = "2022-11-03";
    private final String SHAPE_CREATED = "Создано";
    private final String SHAPE_SIZE_NUM = "2.00 кв. км";
    private final String SHAPE_SIZE = "Пл. области";
    private final DetailedShapePanel detailedShapePanel = new DetailedShapePanel();
    DetailedShapePage detailedShapePage;

    @BeforeEach
    void openShapeCalcPage() {
        detailedShapePage = new DetailedShapePage().openPageWithAsideTrue(SHAPE_ID);
    }

    @Test
    void checkDetailedShapeName() {
        assertEquals(TEST_SHAPE_NAME,
                detailedShapePanel.getShapeName().textContent(),
                "Неверное имя шейпа");
    }

    @Test
    void checkShapeGeneralData() {
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

    @Test
    void checkBackBtn() {
        detailedShapePanel.getButtonBack().click();
        assertEquals(SHAPE_CALC_PAGE_URL,
                detailedShapePage.getPage().url(),
                "Неверный URL");
    }
}
