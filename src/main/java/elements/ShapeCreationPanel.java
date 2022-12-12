package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class ShapeCreationPanel {

    //<editor-fold desc="Selectors">
    private static final String SELECTOR_CRUD_SHAPE_PANEL = "[data-cy=aside-visible]";
    private static final String SELECTOR_PANEL_NAME = "[data-cy=crudShape]";
    private static final String SELECTOR_SHAPE_NAME_INPUT_HEADER = ".css-2zgq6q";
    private static final String SELECTOR_SHAPE_NAME_INPUT = "[data-cy=name]";
    private static final String SELECTOR_CHOOSE_FILE_BTN = "[data-cy=choose-file-button]";
    private static final String SELECTOR_ADD_SHAPE_CANCEL = "[data-cy=cancel]";
    private static final String SELECTOR_CREATE_PROCESS = "[data-cy=createProcess]";
    private static final String SELECTOR_DRAW_POLYGON_BTN = "[data-cy=draw-polygon-button]";
    private static final String SELECTOR_SEGMENT_ONE_NAME = "[data-cy=segment1]";
    private static final String SELECTOR_SEGMENT_TWO_NAME = "[data-cy=segment2]";
    private static final String SELECTOR_SEGMENT_ONE_AREA = "[data-cy=segment1-area]";
    private static final String SELECTOR_SEGMENT_TWO_AREA = "[data-cy=segment2-area]";
    private static final String SELECTOR_DELETE_SEGMENT_BTN = "[data-cy=delete-segment-button]";
    private static final String SELECTOR_TOTAL_AREA_NAME = "[data-cy=total-area]";
    private static final String SELECTOR_TOTAL_AREA_VALUE = "[data-cy=total-area-value]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator crudShapePanel;
    private final Locator panelName;
    private final Locator shapeNameInputHeader;
    private final Locator shapeNameInput;
    private final Locator chooseFileBtn;
    private final Locator addShapeCancel;
    private final Locator createProcess;
    private final Locator drawPolygonBtn;
    private final Locator segmentOneName;
    private final Locator segmentTwoName;
    private final Locator segmentOneArea;
    private final Locator segmentTwoArea;
    private final Locator deleteSegmentBtn;
    private final Locator totalAreaName;
    private final Locator totalAreaValue;
    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getCrudShapePanel() {
        return crudShapePanel;
    }

    public Locator getPanelName() {
        return panelName;
    }

    public Locator getShapeNameInputHeader() {
        return shapeNameInputHeader;
    }

    public Locator getShapeNameInput() {
        return shapeNameInput;
    }

    public Locator getChooseFileBtn() {
        return chooseFileBtn;
    }

    public Locator getAddShapeCancel() {
        return addShapeCancel;
    }

    public Locator getCreateProcess() {
        return createProcess;
    }

    public Locator getDrawPolygonBtn() {
        return drawPolygonBtn;
    }

    public Locator getSegmentOneName() {
        return segmentOneName;
    }

    public Locator getSegmentTwoName() {
        return segmentTwoName;
    }

    public Locator getSegmentOneArea() {
        return segmentOneArea;
    }

    public Locator getSegmentTwoArea() {
        return segmentTwoArea;
    }

    public Locator getDeleteSegmentBtn() {
        return deleteSegmentBtn;
    }

    public Locator getTotalAreaName() {
        return totalAreaName;
    }

    public Locator getTotalAreaValue() {
        return totalAreaValue;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public ShapeCreationPanel() {
        crudShapePanel = page.locator(SELECTOR_CRUD_SHAPE_PANEL);
        panelName = crudShapePanel.locator(SELECTOR_PANEL_NAME);
        shapeNameInputHeader = crudShapePanel.locator(SELECTOR_SHAPE_NAME_INPUT_HEADER);
        shapeNameInput = crudShapePanel.locator(SELECTOR_SHAPE_NAME_INPUT);
        chooseFileBtn = crudShapePanel.locator(SELECTOR_CHOOSE_FILE_BTN);
        addShapeCancel = crudShapePanel.locator(SELECTOR_ADD_SHAPE_CANCEL);
        createProcess = crudShapePanel.locator(SELECTOR_CREATE_PROCESS);
        drawPolygonBtn = page.locator(SELECTOR_DRAW_POLYGON_BTN);
        segmentOneName = crudShapePanel.locator(SELECTOR_SEGMENT_ONE_NAME);
        segmentTwoName = crudShapePanel.locator(SELECTOR_SEGMENT_TWO_NAME);
        segmentOneArea = crudShapePanel.locator(SELECTOR_SEGMENT_ONE_AREA);
        segmentTwoArea = crudShapePanel.locator(SELECTOR_SEGMENT_TWO_AREA);
        deleteSegmentBtn = crudShapePanel.locator(SELECTOR_DELETE_SEGMENT_BTN);
        totalAreaName = crudShapePanel.locator(SELECTOR_TOTAL_AREA_NAME);
        totalAreaValue = crudShapePanel.locator(SELECTOR_TOTAL_AREA_VALUE);
    }

    public void waitForShapeCreationPanel() {
        page.waitForSelector(SELECTOR_CRUD_SHAPE_PANEL);
    }
}
