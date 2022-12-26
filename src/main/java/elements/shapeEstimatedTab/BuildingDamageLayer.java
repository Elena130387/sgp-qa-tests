package elements.shapeEstimatedTab;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class BuildingDamageLayer {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_BUILDING_DAMAGE_TITLE = "[data-cy=building-damage-title]";
    private static final String SELECTOR_BUILDING_DAMAGE_PERCENT = ".css-fc1qbd";
    private static final String SELECTOR_DESTROYED_BUILDINGS = "[data-cy=destroyed-buildings]";
    private static final String SELECTOR_DESTROYED_BUILDINGS_VALUE = "[data-cy=destroyed-buildings-value]";
    private static final String SELECTOR_MAJOR_DAMAGE_BUILDINGS = "[data-cy=major-damage-buildings]";
    private static final String SELECTOR_MAJOR_DAMAGE_BUILDINGS_VALUE = "[data-cy=major-damage-buildings-value]";
    private static final String SELECTOR_MINOR_DAMAGE_BUILDINGS = "[data-cy=minor-damage-buildings]";
    private static final String SELECTOR_MINOR_DAMAGE_BUILDINGS_VALUE = "[data-cy=minor-damage-buildings-value]";
    private static final String SELECTOR_NO_DAMAGE_BUILDINGS = "[data-cy=no-damage-buildings]";
    private static final String SELECTOR_NO_DAMAGE_BUILDINGS_VALUE = "[data-cy=no-damage-buildings-value]";
    private static final String SELECTOR_TOTAL_BUILDING_DAMAGE = "[data-cy=building-damage-total]";
    private static final String SELECTOR_TOTAL_BUILDING_DAMAGE_VALUE = "[data-cy=building-damage-total-value]";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator buildingDamageTitle;
    private final Locator buildingDamagePercent;
    private final Locator destroyedBuildings;
    private final Locator destroyedBuildingsValue;
    private final Locator majorDamageBuildings;
    private final Locator majorDamageBuildingsValue;
    private final Locator minorDamageBuildings;
    private final Locator minorDamageBuildingsValue;
    private final Locator noDamageBuildings;
    private final Locator noDamageBuildingsValue;
    private final Locator totalDamageBuildings;
    private final Locator totalDamageBuildingsValue;

    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getBuildingDamageTitle() {
        return buildingDamageTitle;
    }

    public Locator getBuildingDamagePercent() {
        return buildingDamagePercent;
    }

    public Locator getDestroyedBuildings() {
        return destroyedBuildings;
    }

    public Locator getDestroyedBuildingsValue() {
        return destroyedBuildingsValue;
    }

    public Locator getMajorDamageBuildings() {
        return majorDamageBuildings;
    }

    public Locator getMajorDamageBuildingsValue() {
        return majorDamageBuildingsValue;
    }

    public Locator getMinorDamageBuildings() {
        return minorDamageBuildings;
    }

    public Locator getMinorDamageBuildingsValue() {
        return minorDamageBuildingsValue;
    }

    public Locator getNoDamageBuildings() {
        return noDamageBuildings;
    }

    public Locator getNoDamageBuildingsValue() {
        return noDamageBuildingsValue;
    }

    public Locator getTotalDamageBuildings() {
        return totalDamageBuildings;
    }

    public Locator getTotalDamageBuildingsValue() {
        return totalDamageBuildingsValue;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public BuildingDamageLayer() {
        buildingDamageTitle = page.locator(SELECTOR_BUILDING_DAMAGE_TITLE);
        buildingDamagePercent = page.locator(SELECTOR_BUILDING_DAMAGE_PERCENT);
        destroyedBuildings = page.locator(SELECTOR_DESTROYED_BUILDINGS);
        destroyedBuildingsValue = page.locator(SELECTOR_DESTROYED_BUILDINGS_VALUE);
        majorDamageBuildings = page.locator(SELECTOR_MAJOR_DAMAGE_BUILDINGS);
        majorDamageBuildingsValue = page.locator(SELECTOR_MAJOR_DAMAGE_BUILDINGS_VALUE);
        minorDamageBuildings = page.locator(SELECTOR_MINOR_DAMAGE_BUILDINGS);
        minorDamageBuildingsValue = page.locator(SELECTOR_MINOR_DAMAGE_BUILDINGS_VALUE);
        noDamageBuildings = page.locator(SELECTOR_NO_DAMAGE_BUILDINGS);
        noDamageBuildingsValue = page.locator(SELECTOR_NO_DAMAGE_BUILDINGS_VALUE);
        totalDamageBuildings = page.locator(SELECTOR_TOTAL_BUILDING_DAMAGE);
        totalDamageBuildingsValue = page.locator(SELECTOR_TOTAL_BUILDING_DAMAGE_VALUE);
    }
}
