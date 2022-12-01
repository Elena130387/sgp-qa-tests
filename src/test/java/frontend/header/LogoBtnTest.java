package frontend.header;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.AddShapePage;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.SGP_URL_DEV;

@ExtendWith(JunitExtension.class)
public class LogoBtnTest {
    AddShapePage addShapePage;

    @Test
    void checkLogoClick() {
        addShapePage = new AddShapePage().openPageWithAsideFalse();
        addShapePage.header.getTextLogo().click();
        addShapePage.header.waitForHeader();

        assertAll(
                () -> assertEquals(SGP_URL_DEV, addShapePage.getPage().url(), "Неверный URL"),
                () -> assertThat(addShapePage.header.getNewShape()).isEnabled(),
                () -> assertThat(addShapePage.shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(addShapePage.mapControl.getPolygonsSectionBtn()).isVisible()
        );
    }

}
