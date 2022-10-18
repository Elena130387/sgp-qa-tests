package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

import static util.Constants.MAP_ZOOM_STANDART;

public class MapBlock {

    //<editor-fold desc="Selectors">
    private static final String SELECTOR_MAP = ".overlays";
    private static final String SELECTOR_MAP_ZOOM = ".mapboxgl-ctrl-scale";
    private static final String SELECTOR_STANDART_MAP_ZOOM = "text=" + MAP_ZOOM_STANDART;
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator map;
    private final Locator mapZoom;
    private final Locator standartMapZoom;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getMap() {
        return map;
    }

    public Locator getStandartMapZoom() {
        return standartMapZoom;
    }

    public Locator getMapZoom() {
        return mapZoom;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public MapBlock() {
        this.map = page.locator(SELECTOR_MAP);
        this.mapZoom = page.locator(SELECTOR_MAP_ZOOM);
        this.standartMapZoom = page.locator(SELECTOR_STANDART_MAP_ZOOM);
    }

    public void waitForMap() {
        this.page.waitForSelector(SELECTOR_MAP);
    }

    public void waitForStandartMapZoom(String standartMapZoom) {
        this.page.waitForSelector("text=" + standartMapZoom);
    }

    public String getActualMapZoomWithoutSpace() {
        String actualMapZoom = this.mapZoom.textContent();
        actualMapZoom = actualMapZoom.replaceAll("\\W+", "");
        return actualMapZoom;
    }

    public void turnMapToTheLeft(int countLeftClick) {
        int num = 0;
        page.locator(SELECTOR_MAP).click();
        do {
            num += 1;
            page.locator(SELECTOR_MAP).press("Control+ArrowLeft");

        } while (num < Math.abs(countLeftClick));
        page.waitForTimeout(1000);
    }
}
