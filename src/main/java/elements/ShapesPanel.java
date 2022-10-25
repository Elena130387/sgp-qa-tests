package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class ShapesPanel {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_ADDED_SHAPES = "[data-cy=shape]";
    private static final String SELECTOR_SHAPES_PANEL = "[data-cy=aside]";
    private static final String SELECTOR_SHAPE_SEARCH = "[data-cy=search]";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator addedShapes;
    private final Locator shapesPanel;
    private final Locator shapeSearch;
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

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public ShapesPanel() {
        this.shapesPanel = page.locator(SELECTOR_SHAPES_PANEL);
        this.addedShapes = this.shapesPanel.locator(SELECTOR_ADDED_SHAPES);
        this.shapeSearch = this.shapesPanel.locator(SELECTOR_SHAPE_SEARCH);
    }
}
