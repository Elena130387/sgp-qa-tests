package frontend.mapControl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.SGP_URL_DEV;

@ExtendWith(JunitExtension.class)
public class DerivativeRightsTest {

    DetailedShapePage detailedShapePage;
    private final int SHAPE_ID = 1277;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlTest/";
    private final String mapZoom = "2 km";

    @BeforeEach
    void openShapeShowPage() {
        detailedShapePage = new DetailedShapePage().openPageWithAsideTrueAndMapWait(SHAPE_ID, mapZoom);
        detailedShapePage.selectDefaultSettings();
    }

//    @Test
//    void checkCoverageMessage() {
//        assertAll(
//                () -> assertEquals("100 % of tiles have no derivative rights",
//                        detailedShapePage.page.locator("[data-cy=aside-visible]").locator(".css-1nrfgha").textContent(),
//                        "Неверный текст сообщения"),
//                () -> assertThat(detailedShapePage.page.locator("[data-cy=aside-visible]").locator(".css-1nrfgha")).isVisible()
//        );
//    }

    @Test
    void checkCoverageHighlighting() {
        detailedShapePage.mapControl.getCoverageHighlightingBtn().click();
        Util.checkScreenshotForElementWithLongWait(
                detailedShapePage.mapBlock.getMap(),
                "actCoverageHighlightingIn",
                "expCoverageHighlightingIn",
                "checkCoverageHighlightingIn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        detailedShapePage.mapControl.getCoverageHighlightingBtn().click();
        Util.checkScreenshotForElementWithLongWait(
                detailedShapePage.mapBlock.getMap(),
                "actCoverageHighlightingOut",
                "expCoverageHighlightingOut",
                "checkCoverageHighlightingOut",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
