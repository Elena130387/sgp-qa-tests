package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.jetbrains.annotations.NotNull;
import util.JunitExtension;
import util.Util;

import java.util.regex.Pattern;

public class ShapeCreationPanel {

    //<editor-fold desc="Selectors">
    private static final String SELECTOR_CRUD_SHAPE_PANEL = "[data-cy=aside-visible]";
    private static final String SELECTOR_PANEL_NAME = "[data-cy=crudShape]";
    private static final String SELECTOR_SHAPE_NAME_INPUT_HEADER = ".css-2zgq6q";
    private static final String SELECTOR_SHAPE_NAME_INPUT = "[data-cy=name]";
    private static final String SELECTOR_CHOOSE_FILE_BTN = "[data-cy=choose-file-button]";
    private static final String SELECTOR_CANCEL_BTN = "[data-cy=cancel]";
    private static final String SELECTOR_CREATE_PROCESS_BTN = "[data-cy=createProcess]";
    private static final String SELECTOR_DRAW_POLYGON_BTN = "[data-cy=draw-polygon-button]";
    private static final String SELECTOR_DRAW_POLYGON_TOOLTIP = "[data-tooltip=draw-polygon]";
    private static final String SELECTOR_SEGMENT_ONE_NAME = "[data-cy=segment1]";
    private static final String SELECTOR_SEGMENT_TWO_NAME = "[data-cy=segment2]";
    private static final String SELECTOR_SEGMENT_ONE_AREA = "[data-cy=segment1-area]";
    private static final String SELECTOR_SEGMENT_TWO_AREA = "[data-cy=segment2-area]";
    private static final String SELECTOR_DELETE_SEGMENT_BTN = "[data-cy=delete-segment-button]";
    private static final String SELECTOR_TOTAL_AREA_NAME = "[data-cy=total-area]";
    private static final String SELECTOR_TOTAL_AREA_VALUE = "[data-cy=total-area-value]";
    private static final String SELECTOR_NAME_ERROR = "[data-cy=nameError]";
    private static final String SELECTOR_POLYGONS_ERROR = "[data-cy=polygonsError]";
    //</editor-fold>

    //<editor-fold desc="Regexes">
    public static final String AREA = "Площадь: ";
    public static final String AREA_VALUE_PATTERN = "\\d+\\.\\d\\d";
    public static final String UNITS = " кв. км";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator crudShapePanel;
    private final Locator panelName;
    private final Locator shapeNameInputHeader;
    private final Locator shapeNameInput;
    private final Locator chooseFileBtn;
    private final Locator cancelBtn;
    private final Locator createProcessBtn;
    private final Locator drawPolygonBtn;
    private final Locator drawPolygonTooltip;
    private final Locator segmentOneName;
    private final Locator segmentTwoName;
    private final Locator segmentOneArea;
    private final Locator segmentTwoArea;
    private final Locator deleteSegmentBtn;
    private final Locator totalAreaName;
    private final Locator totalAreaValue;
    private final Locator nameError;
    private final Locator polygonsError;
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

    public Locator getCancelBtn() {
        return cancelBtn;
    }

    public Locator getCreateProcessBtn() {
        return createProcessBtn;
    }

    public Locator getDrawPolygonBtn() {
        return drawPolygonBtn;
    }

    public Locator getDrawPolygonTooltip() {
        return drawPolygonTooltip;
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

    public Locator getNameError() {
        return nameError;
    }

    public Locator getPolygonsError() {
        return polygonsError;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public ShapeCreationPanel() {
        crudShapePanel = page.locator(SELECTOR_CRUD_SHAPE_PANEL);
        panelName = crudShapePanel.locator(SELECTOR_PANEL_NAME);
        shapeNameInputHeader = crudShapePanel.locator(SELECTOR_SHAPE_NAME_INPUT_HEADER);
        shapeNameInput = crudShapePanel.locator(SELECTOR_SHAPE_NAME_INPUT);
        chooseFileBtn = crudShapePanel.locator(SELECTOR_CHOOSE_FILE_BTN);
        cancelBtn = crudShapePanel.locator(SELECTOR_CANCEL_BTN);
        createProcessBtn = crudShapePanel.locator(SELECTOR_CREATE_PROCESS_BTN);
        drawPolygonBtn = page.locator(SELECTOR_DRAW_POLYGON_BTN);
        drawPolygonTooltip = page.locator(SELECTOR_DRAW_POLYGON_TOOLTIP);
        segmentOneName = crudShapePanel.locator(SELECTOR_SEGMENT_ONE_NAME);
        segmentTwoName = crudShapePanel.locator(SELECTOR_SEGMENT_TWO_NAME);
        segmentOneArea = crudShapePanel.locator(SELECTOR_SEGMENT_ONE_AREA);
        segmentTwoArea = crudShapePanel.locator(SELECTOR_SEGMENT_TWO_AREA);
        deleteSegmentBtn = crudShapePanel.locator(SELECTOR_DELETE_SEGMENT_BTN);
        totalAreaName = crudShapePanel.locator(SELECTOR_TOTAL_AREA_NAME);
        totalAreaValue = crudShapePanel.locator(SELECTOR_TOTAL_AREA_VALUE);
        nameError = crudShapePanel.locator(SELECTOR_NAME_ERROR);
        polygonsError = crudShapePanel.locator(SELECTOR_POLYGONS_ERROR);
    }

    public void waitForShapeCreationPanel() {
        page.waitForSelector(SELECTOR_CRUD_SHAPE_PANEL);
    }

    public boolean isSegmentInformationMatchPattern (String segmentInfo) {
        return Pattern.matches(AREA + AREA_VALUE_PATTERN + UNITS, segmentInfo);
    }

    public double segmentAreaValue (@NotNull String segmentInfo) {
        return Double.parseDouble(segmentInfo.replaceAll(AREA, "").replaceAll(UNITS, ""));
    }

    public boolean isTotalAreaInformationMatchPattern (String totalAreaInfo) {
        return Pattern.matches(AREA_VALUE_PATTERN + UNITS, totalAreaInfo);
    }

    public double totalAreaValue (@NotNull String totalAreaInfo) {
        return Double.parseDouble(totalAreaInfo.replaceAll(UNITS, ""));
    }

    public void checkDrawPolygonBtnTooltip(String expText) {
        Util.assertTooltip(drawPolygonBtn,drawPolygonTooltip, expText);
    }
}
