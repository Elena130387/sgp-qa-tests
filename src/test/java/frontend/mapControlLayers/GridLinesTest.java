package frontend.mapControlLayers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ExtendWith(JunitExtension.class)
public class GridLinesTest {
    private final int SHAPE_ID = 611;
    DetailedShapePage detailedShowPage;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlLayers/";

    @BeforeEach
    void openSgpMainPage() {
        detailedShowPage = new DetailedShapePage().openPageWithEconomicTabAndGridLinesBtnWait(SHAPE_ID);
    }

    @Test
    void checkGridLines() {
        detailedShowPage.selectDefaultSettings();

        detailedShowPage.mapControl.getGridLinesBtn().click();
        Util.checkScreenshotForElementWithWait(
                detailedShowPage.mapBlock.getMap(),
                "actGridLinesOff",
                "expGridLinesOff",
                "checkGridLinesOff",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        detailedShowPage.mapControl.getGridLinesBtn().click();
        Util.checkScreenshotForElementWithWait(
                detailedShowPage.mapBlock.getMap(),
                "actGridLinesBtnOn",
                "expGridLinesBtnOn",
                "checkGridLinesBtnOn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkGridLinesBtnTooltips() {
        assertThat(detailedShowPage.mapControl.getGridLinesBtn()).isEnabled();
        detailedShowPage.mapControl.getGridLinesBtn().click();
        detailedShowPage.mapControl.checkGridLinesShowTooltip("Show grid lines");

        detailedShowPage.mapControl.getGridLinesBtn().click();
        detailedShowPage.mapControl.checkGridLinesHideTooltip("Hide grid lines");
    }
}
