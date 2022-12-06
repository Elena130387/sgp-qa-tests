package frontend.shapeDetails.ShapeEstimatedTabDetails;

import elements.EconomicTab;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(JunitExtension.class)
public class ShapeEconomicTabDetailsTest {
    private final int SHAPE_ID = 611;
    private final String ECONOMIC_TITLE = "Экономический риск";
    private final String ECONOMIC_GENERAL = "Общий";
    private final String ECONOMIC_GENERAL_VALUE = "50 946 430 USD";
    DetailedShapePage detailedShapePage;
    private final EconomicTab economicTab = new EconomicTab();

    @Test
    void checkEconomicShapeDetails() {
        detailedShapePage = new DetailedShapePage().openPageWithEconomicTab(SHAPE_ID);
        assertEquals(ECONOMIC_TITLE,
                economicTab.getEconomicTitle().textContent(),
                "Неверный текст у поля 'Экономический риск'");
        assertEquals(ECONOMIC_GENERAL,
                economicTab.getEconomicGeneral().textContent(),
                "Неверный текст у поля 'Общий'");
        assertEquals(ECONOMIC_GENERAL_VALUE,
                economicTab.getEconomicGeneralValue().textContent(),
                "Неверная сумма экономического риска");
    }
}
