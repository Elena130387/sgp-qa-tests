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
public class BuildingDamageCalcTest {
    SgpMain mainPage;
    ShapeCalcPage shapeCalcPage;
    private final int SHAPE_ID = 347;
    private final String SHAPE_CALC_PAGE_URL = Constants.SHAPE_CHOOSE_URL_MID_TRUE + SHAPE_ID + Constants.SHAPE_DETAILS_URL_END;
    private final String TEST_SHAPE_NAME = "Oakland 6.58 кв. км";
    private final ShapeData shapeData = new ShapeData();
    private final DetailedShapePanel detailedShapePanel = new DetailedShapePanel();



    @BeforeEach
    void openMainPage() {
        mainPage = new SgpMain().openMainPage();
        mainPage.selectDefaultSettings();
    }

    @Test
    void checkChooseAndOpenCalcPolygonByName() {
        mainPage.mapControl.getPolygonsSectionBtn().click();
        assertThat(mainPage.shapesPanel.getShapesPanel()).isVisible();
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
}
