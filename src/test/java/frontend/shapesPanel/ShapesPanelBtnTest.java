package frontend.shapesPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMainPage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.*;

@ExtendWith(JunitExtension.class)
public class ShapesPanelBtnTest {
    SgpMainPage sgpMainPage;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "ShapesPanelBtn/";

    @BeforeEach
    void openSgpMainPage() {
        sgpMainPage = new SgpMainPage().openMainPage();
    }

    @Test
    void checkShapesPanelBtnClick() {
        // Default settings check
        assertAll(
                () -> assertThat(sgpMainPage.shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(sgpMainPage.shapesPanel.getShapesPanelInvisible()).isHidden(),
                () -> assertThat(sgpMainPage.mapControl.getPolygonsSectionBtn()).isVisible(),
                () -> assertThat(sgpMainPage.mapControl.getPolygonsSectionBtn()).isEnabled(),
                () -> assertEquals(SGP_URL_DEV, sgpMainPage.getPage().url(), "Неверный URL")
        );


        // Hidden panel check
        sgpMainPage.mapControl.getPolygonsSectionBtn().click();
        assertAll(
                () -> assertThat(sgpMainPage.shapesPanel.getShapesPanelVisible()).isHidden(),
                () -> assertThat(sgpMainPage.shapesPanel.getShapesPanelInvisible()).isVisible(),
                () -> assertThat(sgpMainPage.mapControl.getPolygonsSectionBtn()).isVisible(),
                () -> assertThat(sgpMainPage.mapControl.getPolygonsSectionBtn()).isEnabled(),
                () -> assertEquals(SHOW_ASIDE_FALSE_URL, sgpMainPage.getPage().url(), "Неверный URL")
        );


        // Show panel check
        sgpMainPage.mapControl.getPolygonsSectionBtn().click();
        assertAll(
                () -> assertThat(sgpMainPage.shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(sgpMainPage.mapControl.getPolygonsSectionBtn()).isVisible(),
                () -> assertThat(sgpMainPage.mapControl.getPolygonsSectionBtn()).isEnabled(),
                () -> assertEquals(SHOW_ASIDE_TRUE_URL, sgpMainPage.getPage().url(), "Неверный URL")
        );
    }

    @Test
    void checkShapesPanelBtnTooltips() {
        // Default settings check
        sgpMainPage.mapControl.checkPolygonsSectionHideTooltip("Скрыть секцию");

        // Hidden panel check
        sgpMainPage.mapControl.getPolygonsSectionBtn().click();
        sgpMainPage.mapControl.checkPolygonsSectionShowTooltip("Отобразить секцию");

        // Show panel check
        sgpMainPage.mapControl.getPolygonsSectionBtn().click();
        sgpMainPage.mapControl.checkPolygonsSectionHideTooltip("Скрыть секцию");

    }

    @Test
    void checkShapesPanelBtnIcons() {
        // Default settings check
        Util.checkScreenshotForElement(
                sgpMainPage.mapControl.getPolygonsSectionBtn(),
                "actShapesPanelVisible",
                "expShapesPanelVisible",
                "checkShapesPanelVisible",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        // Hidden panel check
        sgpMainPage.mapControl.getPolygonsSectionBtn().click();
        Util.checkScreenshotForElement(
                sgpMainPage.mapControl.getPolygonsSectionBtn(),
                "actShapesPanelInvisible",
                "expShapesPanelInvisible",
                "checkShapesPanelInvisible",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        // Show panel check
        sgpMainPage.mapControl.getPolygonsSectionBtn().click();
        Util.checkScreenshotForElement(
                sgpMainPage.mapControl.getPolygonsSectionBtn(),
                "actShapesPanelVisible",
                "expShapesPanelVisible",
                "checkShapesPanelVisible",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
