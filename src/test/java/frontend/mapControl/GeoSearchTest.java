package frontend.mapControl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMainPage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ExtendWith(JunitExtension.class)
public class GeoSearchTest {
    SgpMainPage sgpMainPage;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlTest/";

    @Test
    void checkGeoSearch() {
        sgpMainPage = new SgpMainPage().openMainPage();
        sgpMainPage.selectDefaultSettings();

        sgpMainPage.mapControl.getGeoSearch().click();
        sgpMainPage.mapControl.getGeoSearch().type("Moscow");
        assertThat(sgpMainPage.mapControl.getGeoSearchList()).isVisible();
        sgpMainPage.mapControl.getGeoSearchList().locator("text=Moscow").first().click();
        Util.checkScreenshotForElementWithWait(
                sgpMainPage.mapBlock.getMap(),
                "actGeoSearchResult",
                "expGeoSearchResult",
                "checkGeoSearchResult",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
