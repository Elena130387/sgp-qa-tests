package frontend.mapControl.DerivativeRights;

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
    private final int SHAPE_ID = 1277;

    @Test
    void checkCoverageMessage() {
        detailedShapePage = new DetailedShapePage().openPageWithEstimatedTab(SHAPE_ID);
        Assert(0);

        detailedShapePage = new DetailedShapePage().openPageWithEconomicTab(SHAPE_ID);
        Assert(1);

        detailedShapePage = new DetailedShapePage().openPageWithInsuranceTab(SHAPE_ID);
        Assert(2);
    }

    private void Assert(int n) {
        assertAll(
                () -> assertThat(detailedShapePanel.getDerivativeRightsMsg().nth(n)).isVisible(),
                () -> assertEquals("100 % of tiles have no derivative rights",
                        detailedShapePanel.getDerivativeRightsMsg().nth(n).textContent(),
                        "Неверный текст сообщения")
        );
    }
}
