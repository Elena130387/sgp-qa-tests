package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class ShapeData {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_SHAPE_NAME = "[data-cy=name]";
    private static final String SELECTOR_SHAPE_OVERALL = "[data-cy=overall]";
    private static final String SELECTOR_POLYGONS_COUNT_NUMBER = "[data-cy=shape-overall-polygon-count-number]";
    private static final String SELECTOR_POLYGONS_COUNT_TEXT = "[data-cy=shape-overall-polygon-count]";
    private static final String SELECTOR_OPEN_DETAILS = "[data-cy=open]";
    private static final String SELECTOR_PROGRESS_BAR = "[data-cy=progressBar]";
    private static final String SELECTOR_STOP_CALCULATION_BTN = "[data-cy=stopCalculation]";
    private static final String SELECTOR_STOP_CALCULATION_TOOLTIP = "[data-tooltip=stop-calculation]";
    private static final String SELECTOR_STOP_CALCULATION_YES = "[data-cy=stop]";
    private static final String SELECTOR_STOP_CALCULATION_NO = "[data-cy=no]";
    private static final String SELECTOR_STOPPED_CALCULATION_ICON = "[data-cy=calculations-stop-icon]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator shapeName;
    private final Locator shapeOverall;
    private final Locator polygonsCountNumber;
    private final Locator polygonsCountText;
    private final Locator openDetailsBtn;
    private final Locator progressBar;
    private final Locator stopCalculationBtn;
    private final Locator stopCalculationTooltip;
    private final Locator stopCalculationYes;
    private final Locator stopCalculationNo;
    private final Locator stoppedCalculationIcon;
    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getShapeName() {
        return shapeName;
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

    public Locator getOpenDetailsBtn() {
        return openDetailsBtn;
    }

    public Locator getProgressBar() {
        return progressBar;
    }

    public Locator getStopCalculationBtn() {
        return stopCalculationBtn;
    }

    public Locator getStopCalculationTooltip() {
        return stopCalculationTooltip;
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

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public ShapeData() {
        shapeName = page.locator(SELECTOR_SHAPE_NAME);
        shapeOverall = page.locator(SELECTOR_SHAPE_OVERALL);
        polygonsCountNumber = page.locator(SELECTOR_POLYGONS_COUNT_NUMBER);
        polygonsCountText = page.locator(SELECTOR_POLYGONS_COUNT_TEXT);
        openDetailsBtn = page.locator(SELECTOR_OPEN_DETAILS);
        progressBar = page.locator(SELECTOR_PROGRESS_BAR);
        stopCalculationBtn = page.locator(SELECTOR_STOP_CALCULATION_BTN);
        stopCalculationTooltip = page.locator(SELECTOR_STOP_CALCULATION_TOOLTIP);
        stopCalculationYes = page.locator(SELECTOR_STOP_CALCULATION_YES);
        stopCalculationNo = page.locator(SELECTOR_STOP_CALCULATION_NO);
        stoppedCalculationIcon = page.locator(SELECTOR_STOPPED_CALCULATION_ICON);
    }

    public void waitForProgressBar() {
        page.waitForSelector(SELECTOR_PROGRESS_BAR);
    }
}

