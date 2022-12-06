package elements.ShapeEstimatedTab;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class EstimatedTabGeneral {


    //<editor-fold desc="Selectors">
    private static final String SELECTOR_OBJECTS_LAYER = "[data-cy=objects]";
    private static final String SELECTOR_BUILDINGS_LAYER = "[data-cy=buildings]";
    private static final String SELECTOR_BUILDING_HEIGHT_LAYER = "[data-cy=building-height]";
    private static final String SELECTOR_BUILDING_DAMAGE_LAYER = "[data-cy=building-damage]";
    private static final String SELECTOR_POPULATION_LAYER = "[data-cy=population-layer]";
    private static final String SELECTOR_LANDUSE_LAYER = "[data-cy=land-use]";
    private static final String SELECTOR_LAYER_ICONS = ".css-4bw3ev";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator objectsLayer;
    private final Locator buildingsLayer;
    private final Locator buildingHeightLayer;
    private final Locator buildingDamageLayer;
    private final Locator populationLayer;
    private final Locator landuseLayer;
    private final Locator layerIcons;

    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getObjectsLayer() {
        return objectsLayer;
    }

    public Locator getBuildingsLayer() {
        return buildingsLayer;
    }

    public Locator getBuildingHeightLayer() {
        return buildingHeightLayer;
    }

    public Locator getBuildingDamageLayer() {
        return buildingDamageLayer;
    }

    public Locator getPopulationLayer() {
        return populationLayer;
    }

    public Locator getLanduseLayer() {
        return landuseLayer;
    }

    public Locator getLayerIcons() {
        return layerIcons;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public EstimatedTabGeneral() {
        objectsLayer = page.locator(SELECTOR_OBJECTS_LAYER);
        buildingsLayer = page.locator(SELECTOR_BUILDINGS_LAYER);
        buildingHeightLayer = page.locator(SELECTOR_BUILDING_HEIGHT_LAYER);
        buildingDamageLayer = page.locator(SELECTOR_BUILDING_DAMAGE_LAYER);
        populationLayer = page.locator(SELECTOR_POPULATION_LAYER);
        landuseLayer = page.locator(SELECTOR_LANDUSE_LAYER);
        layerIcons = page.locator(SELECTOR_LAYER_ICONS);
    }
}
