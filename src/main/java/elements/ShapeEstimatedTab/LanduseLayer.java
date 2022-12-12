package elements.ShapeEstimatedTab;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class LanduseLayer {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_LANDUSE_TITLE = "[data-cy=land-use-title]";
    private static final String SELECTOR_STREET_TYPE = "[data-cy=уличная зона]";
    private static final String SELECTOR_STREET_VALUE = "[data-cy=уличная зона-value]";
    private static final String SELECTOR_COMMERCIAL_TYPE = "[data-cy=коммерческий]";
    private static final String SELECTOR_COMMERCIAL_VALUE = "[data-cy=коммерческий-value]";
    private static final String SELECTOR_INDUSTRIAL_TYPE = "[data-cy=промышленный]";
    private static final String SELECTOR_INDUSTRIAL_VALUE = "[data-cy=промышленный-value]";
    private static final String SELECTOR_RESIDENTIAL_TYPE = "[data-cy=жилой]";
    private static final String SELECTOR_RESIDENTIAL_VALUE = "[data-cy=жилой-value]";
    private static final String SELECTOR_NATURE_TYPE = "[data-cy=природный]";
    private static final String SELECTOR_NATURE_VALUE = "[data-cy=природный-value]";
    private static final String SELECTOR_CULTIVATED_TYPE = "[data-cy=возделываемый]";
    private static final String SELECTOR_CULTIVATED_VALUE = "[data-cy=возделываемый-value]";
    private static final String SELECTOR_UNAVAILABLE_TYPE = "[data-cy=недоступный]";
    private static final String SELECTOR_UNAVAILABLE_VALUE = "[data-cy=недоступный-value]";
    private static final String SELECTOR_NONE_TYPE = "[data-cy=не определен]";
    private static final String SELECTOR_NONE_VALUE = "[data-cy=не определен-value]";
    private static final String SELECTOR_CALCULATION_STATUS = "[data-cy=land-use-calculation-status]";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator landuseTitle;
    private final Locator streetType;
    private final Locator streetValue;
    private final Locator commercialType;
    private final Locator commercialValue;
    private final Locator industrialType;
    private final Locator industrialValue;
    private final Locator residentialType;
    private final Locator residentialValue;
    private final Locator natureType;
    private final Locator natureValue;
    private final Locator cultivatedType;
    private final Locator cultivatedValue;
    private final Locator unavailableType;
    private final Locator unavailableValue;
    private final Locator noneType;
    private final Locator noneValue;
    private final Locator calculationStatus;

    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getLanduseTitle() {
        return landuseTitle;
    }

    public Locator getStreetType() {
        return streetType;
    }

    public Locator getStreetValue() {
        return streetValue;
    }
    public Locator getCommercialType() {
        return commercialType;
    }

    public Locator getCommercialValue() {
        return commercialValue;
    }

    public Locator getIndustrialType() {
        return industrialType;
    }
    public Locator getIndustrialValue() {
        return industrialValue;
    }
    public Locator getResidentialType() {
        return residentialType;
    }
    public Locator getResidentialValue() {
        return residentialValue;
    }
    public Locator getNatureType() {
        return natureType;
    }
    public Locator getNatureValue() {
        return natureValue;
    }
    public Locator getCultivatedType() {
        return cultivatedType;
    }
    public Locator getCultivatedValue() {
        return cultivatedValue;
    }
    public Locator getUnavailableType() {
        return unavailableType;
    }
    public Locator getUnavailableValue() {
        return unavailableValue;
    }
    public Locator getNoneType() {
        return noneType;
    }
    public Locator getNoneValue() {
        return noneValue;
    }
    public Locator getCalculationStatus() {
        return calculationStatus;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public LanduseLayer() {
        landuseTitle = page.locator(SELECTOR_LANDUSE_TITLE);
        streetType = page.locator(SELECTOR_STREET_TYPE);
        streetValue = page.locator(SELECTOR_STREET_VALUE);
        commercialType = page.locator(SELECTOR_COMMERCIAL_TYPE);
        commercialValue = page.locator(SELECTOR_COMMERCIAL_VALUE);
        industrialType = page.locator(SELECTOR_INDUSTRIAL_TYPE);
        industrialValue = page.locator(SELECTOR_INDUSTRIAL_VALUE);
        residentialType = page.locator(SELECTOR_RESIDENTIAL_TYPE);
        residentialValue = page.locator(SELECTOR_RESIDENTIAL_VALUE);
        natureType = page.locator(SELECTOR_NATURE_TYPE);
        natureValue = page.locator(SELECTOR_NATURE_VALUE);
        cultivatedType = page.locator(SELECTOR_CULTIVATED_TYPE);
        cultivatedValue = page.locator(SELECTOR_CULTIVATED_VALUE);
        unavailableType = page.locator(SELECTOR_UNAVAILABLE_TYPE);
        unavailableValue = page.locator(SELECTOR_UNAVAILABLE_VALUE);
        noneType = page.locator(SELECTOR_NONE_TYPE);
        noneValue = page.locator(SELECTOR_NONE_VALUE);
        calculationStatus = page.locator(SELECTOR_CALCULATION_STATUS);
    }
}
