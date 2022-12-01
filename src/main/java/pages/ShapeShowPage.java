package pages;

import com.microsoft.playwright.Page;

import static util.Constants.SHAPE_SHOW_PAGE_WITH_ASIDE_FALSE_URL;

public class ShapeShowPage extends BasePage {

    public ShapeShowPage openPageWithAsideFalse(int SHAPE_ID) {
        page.navigate(SHAPE_SHOW_PAGE_WITH_ASIDE_FALSE_URL + SHAPE_ID);
        return this;
    }

    public ShapeShowPage openPageWithAsideFalseAndMapWait(int SHAPE_ID, String standartMapZoom) {
        openPageWithAsideFalse(SHAPE_ID);
        waitStandartMapZoom(standartMapZoom);
        return this;
    }


    public Page getPage() {
        return this.page;
    }
}

