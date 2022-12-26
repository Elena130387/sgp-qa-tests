package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

import static util.Util.assertTooltip;

public class OpensourceTab {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_CENSUS_DATA = "[data-cy=open-source-tab-census-data]";
    private static final String SELECTOR_GEOGRAPHIC_DETAILS = "[data-cy=open-source-tab-info-outline]";
    private static final String SELECTOR_GEOGRAPHIC_DETAILS_INFO_ICON = "[data-cy=open-source-tab-info-outline-icon]";
    private static final String SELECTOR_GEOGRAPHIC_DETAILS_INFO_TOOLTIP = "[data-tooltip=county-name]";
    private static final String SELECTOR_POPULATION_SECTION = "[data-cy=populationData]";
    private static final String SELECTOR_POPULATION_TITLE = "[data-cy=population-title]";
    private static final String SELECTOR_POPULATION_QUESTION_TOOLTIP = "[data-tooltip=population-info]";
    private static final String SELECTOR_INCOME_PER_CAPITA = "[data-cy=income-per-capita]";
    private static final String SELECTOR_INCOME_PER_CAPITA_VALUE = "[data-cy=income-per-capita-value]";
    private static final String SELECTOR_POPULATION_DATA = "[data-cy=population-data]";
    private static final String SELECTOR_POPULATION_DATA_VALUE = "[data-cy=population-data-value]";
    private static final String SELECTOR_HOUSING_UNIT_COUNT_SECTION = "[data-cy=unitCount]";
    private static final String SELECTOR_HOUSING_UNIT_COUNT_TITLE = "[data-cy=housing-units-title]";
    private static final String SELECTOR_HOUSING_UNIT_COUNT_QUESTION_TOOLTIP = "[data-tooltip=housing-units-info]";
    private static final String SELECTOR_HOUSING_TOTAL = "[data-cy=housing-units-total]";
    private static final String SELECTOR_HOUSING_TOTAL_VALUE = "[data-cy=housing-units-total-value]";
    private static final String SELECTOR_HOUSING_IN_USE = "[data-cy=housing-units-in-use]";
    private static final String SELECTOR_HOUSING_IN_USE_VALUE = "[data-cy=housing-units-in-use-value]";
    private static final String SELECTOR_HOUSING_DISTRIBUTION_SECTION = "[data-cy=distribution]";
    private static final String SELECTOR_HOUSING_DISTRIBUTION_TITLE = "[data-cy=distribution-title]";
    private static final String SELECTOR_HOUSING_DISTRIBUTION_QUESTION_TOOLTIP = "[data-tooltip=distribution-info]";
    private static final String SELECTOR_HOUSING_DISTRIBUTION_CHART = "[data-cy=distribution-chart]";
    private static final String SELECTOR_HOUSING_PRICE_SECTION = "[data-cy=price]";
    private static final String SELECTOR_HOUSING_PRICE_TITLE = "[data-cy=unit-price-title]";
    private static final String SELECTOR_HOUSING_PRICE_QUESTION_TOOLTIP = "[data-tooltip=unit-price-info]";
    private static final String SELECTOR_HOUSING_AVERAGE_PRICE = "[data-cy=average-unit-price]";
    private static final String SELECTOR_HOUSING_AVERAGE_PRICE_VALUE = "[data-cy=average-unit-price-value]";
    private static final String SELECTOR_HOUSING_MEDIAN_PRICE = "[data-cy=median-unit-price]";
    private static final String SELECTOR_HOUSING_MEDIAN_PRICE_VALUE = "[data-cy=median-unit-price-value]";
    private static final String SELECTOR_QUESTION_ICONS = ".css-1cndm63";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator censusData;
    private final Locator geographicDetails;
    private final Locator geographicDetailsInfoIcon;
    private final Locator geographicDetailsInfoTooltip;
    private final Locator populationSection;
    private final Locator populationTitle;
    private final Locator populationQuestionIcon;
    private final Locator populationQuestionTooltip;
    private final Locator incomePerCapita;
    private final Locator incomePerCapitaValue;
    private final Locator populationData;
    private final Locator populationDataValue;
    private final Locator housingUnitCountSection;
    private final Locator housingUnitCountTitle;
    private final Locator housingUnitCountQuestionIcon;
    private final Locator housingUnitCountQuestionTooltip;
    private final Locator housingTotal;
    private final Locator housingTotalValue;
    private final Locator housingInUse;
    private final Locator housingInUseValue;
    private final Locator housingDistributionSection;
    private final Locator housingDistributionTitle;
    private final Locator housingDistributionQuestionIcon;
    private final Locator housingDistributionQuestionTooltip;
    private final Locator housingDistributionChart;
    private final Locator housingPriceSection;
    private final Locator housingPriceTitle;
    private final Locator housingPriceQuestionIcon;
    private final Locator housingPriceQuestionTooltip;
    private final Locator housingAveragePrice;
    private final Locator housingAveragePriceValue;
    private final Locator housingMedianPrice;
    private final Locator housingMedianPriceValue;


    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getCensusData() {
        return censusData;
    }

