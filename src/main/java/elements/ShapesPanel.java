package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class ShapesPanel {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_ADDED_SHAPES = "[data-cy=shape]";
    private static final String SELECTOR_SHAPES_PANEL = "[data-cy=aside]";
    private static final String SELECTOR_SHAPE_SEARCH = "[data-cy=search]";
    private static final String SELECTOR_SHAPE_NAME = "[data-cy=name]";
    private static final String SELECTOR_SHAPE_OVERALL = "[data-cy=overall]";
    private static final String SELECTOR_OPEN_DETAILS = "[data-cy=open]";
    private static final String SELECTOR_STATUS_ICON = ".css-1wfct4i";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator addedShapes;
    private final Locator shapesPanel;
    private final Locator shapeSearch;
    private final Locator shapeName;
    private final Locator shapeOverall;
    private final Locator openDetailsBtn;
    private final Locator statusIcon;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getAddedShapes() {
        return addedShapes;
    }

    public Locator getShapesPanel() {
        return shapesPanel;
    }

    public Locator getShapeSearch() {
        return shapeSearch;
    }

    public Locator getShapeName() {
        return shapeName;
    }

    public Locator getShapeOverall() {
        return shapeOverall;
    }

    public Locator getOpenDetailsBtn() {
        return openDetailsBtn;
    }

    public Locator getStatusIcon() {
        return statusIcon;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public ShapesPanel() {
        this.shapesPanel = page.locator(SELECTOR_SHAPES_PANEL);
        this.addedShapes = this.shapesPanel.locator(SELECTOR_ADDED_SHAPES);
        this.shapeSearch = this.shapesPanel.locator(SELECTOR_SHAPE_SEARCH);
        this.shapeName = this.addedShapes.locator(SELECTOR_SHAPE_NAME);
        this.shapeOverall = this.addedShapes.locator(SELECTOR_SHAPE_OVERALL);
        this.openDetailsBtn = this.addedShapes.locator(SELECTOR_OPEN_DETAILS);
        this.statusIcon = this.addedShapes.locator(SELECTOR_STATUS_ICON);
    }
}
