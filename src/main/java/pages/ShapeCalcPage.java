package pages;

import com.microsoft.playwright.Page;

import static util.Constants.SHAPE_DETAILS_URL_END;
import static util.Constants.SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL;

public class ShapeCalcPage extends BasePage {

    public ShapeCalcPage openShapeCalcPage(int SHAPE_ID) {
        page.navigate(SHAPE_SHOW_PAGE_WITH_ASIDE_TRUE_URL + SHAPE_ID + SHAPE_DETAILS_URL_END);
        return this;
    }

    public ShapeCalcPage openShapeCalcPageWithMapWait(int SHAPE_ID, String standartMapZoom) {
        openShapeCalcPage(SHAPE_ID);
        waitStandartMapZoom(standartMapZoom);
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}
