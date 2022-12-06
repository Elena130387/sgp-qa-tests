package pages;

import com.microsoft.playwright.Page;
import elements.ChangeShapeMenuBtn;
import elements.ShapeData;

import static util.Constants.SHAPE_SHOW_PAGE_WITH_ASIDE_FALSE_URL;

public class ShapeShowPage extends BasePage {
    public final ShapeData shapeData = new ShapeData();
    public final ChangeShapeMenuBtn changeShapeMenuBtn = new ChangeShapeMenuBtn();

    public ShapeShowPage openPageWithAsideFalse(int SHAPE_ID) {
        page.navigate(SHAPE_SHOW_PAGE_WITH_ASIDE_FALSE_URL + SHAPE_ID);
        return this;
    }

    public ShapeShowPage openPageWithAsideFalseAndMapWait(int SHAPE_ID, String mapZoom) {
        openPageWithAsideFalse(SHAPE_ID);
        waitForMapAndZoom(mapZoom);
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}

