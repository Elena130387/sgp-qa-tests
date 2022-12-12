package elements.ShapeEstimatedTab;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class PopulationLayer {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_POPULATION_TITLE = "[data-cy=population-layer-title]";
    private static final String SELECTOR_SWITCH_3D_TITLE = "[data-cy=layer-switch-3D]";
    private static final String SELECTOR_POPULATION_COUNT = "[data-cy=population-count]";
    private static final String SELECTOR_POPULATION_COUNT_VALUE = "[data-cy=population-count-value]";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator populationTitle;
    private final Locator switch3DTitle;
    private final Locator populationCount;
    private final Locator populationCountValue;

    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getPopulationTitle() {
        return populationTitle;
    }

    public Locator getSwitch3DTitle() {
        return switch3DTitle;
    }

    public Locator getPopulationCount() {
        return populationCount;
    }

    public Locator getPopulationCountValue() {
        return populationCountValue;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public PopulationLayer() {
        populationTitle = page.locator(SELECTOR_POPULATION_TITLE);
        switch3DTitle = page.locator(SELECTOR_SWITCH_3D_TITLE);
        populationCount = page.locator(SELECTOR_POPULATION_COUNT);
        populationCountValue = page.locator(SELECTOR_POPULATION_COUNT_VALUE);
    }
}
