package pages;

import com.microsoft.playwright.Page;

import static util.Constants.*;

public class DetailedShapePage extends BasePage {

    public DetailedShapePage openPageWithAsideTrue(int SHAPE_ID) {
        page.navigate(SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL + SHAPE_ID + SHAPE_DETAILS_URL_END);
        return this;
    }

    public DetailedShapePage openPageWithAsideFalse(int SHAPE_ID) {
        page.navigate(SHAPE_SHOW_PAGE_WITH_ASIDE_FALSE_URL + SHAPE_ID + SHAPE_DETAILS_URL_END);
        return this;
    }

    public DetailedShapePage openPageWithAsideTrueAndMapWait(int SHAPE_ID, String standartMapZoom) {
        openPageWithAsideTrue(SHAPE_ID);
        waitStandartMapZoom(standartMapZoom);
        return this;
    }

    public DetailedShapePage openPageWithAsideFalseAndMapWait(int SHAPE_ID, String standartMapZoom) {
        openPageWithAsideFalse(SHAPE_ID);
        waitStandartMapZoom(standartMapZoom);
        return this;
    }

    public DetailedShapePage openPageWithEconomicTab(int SHAPE_ID) {
        page.navigate(SGP_URL_DEV + SHAPE_URL_MIDDLE + SHAPE_ID + SHAPE_DETAILS_URL_END + SHAPE_ECONOMIC_TAB_URL);
        return this;
    }

    public DetailedShapePage openPageWithInsuranceTab(int SHAPE_ID) {
        page.navigate(SGP_URL_DEV + SHAPE_URL_MIDDLE + SHAPE_ID + SHAPE_DETAILS_URL_END + SHAPE_INSURANCE_TAB_URL);
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}
