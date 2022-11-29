package frontend.header;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMain;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.NEW_CALCULATION_URL;
import static util.Constants.SGP_URL_DEV;

@ExtendWith(JunitExtension.class)
public class LogoBtnTest {
    SgpMain mainPage;

    @Test
    void checkLogoClick() {
        mainPage = new SgpMain().openMainPage();

        mainPage.getPage().navigate(NEW_CALCULATION_URL);
        mainPage.header.waitForHeader();
        assertThat(mainPage.header.getNewShape()).isHidden();

        //close the list of created shapes if it is necessary
        if (mainPage.shapesPanel.getShapesPanelVisible().isVisible()) {
            mainPage.mapControl.getPolygonsSectionBtn().click();
        }
        assertThat(mainPage.shapesPanel.getShapesPanelVisible()).isHidden();

        mainPage.header.getTextLogo().click();
        mainPage.header.waitForHeader();

        assertAll(
                () -> assertEquals(SGP_URL_DEV, mainPage.getPage().url(), "Неверный URL"),
                () -> assertThat(mainPage.header.getNewShape()).isEnabled(),
                () -> assertThat(mainPage.shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(mainPage.mapControl.getPolygonsSectionBtn()).isVisible()
        );
    }

}
