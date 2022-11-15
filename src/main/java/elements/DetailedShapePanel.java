package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class DetailedShapePanel {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_BUTTON_BACK = "[data-cy=Back]";
    private static final String SELECTOR_SHAPE_NAME = ".css-h4f2wu";
    private static final String SELECTOR_SHAPE_OVERALL = "[data-cy=overall]";
    private static final String SELECTOR_ESTIMATED_TAB = "[data-cy=estimated]";
    private static final String SELECTOR_BUILDING_DAMAGE_LAYER = "[data-cy=BuildingDamage]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator buttonBack;
    private final Locator shapeName;
    private final Locator shapeOverall;
    private final Locator estimatedTab;
    private final Locator buildingDamageLayer;

    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getButtonBack() {
        return buttonBack;
    }

    public Locator getShapeName() {
        return shapeName;
    }

    public Locator getShapeOverall() {
        return shapeOverall;
    }

    public Locator getEstimatedTab() {
        return estimatedTab;
    }

    public Locator buildingDamageLayer() {
        return buildingDamageLayer;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public DetailedShapePanel() {
        buttonBack = page.locator(SELECTOR_BUTTON_BACK);
        shapeName = page.locator(SELECTOR_SHAPE_NAME);
        shapeOverall = page.locator(SELECTOR_SHAPE_OVERALL);
        estimatedTab = page.locator(SELECTOR_ESTIMATED_TAB);
        buildingDamageLayer = page.locator(SELECTOR_BUILDING_DAMAGE_LAYER);
    }


}
