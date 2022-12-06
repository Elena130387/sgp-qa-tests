package elements.ShapeEstimatedTab;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class EstimatedTabBuildings {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_BUILDINGS = "[data-cy=buildings-title]";
    private static final String SELECTOR_BUILDING_COUNT_TITLE = "[data-cy=building-count]";
    private static final String SELECTOR_BUILDING_COUNT_VALUE = "[data-cy=building-count-value]";
    private static final String SELECTOR_AVERAGE_HEIGHT_TYPE = "[data-cy=average-height]";
    private static final String SELECTOR_AVERAGE_HEIGHT_VALUE = "[data-cy=average-height-value]";
    private static final String SELECTOR_AVERAGE_COVERAGE_TYPE = "[data-cy=average-area-coverage]";
    private static final String SELECTOR_AVERAGE_COVERAGE_VALUE = "[data-cy=average-area-coverage-value]";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator buildings;
    private final Locator buildingCountTitle;
    private final Locator buildingCountValue;
    private final Locator averageHeightType;
    private final Locator averageHeightValue;
    private final Locator averageCoverageType;
    private final Locator averageCoverageValue;

    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getBuildings() {
        return buildings;
    }

    public Locator getBuildingCountTitle() {
        return buildingCountTitle;
    }

    public Locator getBuildingCountValue() {
        return buildingCountValue;
    }

    public Locator getAverageHeightType() {
        return averageHeightType;
    }

    public Locator getAverageHeightValue() {
        return averageHeightValue;
    }

    public Locator getAverageCoverageType() {
        return averageCoverageType;
    }

    public Locator getAverageCoverageValue() {
        return averageCoverageValue;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public EstimatedTabBuildings() {
        buildings = page.locator(SELECTOR_BUILDINGS);
        buildingCountTitle = page.locator(SELECTOR_BUILDING_COUNT_TITLE);
        buildingCountValue = page.locator(SELECTOR_BUILDING_COUNT_VALUE);
        averageHeightType = page.locator(SELECTOR_AVERAGE_HEIGHT_TYPE);
        averageHeightValue = page.locator(SELECTOR_AVERAGE_HEIGHT_VALUE);
        averageCoverageType = page.locator(SELECTOR_AVERAGE_COVERAGE_TYPE);
        averageCoverageValue = page.locator(SELECTOR_AVERAGE_COVERAGE_VALUE);
    }
}
