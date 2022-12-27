package frontend.shapeDetails;

import elements.DetailedShapePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.*;

@ExtendWith(JunitExtension.class)
public class TabListTest {
    DetailedShapePage detailedShapePage;
    private final int SHAPE_ID = 611;
    private final String SHAPE_URL = GENERALIZED_SHAPE_PAGE_WITH_ASIDE_TRUE_URL + SHAPE_ID + SHAPE_DETAILS_URL_END;
    private final DetailedShapePanel detailedShapePanel = new DetailedShapePanel();

    @BeforeEach
    void openDetailedShapePage() {
        detailedShapePage = new DetailedShapePage().openPageWithAsideTrue(SHAPE_ID);
    }

    @Test
    void openOpensourceTab() {
        detailedShapePanel.openOpensourceTab();
        assertEquals(SHAPE_URL + SHAPE_OPENSOURCE_TAB_URL, detailedShapePage.getPage().url(),
                "Неверный URL");
    }

    @Test
    void openEstimatedTab() {
        detailedShapePanel.openEstimatedTab();
        assertEquals(SHAPE_URL + SHAPE_ESTIMATED_TAB_URL, detailedShapePage.getPage().url(),
                "Неверный URL");
    }

    @Test
    void openEconomicTab() {
        detailedShapePanel.openEconomicTab();
        assertEquals(SHAPE_URL + SHAPE_ECONOMIC_TAB_URL, detailedShapePage.getPage().url(),
                "Неверный URL");
    }

    @Test
    void openInsuranceTab() {
        detailedShapePanel.openInsuranceTab();
        assertEquals(SHAPE_URL + SHAPE_INSURANCE_TAB_URL, detailedShapePage.getPage().url(),
                "Неверный URL");
    }
}
