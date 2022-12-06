package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class EconomicTab {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_ECONOMIC_TITLE = "[data-cy=economic-exposure-title]";
    private static final String SELECTOR_ECONOMIC_GENERAL = "[data-cy=economic-exposure-general]";
    private static final String SELECTOR_ECONOMIC_GENERAL_VALUE = "[data-cy=economic-exposure-general-value]";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator economicTitle;
    private final Locator economicGeneral;
    private final Locator economicGeneralValue;

    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getEconomicTitle() {
        return economicTitle;
    }

    public Locator getEconomicGeneral() { return economicGeneral; }

    public Locator getEconomicGeneralValue() { return economicGeneralValue; }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public EconomicTab() {
        economicTitle = page.locator(SELECTOR_ECONOMIC_TITLE);
        economicGeneral = page.locator(SELECTOR_ECONOMIC_GENERAL);
        economicGeneralValue = page.locator(SELECTOR_ECONOMIC_GENERAL_VALUE);

    }
}
