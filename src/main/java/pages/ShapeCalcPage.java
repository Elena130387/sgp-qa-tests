package pages;

import com.microsoft.playwright.Page;

import static util.Constants.*;

public class ShapeCalcPage extends BasePage {

    public ShapeCalcPage openShapeCalcPage(int SHAPE_ID) {
        page.navigate(SHAPE_CHOOSE_URL_MID_TRUE + SHAPE_ID + SHAPE_DETAILS_URL_END);
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
