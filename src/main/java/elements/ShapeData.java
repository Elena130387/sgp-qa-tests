package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class ShapeData {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_SHAPE_NAME = "[data-cy=name]";
    private static final String SELECTOR_SHAPE_OVERALL = "[data-cy=overall]";
    private static final String SELECTOR_OPEN_DETAILS = "[data-cy=open]";
    private static final String SELECTOR_PROGRESS_BAR = "[data-cy=progressBar]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator shapeName;
    private final Locator shapeOverall;
    private final Locator openDetailsBtn;
    private final Locator progressBar;
    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getShapeName() {
        return shapeName;
    }

    public Locator getShapeOverall() {
        return shapeOverall;
    }

    public Locator getOpenDetailsBtn() {
        return openDetailsBtn;
    }

    public Locator getProgressBar() {
        return progressBar;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public ShapeData() {
        shapeName = page.locator(SELECTOR_SHAPE_NAME);
        shapeOverall = page.locator(SELECTOR_SHAPE_OVERALL);
        openDetailsBtn = page.locator(SELECTOR_OPEN_DETAILS);
        progressBar = page.locator(SELECTOR_PROGRESS_BAR);
    }

    public void waitForProgressBar () {
        page.waitForSelector(SELECTOR_PROGRESS_BAR);
    }
}

