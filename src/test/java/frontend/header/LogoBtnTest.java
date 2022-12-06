package frontend.header;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ShapeCreationPage;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.SGP_URL_DEV;

@ExtendWith(JunitExtension.class)
public class LogoBtnTest {
    ShapeCreationPage shapeCreationPage;

    @Test
    void checkLogoClick() {
        shapeCreationPage = new ShapeCreationPage().openPageWithAsideFalse();
        shapeCreationPage.header.waitForHeader();

        shapeCreationPage.header.getTextLogo().click();
        assertAll(
                () -> assertEquals(SGP_URL_DEV, shapeCreationPage.getPage().url(), "Неверный URL"),
                () -> assertThat(shapeCreationPage.header.getNewShape()).isEnabled(),
                () -> assertThat(shapeCreationPage.shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(shapeCreationPage.mapControl.getPolygonsSectionBtn()).isVisible()
        );
    }

}
