package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class EstimatedTab {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_OBJECTS = "[data-cy=objects]";
    private static final String SELECTOR_BUILDINGS = "[data-cy=buildings]";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator objects;
    private final Locator buildings;


    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getObjects() {
        return objects;
    }

    public Locator getBuildings() { return buildings; }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public EstimatedTab() {
        objects = page.locator(SELECTOR_OBJECTS);
        buildings = page.locator(SELECTOR_BUILDINGS);
    }
}
