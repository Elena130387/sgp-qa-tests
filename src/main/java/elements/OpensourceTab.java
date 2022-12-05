package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class OpensourceTab {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_CENSUS = "[data-cy=open-source-tab-census-data]";
    private static final String SELECTOR_GEOGRAPHIC_DETAILS = "[data-cy=open-source-tab-info-outline]";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator census;
    private final Locator geographicDetails;


    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getCensus() {
        return census;
    }

    public Locator getGeographicDetails() { return geographicDetails; }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public OpensourceTab() {
        census = page.locator(SELECTOR_CENSUS);
        geographicDetails = page.locator(SELECTOR_GEOGRAPHIC_DETAILS);

    }
}
