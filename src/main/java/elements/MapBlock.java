package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class MapBlock {

    //<editor-fold desc="Selectors">
    private static final String SELECTOR_MAP = ".mapboxgl-map";
    private static final String SELECTOR_STANDART_ZOOM = "text=300 m";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator map;
    private final Locator standartZoom;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getMap() {
        return map;
    }

    public Locator getStandartZoom() {
        return standartZoom;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public MapBlock() {
        this.map = page.locator(SELECTOR_MAP);
        this.standartZoom = page.locator(SELECTOR_STANDART_ZOOM);
    }

    public void waitForMap(){
        this.page.waitForSelector(SELECTOR_MAP);
    }

    public void waitForStandartZoom(){
        this.page.waitForSelector(SELECTOR_STANDART_ZOOM);
    }
}