    public Locator getGeographicDetails() {
        return geographicDetails;
    }

    public Locator getGeographicDetailsInfoIcon() {
        return geographicDetailsInfoIcon;
    }

    public Locator getGeographicDetailsInfoTooltip() {
        return geographicDetailsInfoTooltip;
    }

    public Locator getPopulationSection() {
        return populationSection;
    }

    public Locator getPopulationTitle() {
        return populationTitle;
    }

    public Locator getPopulationQuestionIcon() {
        return populationQuestionIcon;
    }

    public Locator getPopulationQuestionTooltip() {
        return populationQuestionTooltip;
    }

    public Locator getIncomePerCapita() {
        return incomePerCapita;
    }

    public Locator getIncomePerCapitaValue() {
        return incomePerCapitaValue;
    }

    public Locator getPopulationData() {
        return populationData;
    }

    public Locator getPopulationDataValue() {
        return populationDataValue;
    }

    public Locator getHousingUnitCountSection() {
        return housingUnitCountSection;
    }

    public Locator getHousingUnitCountTitle() {
        return housingUnitCountTitle;
    }

    public Locator getHousingUnitCountQuestionIcon() {
        return housingUnitCountQuestionIcon;
    }

    public Locator getHousingUnitCountQuestionTooltip() {
        return housingUnitCountQuestionTooltip;
    }

    public Locator getHousingTotal() {
        return housingTotal;
    }

    public Locator getHousingTotalValue() {
        return housingTotalValue;
    }

    public Locator getHousingInUse() {
        return housingInUse;
    }

    public Locator getHousingInUseValue() {
        return housingInUseValue;
    }

    public Locator getHousingDistributionSection() {
        return housingDistributionSection;
    }

    public Locator getHousingDistributionTitle() {
        return housingDistributionTitle;
    }

    public Locator getHousingDistributionQuestionIcon() {
        return housingDistributionQuestionIcon;
    }

    public Locator getHousingDistributionQuestionTooltip() {
        return housingDistributionQuestionTooltip;
    }

    public Locator getHousingDistributionChart() {
        return housingDistributionChart;
    }

    public Locator getHousingPriceSection() {
        return housingPriceSection;
    }

    public Locator getHousingPriceTitle() {
        return housingPriceTitle;
    }

    public Locator getHousingPriceQuestionIcon() {
        return housingPriceQuestionIcon;
    }

    public Locator getHousingPriceQuestionTooltip() {
        return housingPriceQuestionTooltip;
    }

    public Locator getHousingAveragePrice() {
        return housingAveragePrice;
    }

    public Locator getHousingAveragePriceValue() {
        return housingAveragePriceValue;
    }

    public Locator getHousingMedianPrice() {
        return housingMedianPrice;
    }

