package elements.ShapeEstimatedTab;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class BuildingHeightLayer {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_BUILDING_HEIGHT_TITLE = "[data-cy=building-height-title]";
    private static final String SELECTOR_BUILDINGS_3_LESS_HEIGHT = "[data-cy=buildings-three-less-height]";
    private static final String SELECTOR_BUILDINGS_3_LESS_HEIGHT_VALUE = "[data-cy=buildings-three-less-height-value]";
    private static final String SELECTOR_BUILDINGS_3_TO_8_HEIGHT = "[data-cy=buildings-three-to-eight-height]";
    private static final String SELECTOR_BUILDINGS_3_TO_8_HEIGHT_VALUE = "[data-cy=buildings-three-to-eight-height-value]";
    private static final String SELECTOR_BUILDINGS_8_MORE_HEIGHT = "[data-cy=buildings-eight-more-height]";
    private static final String SELECTOR_BUILDINGS_8_MORE_HEIGHT_VALUE = "[data-cy=buildings-eight-more-height-value]";
    private static final String SELECTOR_AVERAGE_HEIGHT_TYPE = "[data-cy=average-building-height]";
    private static final String SELECTOR_AVERAGE_HEIGHT_VALUE = "[data-cy=average-building-height-value]";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator buildingHeightTitle;
    private final Locator buildings3LessHeight;
    private final Locator buildings3LessHeightValue;
    private final Locator buildings3To8Height;
    private final Locator buildings3To8HeightValue;
    private final Locator buildings8MoreHeight;
    private final Locator buildings8MoreHeightValue;
    private final Locator averageHeightType;
    private final Locator averageHeightValue;

    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getBuildingHeightTitle() {
        return buildingHeightTitle;
    }

    public Locator getBuildings3LessHeight() {
        return buildings3LessHeight;
    }

    public Locator getBuildings3LessHeightValue() {
        return buildings3LessHeightValue;
    }
    public Locator getBuildings3To8Height() {
        return buildings3To8Height;
    }

    public Locator getBuildings3To8HeightValue() {
        return buildings3To8HeightValue;
    }
    public Locator getBuildings8MoreHeight() {
        return buildings8MoreHeight;
    }

    public Locator getBuildings8MoreHeightValue() {
        return buildings8MoreHeightValue;
    }

    public Locator getAverageHeightType() {
        return averageHeightType;
    }

    public Locator getAverageHeightValue() {
        return averageHeightValue;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public BuildingHeightLayer() {
        buildingHeightTitle = page.locator(SELECTOR_BUILDING_HEIGHT_TITLE);
        buildings3LessHeight = page.locator(SELECTOR_BUILDINGS_3_LESS_HEIGHT);
        buildings3LessHeightValue = page.locator(SELECTOR_BUILDINGS_3_LESS_HEIGHT_VALUE);
        buildings3To8Height = page.locator(SELECTOR_BUILDINGS_3_TO_8_HEIGHT);
        buildings3To8HeightValue = page.locator(SELECTOR_BUILDINGS_3_TO_8_HEIGHT_VALUE);
        buildings8MoreHeight = page.locator(SELECTOR_BUILDINGS_8_MORE_HEIGHT);
        buildings8MoreHeightValue = page.locator(SELECTOR_BUILDINGS_8_MORE_HEIGHT_VALUE);
        averageHeightType = page.locator(SELECTOR_AVERAGE_HEIGHT_TYPE);
        averageHeightValue = page.locator(SELECTOR_AVERAGE_HEIGHT_VALUE);
    }
}
