package frontend.mapControlLayers.DerivativeRights;

import elements.DetailedShapePanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(JunitExtension.class)
public class CoverageMessageTest {
    DetailedShapePage detailedShapePage;
    DetailedShapePanel detailedShapePanel = new DetailedShapePanel();
    private final int SHAPE_ID_100_PERCENT = 1277;
    private final int SHAPE_ID_No_100_PERCENT = 1455;
    private final String PERCENT_NO_100_TEXT = "27.3";
    private final String PERCENT_100_TEXT = "100";
    private final String MESSAGE_TEXT = " % of tiles have no derivative rights";

    @Test
    void check100PercentCoverageMessage() {
        detailedShapePage = new DetailedShapePage().openPageWithEstimatedTab(SHAPE_ID_100_PERCENT);
        Assert(0, PERCENT_100_TEXT);

        detailedShapePage = new DetailedShapePage().openPageWithEconomicTab(SHAPE_ID_100_PERCENT);
        Assert(1, PERCENT_100_TEXT);

        detailedShapePage = new DetailedShapePage().openPageWithInsuranceTab(SHAPE_ID_100_PERCENT);
        Assert(2, PERCENT_100_TEXT);
    }

    @Test
    void checkNo100PercentCoverageMessage() {
        detailedShapePage = new DetailedShapePage().openPageWithEstimatedTab(SHAPE_ID_No_100_PERCENT);
        Assert(0, PERCENT_NO_100_TEXT);

        detailedShapePage = new DetailedShapePage().openPageWithEconomicTab(SHAPE_ID_No_100_PERCENT);
        Assert(1, PERCENT_NO_100_TEXT);

        detailedShapePage = new DetailedShapePage().openPageWithInsuranceTab(SHAPE_ID_No_100_PERCENT);
        Assert(2, PERCENT_NO_100_TEXT);
    }

    private void Assert(int n, String percent) {
        assertAll(
                () -> assertThat(detailedShapePanel.getDerivativeRightsMsg().nth(n)).isVisible(),
                () -> assertEquals(percent + MESSAGE_TEXT,
                        detailedShapePanel.getDerivativeRightsMsg().nth(n).textContent(),
                        "Неверный текст сообщения")
        );
    }
}
