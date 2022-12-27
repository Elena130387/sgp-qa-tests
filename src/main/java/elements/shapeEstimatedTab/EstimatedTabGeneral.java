package elements.shapeEstimatedTab;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class EstimatedTabGeneral {


    //<editor-fold desc="Selectors">
    private static final String SELECTOR_OBJECTS_LAYER = "[data-cy=objects]";
    private static final String SELECTOR_BUILDINGS_LAYER = "[data-cy=buildings]";
    private static final String SELECTOR_BUILDING_HEIGHT_LAYER = "[data-cy=buildingHeight]";
    private static final String SELECTOR_BUILDING_DAMAGE_LAYER = "[data-cy=BuildingDamage]";
    private static final String SELECTOR_POPULATION_LAYER = "[data-cy=population]";
    private static final String SELECTOR_LAND_USE_LAYER = "[data-cy=landuse]";
    private static final String SELECTOR_LAYER_ICONS = ".css-4bw3ev";
    private static final String SELECTOR_LAYER_CHECKBOX = ".css-e2du76";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator objectsLayer;
    private final Locator buildingsLayer;
    private final Locator buildingHeightLayer;
    private final Locator buildingDamageLayer;
    private final Locator populationLayer;
    private final Locator landUseLayer;
    private final Locator objectsLayerIcon;
    private final Locator buildingsLayerIcon;
    private final Locator buildingHeightLayerIcon;
    private final Locator buildingDamageLayerIcon;
    private final Locator populationLayerIcon;
    private final Locator landUseLayerIcon;
    private final Locator buildingDamageLayerCheckbox;
    private final Locator populationLayerCheckbox;

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

    public Locator getLandUseLayer() {
        return landUseLayer;
    }

    public Locator getObjectsLayerIcon() {
        return objectsLayerIcon;
    }

    public Locator getBuildingsLayerIcon() {
        return buildingsLayerIcon;
    }

    public Locator getBuildingHeightLayerIcon() {
        return buildingHeightLayerIcon;
    }

    public Locator getBuildingDamageLayerIcon() {
        return buildingDamageLayerIcon;
    }

    public Locator getPopulationLayerIcon() {
        return populationLayerIcon;
    }

    public Locator getLandUseLayerIcon() {
        return landUseLayerIcon;
    }

    public Locator getBuildingDamageLayerCheckbox() {
        return buildingDamageLayerCheckbox;
    }

    public Locator getPopulationLayerCheckbox() {
        return populationLayerCheckbox;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public EstimatedTabGeneral() {
        objectsLayer = page.locator(SELECTOR_OBJECTS_LAYER);
        buildingsLayer = page.locator(SELECTOR_BUILDINGS_LAYER);
        buildingHeightLayer = page.locator(SELECTOR_BUILDING_HEIGHT_LAYER);
        buildingDamageLayer = page.locator(SELECTOR_BUILDING_DAMAGE_LAYER);
        populationLayer = page.locator(SELECTOR_POPULATION_LAYER);
        landUseLayer = page.locator(SELECTOR_LAND_USE_LAYER);
        objectsLayerIcon = objectsLayer.locator(SELECTOR_LAYER_ICONS);
        buildingsLayerIcon = buildingsLayer.locator(SELECTOR_LAYER_ICONS);
        buildingHeightLayerIcon = buildingHeightLayer.locator(SELECTOR_LAYER_ICONS);
        buildingDamageLayerIcon = buildingDamageLayer.locator(SELECTOR_LAYER_ICONS);
        populationLayerIcon = populationLayer.locator(SELECTOR_LAYER_ICONS);
        landUseLayerIcon = landUseLayer.locator(SELECTOR_LAYER_ICONS);
        buildingDamageLayerCheckbox = buildingDamageLayer.locator(SELECTOR_LAYER_CHECKBOX);
        populationLayerCheckbox = populationLayer.locator(SELECTOR_LAYER_CHECKBOX);
    }
}
