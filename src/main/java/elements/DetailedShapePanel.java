package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class DetailedShapePanel {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_BUTTON_BACK = "[data-cy=Back]";
    private static final String SELECTOR_SHAPE_NAME = "[data-cy=shape-name-header]";
    private static final String SELECTOR_SHAPE_COUNT_NUM = "[data-cy=shape-overall-polygon-count-number]";
    private static final String SELECTOR_SHAPE_COUNT = "[data-cy=shape-overall-polygon-count]";
    private static final String SELECTOR_SHAPE_DATE = "[data-cy=shape-overall-created-at-date]";
    private static final String SELECTOR_SHAPE_CREATED = "[data-cy=shape-overall-created-at]";
    private static final String SELECTOR_SHAPE_SIZE_NUM = "[data-cy=shape-overall-area-size-number]";
    private static final String SELECTOR_SHAPE_SIZE = "[data-cy=shape-overall-area-size]";
    private static final String SELECTOR_BUILDING_DAMAGE_LAYER = "[data-cy=BuildingDamage]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator buttonBack;
    private final Locator shapeName;
    private final Locator shapeCountNum;
    private final Locator shapeCount;
    private final Locator shapeDate;
    private final Locator shapeCreated;
    private final Locator shapeSizeNum;
    private final Locator shapeSize;

    private final Locator buildingDamageLayer;

    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getButtonBack() {
        return buttonBack;
    }

    public Locator getShapeName() {
        return shapeName;
    }

    public Locator getShapeCountNum() {
        return shapeCountNum;
    }

    public Locator getShapeCount() {
        return shapeCount;
    }

    public Locator getShapeDate() {
        return shapeDate;
    }

    public Locator getShapeCreated() {
        return shapeCreated;
    }

    public Locator getShapeSizeNum() {
        return shapeSizeNum;
    }

    public Locator getShapeSize() {
        return shapeSize;
    }

    public Locator buildingDamageLayer() {
        return buildingDamageLayer;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public DetailedShapePanel() {
        buttonBack = page.locator(SELECTOR_BUTTON_BACK);
        shapeName = page.locator(SELECTOR_SHAPE_NAME);
        shapeCountNum = page.locator(SELECTOR_SHAPE_COUNT_NUM);
        shapeCount = page.locator(SELECTOR_SHAPE_COUNT);
        shapeDate = page.locator(SELECTOR_SHAPE_DATE);
        shapeCreated = page.locator(SELECTOR_SHAPE_CREATED);
        shapeSizeNum = page.locator(SELECTOR_SHAPE_SIZE_NUM);
        shapeSize = page.locator(SELECTOR_SHAPE_SIZE);
        buildingDamageLayer = page.locator(SELECTOR_BUILDING_DAMAGE_LAYER);
    }


}
