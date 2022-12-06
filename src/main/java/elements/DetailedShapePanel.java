package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import elements.ShapeEstimatedTab.EstimatedTabGeneral;
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
    private static final String SELECTOR_OPENSOURCE_TAB_BTN = "[data-cy=opensource]";
    private static final String SELECTOR_ESTIMATED_TAB_BTN = "[data-cy=estimated]";
    private static final String SELECTOR_ECONOMIC_TAB_BTN = "[data-cy=economic]";
    private static final String SELECTOR_INSURANCE_TAB_BTN = "[data-cy=insurance]";
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
    private final Locator opensourceTabBtn;
    private final Locator estimatedTabBtn;
    private final Locator economicTabBtn;
    private final Locator insuranceTabBtn;

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

    public Locator getOpensourceTabBtn() {
        return opensourceTabBtn;
    }
    public Locator getEstimatedTabBtn() {
        return estimatedTabBtn;
    }
    public Locator getEconomicTabBtn() {
        return economicTabBtn;
    }
    public Locator getInsuranceTabBtn() {
        return insuranceTabBtn;
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
        opensourceTabBtn = page.locator(SELECTOR_OPENSOURCE_TAB_BTN);
        estimatedTabBtn = page.locator(SELECTOR_ESTIMATED_TAB_BTN);
        economicTabBtn = page.locator(SELECTOR_ECONOMIC_TAB_BTN);
        insuranceTabBtn = page.locator(SELECTOR_INSURANCE_TAB_BTN);
    }
    public OpensourceTab openOpensourceTab() {
        opensourceTabBtn.click();
        return new OpensourceTab();
    }

    public EstimatedTabGeneral openEstimatedTab() {
        estimatedTabBtn.click();
        return new EstimatedTabGeneral();
    }

    public EconomicTab openEconomicTab() {
        economicTabBtn.click();
        return new EconomicTab();
    }

    public InsuranceTab openInsuranceTab() {
        insuranceTabBtn.click();
        return new InsuranceTab();
    }
}
