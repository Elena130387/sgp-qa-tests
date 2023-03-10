package elements.map;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class MapBlock {

    //<editor-fold desc="Selectors">
    private static final String SELECTOR_MAP = ".overlays";
    private static final String SELECTOR_MAP_ZOOM = "[data-cy=scale-control]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator map;
    private final Locator mapZoom;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getMap() {
        return map;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public MapBlock() {
        map = page.locator(SELECTOR_MAP);
        mapZoom = page.locator(SELECTOR_MAP_ZOOM);
    }

    public void waitForMap() {
        page.waitForSelector(SELECTOR_MAP);
    }

    public void waitForMapZoom(String mapZoom) {
        page.waitForSelector("text=" + mapZoom);
    }

    public String getActualMapZoomWithoutSpace() {
        String actualMapZoom = mapZoom.textContent();
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
