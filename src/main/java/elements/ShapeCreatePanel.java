package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class ShapeCreatePanel {

    //<editor-fold desc="Selectors">
    private static final String SELECTOR_CRUD_SHAPE_PANEL = "[data-cy=aside]";
    private static final String SELECTOR_PANEL_NAME = "[data-cy=crudShape]";
    private static final String SELECTOR_ADD_SEGMENT = "[data-cy=create-shape-inner-dropdown-button]";
    private static final String SELECTOR_NEW_SHAPE_NAME = "[data-cy=name]";
    private static final String SELECTOR_ADD_SHAPE_CANCEL = "[data-cy=cancel]";
    private static final String SELECTOR_CREATE_PROCESS = "[data-cy=createProcess]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator crudShapePanel;
    private final Locator panelName;
    private final Locator addSegment;
    private final Locator newShapeName;
    private final Locator addShapeCancel;
    private final Locator createProcess;
    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getCrudShapePanel() {
        return crudShapePanel;
    }

    public Locator getPanelName() {
        return panelName;
    }

    public Locator getAddSegment() {
        return addSegment;
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

    public ShapeCreatePanel() {
        crudShapePanel = page.locator(SELECTOR_CRUD_SHAPE_PANEL);
        panelName = crudShapePanel.locator(SELECTOR_PANEL_NAME);
        addSegment = crudShapePanel.locator(SELECTOR_ADD_SEGMENT);
        newShapeName = crudShapePanel.locator(SELECTOR_NEW_SHAPE_NAME);
        addShapeCancel = crudShapePanel.locator(SELECTOR_ADD_SHAPE_CANCEL);
        createProcess = crudShapePanel.locator(SELECTOR_CREATE_PROCESS);
    }
}