    public Locator getHousingMedianPriceValue() {
        return housingMedianPriceValue;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public OpensourceTab() {
        censusData = page.locator(SELECTOR_CENSUS_DATA);
        geographicDetails = page.locator(SELECTOR_GEOGRAPHIC_DETAILS);
        geographicDetailsInfoIcon = page.locator(SELECTOR_GEOGRAPHIC_DETAILS_INFO_ICON);
        geographicDetailsInfoTooltip = page.locator(SELECTOR_GEOGRAPHIC_DETAILS_INFO_TOOLTIP);

        populationSection = page.locator(SELECTOR_POPULATION_SECTION);
        populationTitle = page.locator(SELECTOR_POPULATION_TITLE);
        populationQuestionIcon = populationSection.locator(SELECTOR_QUESTION_ICONS);
        populationQuestionTooltip = page.locator(SELECTOR_POPULATION_QUESTION_TOOLTIP);
        incomePerCapita = page.locator(SELECTOR_INCOME_PER_CAPITA);
        incomePerCapitaValue = page.locator(SELECTOR_INCOME_PER_CAPITA_VALUE);
        populationData = page.locator(SELECTOR_POPULATION_DATA);
        populationDataValue = page.locator(SELECTOR_POPULATION_DATA_VALUE);

        housingUnitCountSection = page.locator(SELECTOR_HOUSING_UNIT_COUNT_SECTION);
        housingUnitCountTitle = page.locator(SELECTOR_HOUSING_UNIT_COUNT_TITLE);
        housingUnitCountQuestionIcon = housingUnitCountSection.locator(SELECTOR_QUESTION_ICONS);
        housingUnitCountQuestionTooltip = page.locator(SELECTOR_HOUSING_UNIT_COUNT_QUESTION_TOOLTIP);
        housingTotal = page.locator(SELECTOR_HOUSING_TOTAL);
        housingTotalValue = page.locator(SELECTOR_HOUSING_TOTAL_VALUE);
        housingInUse = page.locator(SELECTOR_HOUSING_IN_USE);
        housingInUseValue = page.locator(SELECTOR_HOUSING_IN_USE_VALUE);

        housingDistributionSection = page.locator(SELECTOR_HOUSING_DISTRIBUTION_SECTION);
        housingDistributionTitle = page.locator(SELECTOR_HOUSING_DISTRIBUTION_TITLE);
        housingDistributionQuestionIcon = housingDistributionSection.locator(SELECTOR_QUESTION_ICONS);
        housingDistributionQuestionTooltip = page.locator(SELECTOR_HOUSING_DISTRIBUTION_QUESTION_TOOLTIP);
        housingDistributionChart = page.locator(SELECTOR_HOUSING_DISTRIBUTION_CHART);

        housingPriceSection = page.locator(SELECTOR_HOUSING_PRICE_SECTION);
        housingPriceTitle = page.locator(SELECTOR_HOUSING_PRICE_TITLE);
        housingPriceQuestionIcon = housingPriceSection.locator(SELECTOR_QUESTION_ICONS);
        housingPriceQuestionTooltip = page.locator(SELECTOR_HOUSING_PRICE_QUESTION_TOOLTIP);
        housingAveragePrice = page.locator(SELECTOR_HOUSING_AVERAGE_PRICE);
        housingAveragePriceValue = page.locator(SELECTOR_HOUSING_AVERAGE_PRICE_VALUE);
        housingMedianPrice = page.locator(SELECTOR_HOUSING_MEDIAN_PRICE);
        housingMedianPriceValue = page.locator(SELECTOR_HOUSING_MEDIAN_PRICE_VALUE);
    }

    public void checkGeographicDetailsInfoTooltip(String expText) {
        assertTooltip(geographicDetailsInfoIcon, geographicDetailsInfoTooltip, expText);
    }

    public void checkPopulationQuestionTooltip(String expText) {
        assertTooltip(populationQuestionIcon, populationQuestionTooltip, expText);
    }

    public void checkHousingUnitCountQuestionTooltip(String expText) {
        assertTooltip(housingUnitCountQuestionIcon, housingUnitCountQuestionTooltip, expText);
    }

    public void checkHousingDistributionQuestionTooltip(String expText) {
        assertTooltip(housingDistributionQuestionIcon, housingDistributionQuestionTooltip, expText);
    }

    public void checkHousingPriceQuestionTooltip(String expText) {
        assertTooltip(housingPriceQuestionIcon, housingPriceQuestionTooltip, expText);
    }
}
