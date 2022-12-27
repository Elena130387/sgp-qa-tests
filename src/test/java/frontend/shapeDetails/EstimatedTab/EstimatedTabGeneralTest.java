package frontend.shapeDetails.EstimatedTab;

import elements.shapeEstimatedTab.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DetailedShapePage;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(JunitExtension.class)
public class EstimatedTabGeneralTest {
    private static final int SHAPE_ID = 1124;
    private final String ESTIMATED_OBJECTS_LAYER = "Объекты";
    private final String ESTIMATED_BUILDINGS = "Здания";
    private final String ESTIMATED_BUILDING_HEIGHT = "Высота зданий";
    private final String ESTIMATED_BUILDING_DAMAGE = "Повреждения зданий";
    private final String ESTIMATED_POPULATION = "Население";
    private final String ESTIMATED_LAND_USE = "Тип землепользования, %";
    private final String EXP_SCREENSHOTS_TEST_CLASS_DIR = "ShapeEstimatedTabGeneralTest/";
    private final EstimatedTabGeneral estimatedTabGeneral = new EstimatedTabGeneral();
    private final ObjectsLayer objectsLayer = new ObjectsLayer();
    private final BuildingsLayer buildingsLayer = new BuildingsLayer();
    private final BuildingHeightLayer buildingHeightLayer = new BuildingHeightLayer();
    private final BuildingDamageLayer buildingDamageLayer = new BuildingDamageLayer();
    private final PopulationLayer populationLayer = new PopulationLayer();
    private final LandUseLayer landuseLayer = new LandUseLayer();
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
                EXP_SCREENSHOTS_TEST_CLASS_DIR);
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
                EXP_SCREENSHOTS_TEST_CLASS_DIR);
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
                EXP_SCREENSHOTS_TEST_CLASS_DIR);
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
                EXP_SCREENSHOTS_TEST_CLASS_DIR);
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
                EXP_SCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkLandUseLayerTitleAndIcon() {
        assertEquals(ESTIMATED_LAND_USE,
                landuseLayer.getLandUseTitle().textContent(),
                "Неверное имя слоя 'Тип землепользования, %'");
        Util.checkScreenshotForElement(
                estimatedTabGeneral.getLandUseLayerIcon(),
                "actLandUseIcon",
                "expLandUseIcon",
                "checkLandUseIcon",
                EXP_SCREENSHOTS_TEST_CLASS_DIR);
    }
}
