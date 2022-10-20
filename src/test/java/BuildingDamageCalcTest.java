import elements.DetailedShapePanel;
import elements.ShapeData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMain;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(JunitExtension.class)
public class BuildingDamageCalcTest {
    SgpMain mainPage;
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
        mainPage.openShapeWithName("Oakland 6.58 кв. км");
        assertThat(shapeData.getOpenDetailsBtn()).isVisible();
        shapeData.getOpenDetailsBtn().click();
        assertEquals("https://sgp-dev.syncretis.com/?showAside=true&shape=347&detailed=true",
                mainPage.getPage().url(), "Неверный URL");

    }

    @Test
    void checkDetailedShapeName() {
        mainPage.mapControl.getPolygonsSectionBtn().click();
        assertThat(mainPage.shapesPanel.getShapesPanel()).isVisible();
        mainPage.openShapeWithName("Oakland 6.58 кв. км");
        assertThat(shapeData.getOpenDetailsBtn()).isVisible();
        shapeData.getOpenDetailsBtn().click();
        assertEquals("https://sgp-dev.syncretis.com/?showAside=true&shape=347&detailed=true",
                mainPage.getPage().url(), "Неверный URL");
        assertEquals(TEST_SHAPE_NAME,
            detailedShapePanel.getShapeName().textContent(),
            "Wrong text");

    }
}
