package frontend.shapeDetails.EstimatedTab;

import elements.ShapeEstimatedTab.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(JunitExtension.class)
public class ShapeEstimatedTabGeneralTest {
    private static final int SHAPE_ID = 1124;
    private final String ESTIMATED_OBJECTS_LAYER = "Объекты";
    private final String ESTIMATED_BUILDINGS = "Здания";
    private final String ESTIMATED_BUILDING_HEIGHT = "Высота зданий";
    private final String ESTIMATED_BUILDING_DAMAGE = "Повреждения зданий";
    private final String ESTIMATED_POPULATION = "Население";
    private final String ESTIMATED_LANDUSE = "Тип землепользования, %";
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "ShapeEstimatedTabGeneralTest/";
    private final EstimatedTabGeneral estimatedTabGeneral = new EstimatedTabGeneral();
    private final ObjectsLayer objectsLayer = new ObjectsLayer();
    private final BuildingsLayer buildingsLayer = new BuildingsLayer();
    private final BuildingHeightLayer buildingHeightLayer = new BuildingHeightLayer();
    private final BuildingDamageLayer buildingDamageLayer = new BuildingDamageLayer();
    private final PopulationLayer populationLayer = new PopulationLayer();
    private final LanduseLayer landuseLayer = new LanduseLayer();
    static DetailedShapePage detailedShapePage;

    @BeforeAll
    static void openTestPage() {
        detailedShapePage = new DetailedShapePage().openPageWithEstimatedTab(SHAPE_ID);
    }

    @Test
    void checkObjectsLayerTitleAndIcon() {
        assertEquals(ESTIMATED_OBJECTS_LAYER,
                objectsLayer.getObjectsTitle().textContent(),
                "Неверное имя слоя 'Объекты'");
        Util.checkScreenshotForElement(
                estimatedTabGeneral.getObjectsLayerIcon(),
                "actObjectsIcon",
                "expObjectsIcon",
                "checkObjectsIcon",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkBuildingsLayerTitleAndIcon() {
        assertEquals(ESTIMATED_BUILDINGS,
                buildingsLayer.getBuildingsTitle().textContent(),
                "Неверное имя слоя 'Здания'");
        Util.checkScreenshotForElement(
                estimatedTabGeneral.getBuildingsLayerIcon(),
                "actBuildingsIcon",
                "expBuildingsIcon",
                "checkBuildingsIcon",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkBuildingHeightLayerTitleAndIcon() {
        assertEquals(ESTIMATED_BUILDING_HEIGHT,
                buildingHeightLayer.getBuildingHeightTitle().textContent(),
                "Неверное имя слоя 'Высота зданий'");
        Util.checkScreenshotForElement(
                estimatedTabGeneral.getBuildingHeightLayerIcon(),
                "actBuildingHeightIcon",
                "expBuildingsIcon",
                "checkBuildingHeightIcon",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkBuildingDamageLayerTitleIconAndCheckbox() {
        assertEquals(ESTIMATED_BUILDING_DAMAGE,
                buildingDamageLayer.getBuildingDamageTitle().textContent(),
                "Неверное имя слоя 'Повреждения зданий'");
        assertThat(estimatedTabGeneral.getBuildingDamageLayerCheckbox()).isChecked();
        assertThat(estimatedTabGeneral.getBuildingDamageLayerCheckbox()).isDisabled();
        Util.checkScreenshotForElement(
                estimatedTabGeneral.getBuildingHeightLayerIcon(),
                "actBuildingDamageIcon",
                "expBuildingsIcon",
                "checkBuildingDamageIcon",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkPopulationLayerTitleIconAndCheckbox() {
        assertEquals(ESTIMATED_POPULATION,
                populationLayer.getPopulationTitle().textContent(),
                "Неверное имя слоя 'Население'");
        assertFalse(estimatedTabGeneral.getPopulationLayerCheckbox().isChecked());
        assertThat(estimatedTabGeneral.getPopulationLayerCheckbox()).isEnabled();
        Util.checkScreenshotForElement(
                estimatedTabGeneral.getPopulationLayerIcon(),
                "actPopulationIcon",
                "expPopulationIcon",
                "checkPopulationIcon",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkLanduseLayerTitleAndIcon() {
        assertEquals(ESTIMATED_LANDUSE,
                landuseLayer.getLanduseTitle().textContent(),
                "Неверное имя слоя 'Тип землепользования, %'");
        Util.checkScreenshotForElement(
                estimatedTabGeneral.getLanduseLayerIcon(),
                "actLanduseIcon",
                "expLanduseIcon",
                "checkLanduseIcon",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
