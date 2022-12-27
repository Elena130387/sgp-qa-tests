package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

import static util.Util.assertTooltip;

public class GeneralizedShapePanel extends ShapeControlButtons {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_SHAPE_NAME = "[data-cy=name]";
    private static final String SELECTOR_SHAPE_NAME_TOOLTIP = "[data-tooltip=shape-name]";
    private static final String SELECTOR_SHAPE_OVERALL = "[data-cy=overall]";
    private static final String SELECTOR_POLYGONS_COUNT_NUMBER = "[data-cy=shape-overall-polygon-count-number]";
    private static final String SELECTOR_POLYGONS_COUNT_TEXT = "[data-cy=shape-overall-polygon-count]";
    private static final String SELECTOR_CREATED_DATE = "[data-cy=shape-overall-created-at-date]";
    private static final String SELECTOR_CREATED_DATE_TEXT = "[data-cy=shape-overall-created-at]";
    private static final String SELECTOR_SHAPE_SIZE = "[data-cy=shape-overall-area-size-number]";
    private static final String SELECTOR_SHAPE_SIZE_TEXT = "[data-cy=shape-overall-area-size]";
    private static final String SELECTOR_PROGRESS_BAR = "[data-cy=progressBar]";
    private static final String SELECTOR_STOP_CALCULATION_BTN = "[data-cy=stopCalculation]";
    private static final String SELECTOR_STOP_CALCULATION_TOOLTIP = "[data-tooltip=stop-calculation]";
    private static final String SELECTOR_STOP_CALCULATION_YES = "[data-cy=stop]";
    private static final String SELECTOR_STOP_CALCULATION_NO = "[data-cy=no]";
    private static final String SELECTOR_STOPPED_CALCULATION_ICON = "[data-cy=calculations-stop-icon]";
    private static final String SELECTOR_STOPPED_CALCULATION_TOOLTIP = "[data-tooltip=calculation-stopped]";
    private static final String SELECTOR_FAILED_CALCULATION_ICON = "[data-cy=calculations-error-icon]";
    private static final String SELECTOR_FAILED_CALCULATION_TOOLTIP = "[data-tooltip=calculation-error]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator shapeName;
    private final Locator shapeNameTooltip;
    private final Locator shapeOverall;
    private final Locator polygonsCountNumber;
    private final Locator polygonsCountText;
    private final Locator createdDate;
    private final Locator createdDateText;
    private final Locator shapeSize;
    private final Locator shapeSizeText;
    private final Locator progressBar;
    private final Locator stopCalculationBtn;
    private final Locator stopCalculationTooltip;
    private final Locator stopCalculationYes;
    private final Locator stopCalculationNo;
    private final Locator stoppedCalculationIcon;
    private final Locator stoppedCalculationTooltip;
    private final Locator failedCalculationIcon;
    private final Locator failedCalculationTooltip;
    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getShapeName() {
        return shapeName;
    }

    public Locator getShapeNameTooltip() {
        return shapeNameTooltip;
    }

    public Locator getShapeOverall() {
        return shapeOverall;
    }

    public Locator getPolygonsCountNumber() {
        return polygonsCountNumber;
    }

    public Locator getPolygonsCountText() {
        return polygonsCountText;
    }

    public Locator getCreatedDate() {
        return createdDate;
    }

    public Locator getCreatedDateText() {
        return createdDateText;
    }

    public Locator getShapeSize() {
        return shapeSize;
    }

    public Locator getShapeSizeText() {
        return shapeSizeText;
    }

    public Locator getProgressBar() {
        return progressBar;
    }

    public Locator getStopCalculationBtn() {
        return stopCalculationBtn;
    }

    public Locator getStopCalculationYes() {
        return stopCalculationYes;
    }

    public Locator getStopCalculationNo() {
        return stopCalculationNo;
    }

    public Locator getStoppedCalculationIcon() {
        return stoppedCalculationIcon;
    }

    public Locator getFailedCalculationIcon() {
        return failedCalculationIcon;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public GeneralizedShapePanel() {
        shapeName = page.locator(SELECTOR_SHAPE_NAME);
        shapeNameTooltip = page.locator(SELECTOR_SHAPE_NAME_TOOLTIP);
        shapeOverall = page.locator(SELECTOR_SHAPE_OVERALL);
        polygonsCountNumber = page.locator(SELECTOR_POLYGONS_COUNT_NUMBER);
        polygonsCountText = page.locator(SELECTOR_POLYGONS_COUNT_TEXT);
        createdDate = page.locator(SELECTOR_CREATED_DATE);
        createdDateText = page.locator(SELECTOR_CREATED_DATE_TEXT);
        shapeSize = page.locator(SELECTOR_SHAPE_SIZE);
        shapeSizeText = page.locator(SELECTOR_SHAPE_SIZE_TEXT);
        progressBar = page.locator(SELECTOR_PROGRESS_BAR);
        stopCalculationBtn = page.locator(SELECTOR_STOP_CALCULATION_BTN);
        stopCalculationTooltip = page.locator(SELECTOR_STOP_CALCULATION_TOOLTIP);
        stopCalculationYes = page.locator(SELECTOR_STOP_CALCULATION_YES);
        stopCalculationNo = page.locator(SELECTOR_STOP_CALCULATION_NO);
        stoppedCalculationIcon = page.locator(SELECTOR_STOPPED_CALCULATION_ICON);
        stoppedCalculationTooltip = page.locator(SELECTOR_STOPPED_CALCULATION_TOOLTIP);
        failedCalculationIcon = page.locator(SELECTOR_FAILED_CALCULATION_ICON);
        failedCalculationTooltip = page.locator(SELECTOR_FAILED_CALCULATION_TOOLTIP);
    }

    public void waitForProgressBar() {
        page.waitForSelector(SELECTOR_PROGRESS_BAR);
    }

    public void waitForStoppedCalculationIcon() {
        page.waitForSelector(SELECTOR_STOPPED_CALCULATION_ICON);
    }

    public void checkStoppedCalculationIconTooltip(String expText) {
        assertTooltip(stoppedCalculationIcon, stoppedCalculationTooltip, expText);
    }

    public void checkFailedCalculationIconTooltip(String expText) {
        assertTooltip(failedCalculationIcon, failedCalculationTooltip, expText);
    }
}

