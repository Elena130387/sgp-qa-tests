package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class InsuranceTab {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_INSURANCE_TITLE = "[data-cy=insurance-exposure-title]";
    private static final String SELECTOR_RESIDENTIAL_TYPE = "[data-cy=insurance-exposure-Жилой]";
    private static final String SELECTOR_RESIDENTIAL_VALUE = "[data-cy=insurance-exposure-Жилой-value]";
    private static final String SELECTOR_COMMERCIAL_TYPE = "[data-cy=insurance-exposure-Коммерческий]";
    private static final String SELECTOR_COMMERCIAL_VALUE = "[data-cy=insurance-exposure-Коммерческий-value]";
    private static final String SELECTOR_INDUSTRIAL_TYPE = "[data-cy=insurance-exposure-Промышленный]";
    private static final String SELECTOR_INDUSTRIAL_VALUE = "[data-cy=insurance-exposure-Промышленный-value]";
    private static final String SELECTOR_AGRICULTURAL_TYPE = "[data-cy=insurance-exposure-Сельскохозяйственный]";
    private static final String SELECTOR_AGRICULTURAL_VALUE = "[data-cy=insurance-exposure-Сельскохозяйственный-value]";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator insuranceTitle;
    private final Locator residentialType;
    private final Locator residentialValue;
    private final Locator commercialType;
    private final Locator commercialValue;
    private final Locator industrialType;
    private final Locator industrialValue;
    private final Locator agriculturalType;
    private final Locator agriculturalValue;

    //</editor-fold>

    //<editor-fold desc="Getters">

    public Locator getInsuranceTitle() {
        return insuranceTitle;
    }

    public Locator getResidentialType() {
        return residentialType;
    }

    public Locator getResidentialValue() {
        return residentialValue;
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

    public Locator getAgriculturalType() {
        return agriculturalType;
    }

    public Locator getAgriculturalValue() {
        return agriculturalValue;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public InsuranceTab() {
        insuranceTitle = page.locator(SELECTOR_INSURANCE_TITLE);
        residentialType = page.locator(SELECTOR_RESIDENTIAL_TYPE);
        residentialValue = page.locator(SELECTOR_RESIDENTIAL_VALUE);
        commercialType = page.locator(SELECTOR_COMMERCIAL_TYPE);
        commercialValue = page.locator(SELECTOR_COMMERCIAL_VALUE);
        industrialType = page.locator(SELECTOR_INDUSTRIAL_TYPE);
        industrialValue = page.locator(SELECTOR_INDUSTRIAL_VALUE);
        agriculturalType = page.locator(SELECTOR_AGRICULTURAL_TYPE);
        agriculturalValue = page.locator(SELECTOR_AGRICULTURAL_VALUE);

    }
}
