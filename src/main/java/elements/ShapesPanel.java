package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class ShapesPanel {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_ADDED_SHAPES = "[data-cy=shape]";
    private static final String SELECTOR_SHAPES_PANEL_VISIBLE = "[data-cy=aside-visible]";
    private static final String SELECTOR_SHAPES_PANEL_INVISIBLE = "[data-cy=aside-invisible]";
    private static final String SELECTOR_SHAPE_SEARCH = "[data-cy=search]";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator addedShapes;
    private final Locator shapesPanelVisible;
    private final Locator shapesPanelInvisible;
    private final Locator shapeSearch;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getAddedShapes() {
        return addedShapes;
    }

    public Locator getShapesPanelVisible() {
        return shapesPanelVisible;
    }

    public Locator getShapesPanelInvisible() {
        return shapesPanelInvisible;
    }

    public Locator getShapeSearch() {
        return shapeSearch;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public ShapesPanel() {
        shapesPanelVisible = page.locator(SELECTOR_SHAPES_PANEL_VISIBLE);
        shapesPanelInvisible = page.locator(SELECTOR_SHAPES_PANEL_INVISIBLE);
        addedShapes = shapesPanelVisible.locator(SELECTOR_ADDED_SHAPES);
        shapeSearch = shapesPanelVisible.locator(SELECTOR_SHAPE_SEARCH);
    }
}
