package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;
import util.Util;

public class MapControl {

    //<editor-fold desc="Selectors">
    private static final String SELECTOR_NAVIGATION_BAR = "[data-cy=map-navigation-controls]";
    private static final String SELECTOR_ZOOM_IN = ".mapboxgl-ctrl-zoom-in";
    private static final String SELECTOR_ZOOM_OUT = ".mapboxgl-ctrl-zoom-out";
    private static final String SELECTOR_COMPASS = ".mapboxgl-ctrl-compass";
    private static final String SELECTOR_COMPASS_IMG = ".mapboxgl-ctrl-icon";

    private static final String SELECTOR_COVERAGE_AND_GRID_BAR = "[data-cy=map-controls]";
    private static final String SELECTOR_COVERAGE = "[data-cy=coverage]";
    private static final String SELECTOR_COVERAGE_HIGHLIGHTING = "[data-cy=highlighting]";
    private static final String SELECTOR_COVERAGE_HIGHLIGHTING_HIDE_TOOLTIP = "[data-tooltip=not-highlight-tiles]";
    private static final String SELECTOR_COVERAGE_HIGHLIGHTING_SHOW_TOOLTIP = "[data-tooltip=highlight-tiles]";
    private static final String SELECTOR_GLOBAL_GRID = "[data-cy=globalGrid]";
    private static final String SELECTOR_GLOBAL_GRID_HIDE_TOOLTIP = "[data-tooltip=hide-grid]";
    private static final String SELECTOR_GLOBAL_GRID_SHOW_TOOLTIP = "[data-tooltip=show-grid]";
    private static final String SELECTOR_GRID_LINES = "[data-cy=gridLines]";
    private static final String SELECTOR_GRID_LINES_HIDE_TOOLTIP = "[data-tooltip=hide-grid-lines]";
    private static final String SELECTOR_GRID_LINES_SHOW_TOOLTIP = "[data-tooltip=show-grid-lines]";
    private static final String SELECTOR_AGGREGATED = "[data-cy=aggregated]";
    private static final String SELECTOR_RESET_VIEWPORT = "[data-cy=viewport]";
    private static final String SELECTOR_RESET_VIEWPORT_TOOLTIP = "[data-tooltip=reset-viewport]";

    private static final String SELECTOR_SHAPES_PANEL_HIDE_SHOW_BTN = "[data-cy=polygon]";
    private static final String SELECTOR_SHAPES_PANEL_HIDE_TOOLTIP = "[data-tooltip=hide-aside]";
    private static final String SELECTOR_SHAPES_PANEL_SHOW_TOOLTIP = "[data-tooltip=show-aside]";
    private static final String SELECTOR_GEO_SEARCH = "[data-cy=geoSearch]";
    private static final String SELECTOR_GEO_SEARCH_LIST = ".suggestions";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator navigationBar;
    private final Locator coverageGridBar;
    private final Locator shapesPanelHideShowBtn;
    private final Locator shapesPanelHideTooltip;
    private final Locator shapesPanelShowTooltip;
    private final Locator geoSearch;
    private final Locator zoomInBtn;
    private final Locator geoSearchList;
    private final Locator zoomOutBtn;
    private final Locator compassBtn;
    private final Locator compassBtnImg;
    private final Locator coverageBtn;
    private final Locator coverageHighlightingBtn;
    private final Locator coverageHighlightHideTooltip;
    private final Locator coverageHighlightShowTooltip;
    private final Locator globalGridBtn;
    private final Locator globalGridHideTooltip;
    private final Locator globalGridShowTooltip;
    private final Locator gridLinesBtn;
    private final Locator gridLinesHideTooltip;
    private final Locator gridLinesShowTooltip;
    private final Locator aggregationBtn;
    private final Locator viewportBtn;
    private final Locator resetViewportBtnTooltip;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getShapesPanelHideShowBtn() {
        return shapesPanelHideShowBtn;
    }

    public Locator getZoomInBtn() {
        return zoomInBtn;
    }

    public Locator getZoomOutBtn() {
        return zoomOutBtn;
    }

    public Locator getGeoSearch() {
        return geoSearch;
    }

    public Locator getGeoSearchList() {
        return geoSearchList;
    }

    public Locator getCompassBtn() {
        return compassBtn;
    }

    public Locator getCoverageHighlightingBtn() {
        return coverageHighlightingBtn;
    }

    public Locator getGlobalGridBtn() {
        return globalGridBtn;
    }

    public Locator getGridLinesBtn() {
        return gridLinesBtn;
    }

