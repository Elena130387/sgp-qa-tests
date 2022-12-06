package frontend.mapControl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMainPage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ExtendWith(JunitExtension.class)
public class GeoSearchTest {
    SgpMainPage sgpMainPage;
    private final String CITY_NAME = "Moscow";
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlTest/";

    @BeforeEach
    void openShapeShowPage() {
        sgpMainPage = new SgpMainPage().openMainPageWithDefaultSettings();
        sgpMainPage.mapControl.getGeoSearch().click();
    }

    @Test
    void checkLatinCityGeoSearch() {
        sgpMainPage.mapControl.getGeoSearch().type("Moscow");
        assertThat(sgpMainPage.mapControl.getGeoSearchList()).isVisible();

        sgpMainPage.mapControl.getGeoSearchList().locator("text=" + CITY_NAME).first().click();
        Util.checkScreenshotForElementWithWait(
                sgpMainPage.mapBlock.getMap(),
                "actLatinCityGeoSearch",
                "expLatinCityGeoSearch",
                "checkLatinCityGeoSearch",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkCyrillicCityGeoSearch() {
        sgpMainPage.mapControl.getGeoSearch().type("Москва");
        sgpMainPage.mapControl.getGeoSearch().click();
        sgpMainPage.getPage().keyboard().press("Backspace");
        assertThat(sgpMainPage.mapControl.getGeoSearchList()).isVisible();

        sgpMainPage.mapControl.getGeoSearchList().locator("text=" + CITY_NAME).first().click();
        Util.checkScreenshotForElementWithWait(
                sgpMainPage.mapBlock.getMap(),
                "actCyrillicCityGeoSearch",
                "expCyrillicCityGeoSearch",
                "checkCyrillicCityGeoSearch",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
