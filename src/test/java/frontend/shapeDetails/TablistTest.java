package frontend.shapeDetails;

import elements.DetailedShapePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.*;

@ExtendWith(JunitExtension.class)
public class TablistTest {
    DetailedShapePage detailedShapePage;

    private final int SHAPE_ID = 611;
    private final String SHAPE_URL = SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL + SHAPE_ID + SHAPE_DETAILS_URL_END;
    private final DetailedShapePanel detailedShapePanel = new DetailedShapePanel();

    @BeforeEach
    void openShapeCalcPage() {

        detailedShapePage = new DetailedShapePage().openPageWithAsideTrue(SHAPE_ID);
    }

    @Test
    void openOpensourceTab() {
        assertThat(detailedShapePanel.getOpensourceTabBtn()).isEnabled();
        detailedShapePanel.openOpensourceTab();
        assertEquals(SHAPE_URL + SHAPE_OPENSOURCE_TAB_URL, detailedShapePage.getPage().url(),
                "Неверный URL");
    }

    @Test
    void openEstimatedTab() {
        assertThat(detailedShapePanel.getEstimatedTabBtn()).isEnabled();
        detailedShapePanel.openEstimatedTab();
        assertEquals(SHAPE_URL + SHAPE_ESTIMATED_TAB_URL, detailedShapePage.getPage().url(),
                "Неверный URL");
    }

    @Test
    void openEconomicTab() {
        assertThat(detailedShapePanel.getEconomicTabBtn()).isEnabled();
        detailedShapePanel.openEconomicTab();
        assertEquals(SHAPE_URL + SHAPE_ECONOMIC_TAB_URL, detailedShapePage.getPage().url(),
                "Неверный URL");
    }

    @Test
    void openInsuranceTab() {
        assertThat(detailedShapePanel.getInsuranceTabBtn()).isEnabled();
        detailedShapePanel.openInsuranceTab();
        assertEquals(SHAPE_URL + SHAPE_INSURANCE_TAB_URL, detailedShapePage.getPage().url(),
                "Неверный URL");
    }
}
