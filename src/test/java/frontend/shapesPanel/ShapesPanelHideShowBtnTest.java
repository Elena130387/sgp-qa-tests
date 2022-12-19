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
public class ShapesPanelHideShowBtnTest {
    SgpMainPage sgpMainPage;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "ShapesPanelHideShowBtn/";
    private final String SHAPES_PANEL_SHOW_TOOLTIP = "Отобразить секцию";
    private final String SHAPES_PANEL_HIDE_TOOLTIP = "Скрыть секцию";

    @BeforeEach
    void openSgpMainPage() {
        sgpMainPage = new SgpMainPage().openMainPage();
    }

    @Test
    void checkShapesPanelHideShowBtnClick() {
        // Default settings check
        assertAll(
                () -> assertThat(sgpMainPage.shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(sgpMainPage.shapesPanel.getShapesPanelInvisible()).isHidden(),
                () -> assertThat(sgpMainPage.mapControl.getShapesPanelHideShowBtn()).isVisible(),
                () -> assertThat(sgpMainPage.mapControl.getShapesPanelHideShowBtn()).isEnabled(),
                () -> assertEquals(SGP_URL_DEV, sgpMainPage.getPage().url(), "Неверный URL")
        );


        // Hidden panel check
        sgpMainPage.mapControl.getShapesPanelHideShowBtn().click();
        assertAll(
                () -> assertThat(sgpMainPage.shapesPanel.getShapesPanelVisible()).isHidden(),
                () -> assertThat(sgpMainPage.shapesPanel.getShapesPanelInvisible()).isVisible(),
                () -> assertThat(sgpMainPage.mapControl.getShapesPanelHideShowBtn()).isVisible(),
                () -> assertThat(sgpMainPage.mapControl.getShapesPanelHideShowBtn()).isEnabled(),
                () -> assertEquals(SHOW_ASIDE_FALSE_URL, sgpMainPage.getPage().url(), "Неверный URL")
        );


        // Show panel check
        sgpMainPage.mapControl.getShapesPanelHideShowBtn().click();
        assertAll(
                () -> assertThat(sgpMainPage.shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(sgpMainPage.mapControl.getShapesPanelHideShowBtn()).isVisible(),
                () -> assertThat(sgpMainPage.mapControl.getShapesPanelHideShowBtn()).isEnabled(),
                () -> assertEquals(SHOW_ASIDE_TRUE_URL, sgpMainPage.getPage().url(), "Неверный URL")
        );
    }

    @Test
    void checkShapesPanelHideShowBtnTooltips() {
        // Default settings check
        sgpMainPage.mapControl.checkShapesPanelHideTooltip(SHAPES_PANEL_HIDE_TOOLTIP);

        // Hidden panel check
        sgpMainPage.mapControl.getShapesPanelHideShowBtn().click();
        sgpMainPage.mapControl.checkShapesPanelShowTooltip(SHAPES_PANEL_SHOW_TOOLTIP);

        // Show panel check
        sgpMainPage.mapControl.getShapesPanelHideShowBtn().click();
        sgpMainPage.mapControl.checkShapesPanelHideTooltip(SHAPES_PANEL_HIDE_TOOLTIP);

    }

    @Test
    void checkShapesPanelHideShowBtnIcons() {
        // Default settings check
        Util.checkScreenshotForElement(
                sgpMainPage.mapControl.getShapesPanelHideShowBtn(),
                "actShapesPanelVisible",
                "expShapesPanelVisible",
                "checkShapesPanelVisible",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        // Hidden panel check
        sgpMainPage.mapControl.getShapesPanelHideShowBtn().click();
        Util.checkScreenshotForElement(
                sgpMainPage.mapControl.getShapesPanelHideShowBtn(),
                "actShapesPanelInvisible",
                "expShapesPanelInvisible",
                "checkShapesPanelInvisible",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        // Show panel check
        sgpMainPage.mapControl.getShapesPanelHideShowBtn().click();
        Util.checkScreenshotForElement(
                sgpMainPage.mapControl.getShapesPanelHideShowBtn(),
                "actShapesPanelVisible",
                "expShapesPanelVisible",
                "checkShapesPanelVisible",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
