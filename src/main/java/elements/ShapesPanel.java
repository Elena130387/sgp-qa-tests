package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class ShapesPanel {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_ADDED_SHAPES = "[data-cy=shape]";
    private static final String SELECTOR_SHAPES_PANEL = "[data-cy=aside]";
    private static final String SELECTOR_CRUD_SHAPE = "[data-cy=crudShape]";
    private static final String SELECTOR_NEW_SHAPE_NAME = "[data-cy=name]";
    private static final String SELECTOR_ADD_SHAPE_CANCEL = "[data-cy=cancel]";
    private static final String SELECTOR_CREATE_PROCESS = "[data-cy=createProcess]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator addedShapes;
    private final Locator shapesPanel;
    private final Locator crudShape;
    private final Locator newShapeName;
    private final Locator addShapeCancel;
    private final Locator createProcess;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getAddedShapes() {
        return addedShapes;
    }

    public Locator getShapesPanel() {
        return shapesPanel;
    }

    public Locator getCrudShape() {
        return crudShape;
    }

    public Locator getNewShapeName() {
        return newShapeName;
    }

    public Locator getAddShapeCancel() {
        return addShapeCancel;
    }

    public Locator getCreateProcess() {
        return createProcess;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public ShapesPanel() {
        this.shapesPanel = page.locator(SELECTOR_SHAPES_PANEL);
        this.addedShapes = this.shapesPanel.locator(SELECTOR_ADDED_SHAPES);
        this.crudShape = this.shapesPanel.locator(SELECTOR_CRUD_SHAPE);
        this.newShapeName = this.shapesPanel.locator(SELECTOR_NEW_SHAPE_NAME);
        this.addShapeCancel = this.shapesPanel.locator(SELECTOR_ADD_SHAPE_CANCEL);
        this.createProcess = this.shapesPanel.locator(SELECTOR_CREATE_PROCESS);
    }
}
