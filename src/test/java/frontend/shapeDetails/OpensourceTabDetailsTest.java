package frontend.shapeDetails;

import com.microsoft.playwright.Locator;
import elements.OpensourceTab;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;
import util.Util;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Util.assertTooltip;

@ExtendWith(JunitExtension.class)
public class OpensourceTabDetailsTest {
    static DetailedShapePage detailedShapePage;
    private final OpensourceTab opensourceTab = new OpensourceTab();
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "OpensourceTab/";
    private static final int SHAPE_ID = 1254;
    private final String CENSUS_DATA = "Census данные";
    private final String GEOGRAPHIC_DETAILS = " Географические детали";
    private final String EXP_GEOGRAPHIC_DETAILS_TOOLTIP = "Horry County, South Carolina";
    private final String POPULATION_TITLE = "Население";
    private final String EXP_POPULATION_TOOLTIP = "Средний доход на душу населения и количество жителей для выбранной области";
    private final String INCOME_PER_CAPITA = "Средний доход на душу населения";
    private final String INCOME_PER_CAPITA_VALUE = "25 804 USD";
    private final String POPULATION_DATA = "Количество жителей";
    private final String POPULATION_DATA_VALUE = "310 186";
    private final String HOUSING_TITLE = "Количество жилых помещений";
    private final String EXP_HOUSING_TOOLTIP = "Количество жилых помещений для выбранной области";
    private final String HOUSING_TOTAL = "Все жилые помещения";
    private final String HOUSING_TOTAL_VALUE = "198 229";
    private final String HOUSING_IN_USE = "Жилые помещения, занимаемые владельцами";
    private final String HOUSING_IN_USE_VALUE = "87 486";
    private final String HOUSING_DISTRIBUTION = "Распределение жилых помещений";
    private final String EXP_HOUSING_DISTRIBUTION_TOOLTIP = "Распределение жилых помещений для выбранной области";
    private final String HOUSING_PRICE_TITLE = "Цена жилого помещения";
    private final String EXP_HOUSING_PRICE_TOOLTIP = "Средняя цена и медиана для выбранной области";
    private final String HOUSING_AVERAGE_PRICE = "Средняя";
    private final String HOUSING_AVERAGE_PRICE_VALUE = "202 946 USD";
    private final String HOUSING_MEDIAN_PRICE = "Медиана";
    private final String HOUSING_MEDIAN_PRICE_VALUE = "166 509 USD";

    @BeforeAll
    static void openTestPage() {

        detailedShapePage = new DetailedShapePage().openPageWithAsideTrue(SHAPE_ID);
    }

    @Test
    void checkOpensourceTabHeader() {
        assertEquals(CENSUS_DATA,
                opensourceTab.getCensusData().textContent(),
                "Неверное название заголовка 'Census данные'");
        assertEquals(GEOGRAPHIC_DETAILS,
                opensourceTab.getGeographicDetails().textContent(),
                "Неверное название заголовка 'Географические детали'");
        opensourceTab.checkGeographicDetailsInfoTooltip(EXP_GEOGRAPHIC_DETAILS_TOOLTIP);
    }

    @Test
    void checkPopulationData() {
        assertEquals(POPULATION_TITLE,
                opensourceTab.getPopulationTitle().textContent(),
                "Неверное название блока 'Население'");
        opensourceTab.checkPopulationQuestionTooltip(EXP_POPULATION_TOOLTIP);
        assertEquals(INCOME_PER_CAPITA,
                opensourceTab.getIncomePerCapita().textContent(),
                "Неверное название параметра 'Средний доход на душу населения'");
        assertEquals(INCOME_PER_CAPITA_VALUE,
                opensourceTab.getIncomePerCapitaValue().textContent(),
                "Неверное значение параметра 'Средний доход на душу населения'");
        assertEquals(POPULATION_DATA,
                opensourceTab.getPopulationData().textContent(),
                "Неверное название параметра 'Количество жителей'");
        assertEquals(POPULATION_DATA_VALUE,
                opensourceTab.getPopulationDataValue().textContent(),
                "Неверное значение параметра 'Количество жителей'");
    }

    @Test
    void checkHousingUnitCountData() {
        assertEquals(HOUSING_TITLE,
                opensourceTab.getHousingUnitCountTitle().textContent(),
                "Неверное название блока 'Количество жилых помещений'");
        opensourceTab.checkHousingUnitCountQuestionTooltip(EXP_HOUSING_TOOLTIP);
        assertEquals(HOUSING_TOTAL,
                opensourceTab.getHousingTotal().textContent(),
                "Неверное название параметра 'Все жилые помещения'");
        assertEquals(HOUSING_TOTAL_VALUE,
                opensourceTab.getHousingTotalValue().textContent(),
                "Неверное значение параметра 'Все жилые помещения'");
        assertEquals(HOUSING_IN_USE,
                opensourceTab.getHousingInUse().textContent(),
                "Неверное название параметра 'Жилые помещения, занимаемые владельцами'");
        assertEquals(HOUSING_IN_USE_VALUE,
                opensourceTab.getHousingInUseValue().textContent(),
                "Неверное значение параметра 'Жилые помещения, занимаемые владельцами'");
    }

    @Test
    void checkHousingDistribution() {
        assertEquals(HOUSING_DISTRIBUTION,
                opensourceTab.getHousingDistributionTitle().textContent(),
                "Неверное название блока 'Распределение жилых помещений'");
        Util.checkScreenshotForElement(
                opensourceTab.getHousingDistributionChart(),
                "actDistributionChart",
                "expDistributionChart",
                "checkDistributionChart",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
        opensourceTab.checkHousingDistributionQuestionTooltip(EXP_HOUSING_DISTRIBUTION_TOOLTIP);
    }

    @Test
    void checkHousingPriceData() {
        assertEquals(HOUSING_PRICE_TITLE,
                opensourceTab.getHousingPriceTitle().textContent(),
                "Неверное название блока 'Цена жилого помещения'");
        opensourceTab.checkHousingPriceQuestionTooltip(EXP_HOUSING_PRICE_TOOLTIP);
        assertEquals(HOUSING_AVERAGE_PRICE,
                opensourceTab.getHousingAveragePrice().textContent(),
                "Неверное название параметра 'Средняя'");
        assertEquals(HOUSING_AVERAGE_PRICE_VALUE,
                opensourceTab.getHousingAveragePriceValue().textContent(),
                "Неверное значение параметра 'Средняя'");
        assertEquals(HOUSING_MEDIAN_PRICE,
                opensourceTab.getHousingMedianPrice().textContent(),
                "Неверное название параметра 'Медиана'");
        assertEquals(HOUSING_MEDIAN_PRICE_VALUE,
                opensourceTab.getHousingMedianPriceValue().textContent(),
                "Неверное значение параметра 'Медиана'");
    }
}