    public Locator getViewportBtn() {
        return viewportBtn;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public MapControl() {
        navigationBar = page.locator(SELECTOR_NAVIGATION_BAR);
        coverageGridBar = page.locator(SELECTOR_COVERAGE_AND_GRID_BAR);
        shapesPanelHideShowBtn = page.locator(SELECTOR_SHAPES_PANEL_HIDE_SHOW_BTN);
        shapesPanelHideTooltip = page.locator(SELECTOR_SHAPES_PANEL_HIDE_TOOLTIP);
        shapesPanelShowTooltip = page.locator(SELECTOR_SHAPES_PANEL_SHOW_TOOLTIP);

        geoSearch = page.locator(SELECTOR_GEO_SEARCH);
        geoSearchList = geoSearch.locator(SELECTOR_GEO_SEARCH_LIST);

        zoomOutBtn = navigationBar.locator(SELECTOR_ZOOM_OUT);
        zoomInBtn = navigationBar.locator(SELECTOR_ZOOM_IN);
        compassBtn = navigationBar.locator(SELECTOR_COMPASS);

        compassBtnImg = compassBtn.locator(SELECTOR_COMPASS_IMG);
        coverageBtn = coverageGridBar.locator(SELECTOR_COVERAGE);
        coverageHighlightingBtn = coverageGridBar.locator(SELECTOR_COVERAGE_HIGHLIGHTING);
        coverageHighlightHideTooltip = page.locator(SELECTOR_COVERAGE_HIGHLIGHTING_HIDE_TOOLTIP);
        coverageHighlightShowTooltip = page.locator(SELECTOR_COVERAGE_HIGHLIGHTING_SHOW_TOOLTIP);
        globalGridBtn = coverageGridBar.locator(SELECTOR_GLOBAL_GRID);
        globalGridHideTooltip = page.locator(SELECTOR_GLOBAL_GRID_HIDE_TOOLTIP);
        globalGridShowTooltip = page.locator(SELECTOR_GLOBAL_GRID_SHOW_TOOLTIP);
        gridLinesBtn = coverageGridBar.locator(SELECTOR_GRID_LINES);
        gridLinesHideTooltip = page.locator(SELECTOR_GRID_LINES_HIDE_TOOLTIP);
        gridLinesShowTooltip = page.locator(SELECTOR_GRID_LINES_SHOW_TOOLTIP);
        aggregationBtn = coverageGridBar.locator(SELECTOR_AGGREGATED);
        viewportBtn = coverageGridBar.locator(SELECTOR_RESET_VIEWPORT);
        resetViewportBtnTooltip = page.locator(SELECTOR_RESET_VIEWPORT_TOOLTIP);
    }

    private void clickZoom(String zoomLocator, int clickNum) {
        int num = 0;
        do {
            num += 1;
            page.locator(zoomLocator).click();
        } while (num < Math.abs(clickNum));
        page.waitForTimeout(500);
    }

    public void clickZoomIn(int clickNum) {
        clickZoom(SELECTOR_ZOOM_IN, clickNum);
    }

    public void clickZoomOut(int clickNum) {
        clickZoom(SELECTOR_ZOOM_OUT, clickNum);
    }

    public String getCompassState() {
        String state = compassBtnImg.getAttribute("style");
        state = state.substring(0, state.length() - 1);
        return state;
    }

    public void waitForCoverageGridBar() {
        page.waitForSelector(SELECTOR_COVERAGE_AND_GRID_BAR);
    }

    public void checkShapesPanelHideTooltip(String expText) {
        Util.assertTooltip(shapesPanelHideShowBtn, shapesPanelHideTooltip, expText);
    }

    public void checkShapesPanelShowTooltip(String expText) {
        Util.assertTooltip(shapesPanelHideShowBtn, shapesPanelShowTooltip, expText);
    }

    public void checkGridLinesHideTooltip(String expText) {
        Util.assertTooltip(gridLinesBtn, gridLinesHideTooltip, expText);
    }

    public void checkGridLinesShowTooltip(String expText) {
        Util.assertTooltip(gridLinesBtn, gridLinesShowTooltip, expText);
    }

    public void checkGlobalGridHideTooltip(String expText) {
        Util.assertTooltip(globalGridBtn, globalGridHideTooltip, expText);
    }

    public void checkGlobalGridShowTooltip(String expText) {
        Util.assertTooltip(globalGridBtn, globalGridShowTooltip, expText);
    }

    public void checkCoverageHighlightHideTooltip(String expText) {
        Util.assertTooltip(coverageHighlightingBtn, coverageHighlightHideTooltip, expText);
    }

    public void checkCoverageHighlightShowTooltip(String expText) {
        Util.assertTooltip(coverageHighlightingBtn, coverageHighlightShowTooltip, expText);
    }

    public void checkResetViewportBtnTooltip(String expText) {
        Util.assertTooltip(viewportBtn, resetViewportBtnTooltip, expText);
    }
}
