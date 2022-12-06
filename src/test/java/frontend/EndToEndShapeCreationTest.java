package frontend;

import com.microsoft.playwright.FileChooser;
import elements.ShapeCreationPanel;
import org.junit.jupiter.api.AfterAll;
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
import static util.JunitExtension.page;

@ExtendWith(JunitExtension.class)
public class EndToEndShapeCreationTest {
    private static final String CITY = "Detroit";
    private static final String EXPECTED_ZOOM = "3 km";
    private static final String MANUAL_SHAPE_CREATION_NAME = "Auto-test: manual shape creation";
    private static final String PREPARED_JSON_FILE = "./src/test/resources/shapeInput/smallShapeInTulunForUiTests.json";
    private static final String JSON_SHAPE_CREATION_NAME = "Auto-test: json shape creation";
    ShapeCreationPage shapeCreationPage;
    ShapeCreationPanel shapeCreationPanel;
    ShapeShowPage shapeShowPage = new ShapeShowPage();

    @BeforeEach
    void openShapeCreationPage() {
        shapeCreationPage = new SgpMainPage().openShapeCreationPageAsUser();
        shapeCreationPanel = shapeCreationPage.shapeCreationPanel;
    }

    @Test
    void manualShapeCreation() {
        shapeCreationPage.mapControl.getGeoSearch().click();
        shapeCreationPage.mapControl.getGeoSearch().type(CITY);
        shapeCreationPage.mapControl.getGeoSearchList().locator("text=" + CITY).first().click();

        shapeCreationPage.mapBlock.waitForMapZoom(EXPECTED_ZOOM);

        shapeCreationPage.mapControl.clickZoomIn(2);

        shapeCreationPanel.getShapeNameInput().click();
        shapeCreationPanel.getShapeNameInput().type(MANUAL_SHAPE_CREATION_NAME);

        shapeCreationPanel.getDrawPolygonBtn().click();
        shapeCreationPage.drawRectangleByScreenPositions(800, 300, 900, 400);

        shapeCreationPanel.getCreateProcess().click();

        shapeShowPage.shapeData.waitForProgressBar();
        assertThat(shapeShowPage.changeShapeMenuBtn.getMenuBtn()).isVisible();
    }

    @Test
    void jsonShapeCreation() {
        FileChooser fileChooser = page.waitForFileChooser(() -> shapeCreationPanel.getChooseFileBtn().click());
        fileChooser.setFiles(Paths.get(PREPARED_JSON_FILE));

        shapeCreationPanel.getCreateProcess().click();

        shapeShowPage.shapeData.waitForProgressBar();
        assertThat(shapeShowPage.changeShapeMenuBtn.getMenuBtn()).isVisible();
    }

    @AfterAll
    static void cleanUp() {
        BasePage.deleteShapeByName(MANUAL_SHAPE_CREATION_NAME);
        BasePage.deleteShapeByName(JSON_SHAPE_CREATION_NAME);
    }
}