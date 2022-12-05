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

    public DetailedShapePage openPageWithAsideTrueAndMapWait(int SHAPE_ID, String mapZoom) {
        openPageWithAsideTrue(SHAPE_ID);
        waitForMapAndZoom(mapZoom);
        return this;
    }

    public DetailedShapePage openPageWithAsideFalseAndMapWait(int SHAPE_ID, String mapZoom) {
        openPageWithAsideFalse(SHAPE_ID);
        waitForMapAndZoom(mapZoom);
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}
