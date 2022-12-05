package frontend.shapesPanel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMainPage;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.*;

@ExtendWith(JunitExtension.class)
public class ShapesPanelBtnTest {
    SgpMainPage sgpMainPage;

    @Test
    void checkGetShapesPanelBtn() {
        sgpMainPage = new SgpMainPage().openMainPage();

        assertAll(
                () -> assertThat(sgpMainPage.shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(sgpMainPage.mapControl.getPolygonsSectionBtn()).isVisible(),
                () -> assertEquals(SGP_URL_DEV, sgpMainPage.getPage().url(), "Неверный URL")
        );

        sgpMainPage.mapControl.getPolygonsSectionBtn().click();
        assertAll(
                () -> assertThat(sgpMainPage.shapesPanel.getShapesPanelVisible()).isHidden(),
                () -> assertThat(sgpMainPage.mapControl.getPolygonsSectionBtn()).isVisible(),
                () -> assertEquals(SHOW_ASIDE_FALSE_URL, sgpMainPage.getPage().url(), "Неверный URL")
        );

        sgpMainPage.mapControl.getPolygonsSectionBtn().click();
        assertAll(
                () -> assertThat(sgpMainPage.shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(sgpMainPage.mapControl.getPolygonsSectionBtn()).isVisible(),
                () -> assertEquals(SHOW_ASIDE_TRUE_URL, sgpMainPage.getPage().url(), "Неверный URL")
        );
    }
}
