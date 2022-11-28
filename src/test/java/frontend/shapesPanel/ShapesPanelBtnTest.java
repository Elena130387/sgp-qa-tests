package frontend.shapesPanel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMain;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.*;

@ExtendWith(JunitExtension.class)
public class ShapesPanelBtnTest {
    SgpMain mainPage;

    @Test
    void checkGetShapesPanelBtn() {
        mainPage = new SgpMain().openMainPage();

        assertAll(
                () -> assertThat(mainPage.shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(mainPage.mapControl.getPolygonsSectionBtn()).isVisible(),
                () -> assertEquals(SGP_URL_DEV, mainPage.getPage().url(), "Неверный URL")
        );

        mainPage.mapControl.getPolygonsSectionBtn().click();
        assertAll(
                () -> assertThat(mainPage.shapesPanel.getShapesPanelVisible()).isHidden(),
                () -> assertThat(mainPage.mapControl.getPolygonsSectionBtn()).isVisible(),
                () -> assertEquals(SHOW_ASIDE_FALSE_URL, mainPage.getPage().url(), "Неверный URL")
        );

        mainPage.mapControl.getPolygonsSectionBtn().click();
        assertAll(
                () -> assertThat(mainPage.shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(mainPage.mapControl.getPolygonsSectionBtn()).isVisible(),
                () -> assertEquals(SHOW_ASIDE_TRUE_URL, mainPage.getPage().url(), "Неверный URL")
        );
    }
}
