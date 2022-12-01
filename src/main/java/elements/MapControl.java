package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class MapControl {

    //<editor-fold desc="Selectors">
    private static final String SELECTOR_NAVIGATION_BAR = "[data-cy=map-navigation-controls]";
    private static final String SELECTOR_ZOOM_IN = ".mapboxgl-ctrl-zoom-in";
    private static final String SELECTOR_ZOOM_OUT = ".mapboxgl-ctrl-zoom-out";
    private static final String SELECTOR_COMPASS = ".mapboxgl-ctrl-compass";
    private static final String SELECTOR_COMPASS_IMG = ".mapboxgl-ctrl-icon";
    private static final String SELECTOR_COVERAGE_AND_GRID_BAR = "[data-cy=map-controls]";
    private static final String SELECTOR_COVERAGE = "[data-cy=coverage]";
    private static final String SELECTOR_GLOBAL_GRID = "[data-cy=globalGrid]";
    private static final String SELECTOR_GRID_LINES = "[data-cy=gridLines]";
    private static final String SELECTOR_AGGREGATED = "[data-cy=aggregated]";
    private static final String SELECTOR_VIEWPORT = "[data-cy=viewport]";
    private static final String SELECTOR_POLYGONS_SECTION_OPEN_BTN = "[data-cy=polygon]";
    private static final String SELECTOR_GEO_SEARCH = "[data-cy=geoSearch]";
    private static final String SELECTOR_GEO_SEARCH_LIST = ".suggestions";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator navigationBar;
    private final Locator coverageAndGridBar;
    private final Locator polygonsSectionBtn;
    private final Locator geoSearch;
    private final Locator zoomInBtn;
    private final Locator geoSearchList;
    private final Locator zoomOutBtn;
    private final Locator compassBtn;
    private final Locator compassBtnImg;
    private final Locator coverageBtn;
    private final Locator globalGridBtn;
    private final Locator gridLinesBtn;
    private final Locator aggregationBtn;
    private final Locator viewportBtn;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getNavigationBar() {
        return navigationBar;
    }

    public Locator getCoverageAndGridBar() {
        return coverageAndGridBar;
    }

    public Locator getPolygonsSectionBtn() {
        return polygonsSectionBtn;
    }

    public Locator getGeoSearch() {
        return geoSearch;
    }

    public Locator getGeoSearchList() {
        return geoSearchList;
    }

    public Locator getZoomInBtn() {
        return zoomInBtn;
    }

    public Locator getZoomOutBtn() {
        return zoomOutBtn;
    }

    public Locator getCompassBtn() {
        return compassBtn;
    }

    public Locator getCompassBtnImg() {
        return compassBtnImg;
    }

    public Locator getCoverageBtn() {
        return coverageBtn;
    }

    public Locator getGlobalGridBtn() {
        return globalGridBtn;
    }

    public Locator getGridLinesBtn() {
        return gridLinesBtn;
    }

    public Locator getAggregationBtn() {
        return aggregationBtn;
    }

    public Locator getViewportBtn() {
        return viewportBtn;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public MapControl() {
        navigationBar = page.locator(SELECTOR_NAVIGATION_BAR);
        coverageAndGridBar = page.locator(SELECTOR_COVERAGE_AND_GRID_BAR);
        polygonsSectionBtn = page.locator(SELECTOR_POLYGONS_SECTION_OPEN_BTN);
        geoSearch = page.locator(SELECTOR_GEO_SEARCH);
        geoSearchList = geoSearch.locator(SELECTOR_GEO_SEARCH_LIST);
        zoomOutBtn = navigationBar.locator(SELECTOR_ZOOM_OUT);
        zoomInBtn = navigationBar.locator(SELECTOR_ZOOM_IN);
        compassBtn = navigationBar.locator(SELECTOR_COMPASS);
        compassBtnImg = compassBtn.locator(SELECTOR_COMPASS_IMG);
        coverageBtn = coverageAndGridBar.locator(SELECTOR_COVERAGE);
        globalGridBtn = coverageAndGridBar.locator(SELECTOR_GLOBAL_GRID);
        gridLinesBtn = coverageAndGridBar.locator(SELECTOR_GRID_LINES);
        aggregationBtn = coverageAndGridBar.locator(SELECTOR_AGGREGATED);
        viewportBtn = coverageAndGridBar.locator(SELECTOR_VIEWPORT);
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
}
