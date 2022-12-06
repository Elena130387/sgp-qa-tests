package frontend.shapeDetails.ShapeEstimatedTabDetails;

import elements.InsuranceTab;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(JunitExtension.class)
public class ShapeInsuranceTabDetailsTest {
    DetailedShapePage detailedShapePage;
    private final int SHAPE_ID = 616;
    private final String INSURANCE_TITLE = "Страховой риск";
    private final String RESIDENTIAL_TYPE = "Жилой";
    private final String RESIDENTIAL_VALUE = "4 942 743 799 USD";
    private final String COMMERCIAL_TYPE = "Коммерческий";
    private final String COMMERCIAL_VALUE = "1 161 253 922 USD";
    private final String INDUSTRIAL_TYPE = "Промышленный";
    private final String INDUSTRIAL_VALUE = "266 206 534 USD";
    private final String AGRICULTURAL_TYPE = "Сельскохозяйственный";
    private final String AGRICULTURAL_VALUE = "148 319 392 USD";
    private final InsuranceTab insuranceTab = new InsuranceTab();

    @Test
    void checkInsuranceShapeDetails() {
        detailedShapePage = new DetailedShapePage().openPageWithInsuranceTab(SHAPE_ID);
        assertEquals(INSURANCE_TITLE,
                insuranceTab.getInsuranceTitle().textContent(),
                "Неверный текст у поля 'Страховой риск'");
        assertEquals(RESIDENTIAL_TYPE,
                insuranceTab.getResidentialType().textContent(),
                "Неверный текст у поля 'Жилой'");
        assertEquals(RESIDENTIAL_VALUE,
                insuranceTab.getResidentialValue().textContent(),
                "Неверная сумма страхового риска у типа 'Жилой'");
        assertEquals(COMMERCIAL_TYPE,
                insuranceTab.getCommercialType().textContent(),
                "Неверный текст у поля 'Коммерческий'");
        assertEquals(COMMERCIAL_VALUE,
                insuranceTab.getCommercialValue().textContent(),
                "Неверная сумма страхового риска у типа 'Коммерческий'");
        assertEquals(INDUSTRIAL_TYPE,
                insuranceTab.getIndustrialType().textContent(),
                "Неверный текст у поля 'Промышленный'");
        assertEquals(INDUSTRIAL_VALUE,
                insuranceTab.getIndustrialValue().textContent(),
                "Неверная сумма страхового риска у типа 'Промышленный'");
        assertEquals(AGRICULTURAL_TYPE,
                insuranceTab.getAgriculturalType().textContent(),
                "Неверный текст у поля 'Сельскохозяйственный'");
        assertEquals(AGRICULTURAL_VALUE,
                insuranceTab.getAgriculturalValue().textContent(),
                "Неверная сумма страхового риска у типа 'Сельскохозяйственный'");
    }
}
