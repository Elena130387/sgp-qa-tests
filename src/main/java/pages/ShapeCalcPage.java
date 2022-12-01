package pages;

import com.microsoft.playwright.Page;

import static util.Constants.*;

public class ShapeCalcPage extends BasePage {

    public ShapeCalcPage openShapeCalcPageWithAsideTrue(int SHAPE_ID) {
        page.navigate(SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL + SHAPE_ID + SHAPE_DETAILS_URL_END);
        return this;
    }

    public ShapeCalcPage openShapeCalcPageWithAsideFalse(int SHAPE_ID) {
        page.navigate(SHAPE_SHOW_PAGE_WITH_ASIDE_FALSE_URL + SHAPE_ID + SHAPE_DETAILS_URL_END);
        return this;
    }

    public ShapeCalcPage openPageWithAsideTrueAndMapWait(int SHAPE_ID, String standartMapZoom) {
        openShapeCalcPageWithAsideTrue(SHAPE_ID);
        waitStandartMapZoom(standartMapZoom);
        return this;
    }

    public ShapeCalcPage openPageWithAsideFalseAndMapWait(int SHAPE_ID, String standartMapZoom) {
        openShapeCalcPageWithAsideFalse(SHAPE_ID);
        waitStandartMapZoom(standartMapZoom);
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}
