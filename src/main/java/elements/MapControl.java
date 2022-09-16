package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class MapControl {

    //<editor-fold desc="Selectors">
    private static final String SELECTOR_NAVIGATION_BAR = ".mapboxgl-ctrl mapboxgl-ctrl-group";
    private static final String SELECTOR_ZOOM_IN = ".mapboxgl-ctrl-zoom-in";
    private static final String SELECTOR_ZOOM_OUT = ".mapboxgl-ctrl-zoom-out";
    private static final String SELECTOR_COMPASS = ".mapboxgl-ctrl-compass";
    private static final String SELECTOR_COVERAGE_AND_GRID_BAR = ".css-j7qwjs";
    private static final String SELECTOR_COVERAGE = "[data-cy=coverage]";
    private static final String SELECTOR_GLOBAL_GRID = "[data-cy=globalGrid]";
    private static final String SELECTOR_GRID_LINES = "[data-cy=gridLines]";
    private static final String SELECTOR_POLYGONS_SECTION = "[data-cy=polygon]";
    private static final String SELECTOR_GEO_SEARCH = "[data-cy=geoSearch]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator navigationBar;
    private final Locator coverageAndGridBar;
    private final Locator polygonsSectionBtn;
    private final Locator geoSearch;
    private final Locator zoomInBtn;
    private final Locator zoomOutBtn;
    private final Locator compassBtn;
    private final Locator coverageBtn;
    private final Locator globalGridBtn;
    private final Locator gridLinesBtn;
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

    public Locator getZoomInBtn() {
        return zoomInBtn;
    }

    public Locator getZoomOutBtn() {
        return zoomOutBtn;
    }

    public Locator getCompassBtn() {
        return compassBtn;
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
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public MapControl() {
        this.navigationBar = page.locator(SELECTOR_NAVIGATION_BAR);
        this.coverageAndGridBar = page.locator(SELECTOR_COVERAGE_AND_GRID_BAR);
        this.polygonsSectionBtn = page.locator(SELECTOR_POLYGONS_SECTION);
        this.geoSearch = page.locator(SELECTOR_GEO_SEARCH);
        this.zoomOutBtn = this.navigationBar.locator(SELECTOR_ZOOM_OUT);
        this.zoomInBtn = this.navigationBar.locator(SELECTOR_ZOOM_IN);
        this.compassBtn = this.navigationBar.locator(SELECTOR_COMPASS);
        this.coverageBtn = this.coverageAndGridBar.locator(SELECTOR_COVERAGE);
        this.globalGridBtn = this.coverageAndGridBar.locator(SELECTOR_GLOBAL_GRID);
        this.gridLinesBtn = this.coverageAndGridBar.locator(SELECTOR_GRID_LINES);
    }
}
