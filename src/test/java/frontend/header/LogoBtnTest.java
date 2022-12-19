package frontend.header;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import pages.ShapeCreationPage;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.SGP_URL_DEV;

@ExtendWith(JunitExtension.class)
public class LogoBtnTest {
    ShapeCreationPage shapeCreationPage;
    DetailedShapePage detailedShapePage;
    private final int SHAPE_ID = 1277;

    @Test
    void checkLogoClickFromShapeCreationPage() {
        shapeCreationPage = new ShapeCreationPage().openShapeCreationPage();
        shapeCreationPage.header.waitForHeader();

        shapeCreationPage.header.getTextLogo().click();
        assertAll(
                () -> assertEquals(SGP_URL_DEV, shapeCreationPage.getPage().url(), "Неверный URL"),
                () -> assertThat(shapeCreationPage.header.getNewShape()).isVisible(),
                () -> assertThat(shapeCreationPage.header.getNewShape()).isEnabled(),
                () -> assertThat(shapeCreationPage.shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(shapeCreationPage.mapControl.getShapesPanelHideShowBtn()).isVisible(),
                () -> assertThat(shapeCreationPage.mapControl.getShapesPanelHideShowBtn()).isEnabled()
        );
    }

    @Test
    void checkLogoClickFromPageWithAsideFalse() {
        detailedShapePage = new DetailedShapePage().openPageWithAsideFalse(SHAPE_ID);

        detailedShapePage.header.getTextLogo().click();
        assertAll(
                () -> assertEquals(SGP_URL_DEV, detailedShapePage.getPage().url(), "Неверный URL"),
                () -> assertThat(detailedShapePage.shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(detailedShapePage.mapControl.getShapesPanelHideShowBtn()).isVisible(),
                () -> assertThat(detailedShapePage.mapControl.getShapesPanelHideShowBtn()).isEnabled()
        );
    }
}
