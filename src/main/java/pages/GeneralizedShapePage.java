package pages;

import com.microsoft.playwright.Page;
import elements.ChangeShapeMenuBtn;
import elements.GeneralizedShapePanel;

import static util.Constants.SHAPE_SHOW_PAGE_WITH_ASIDE_FALSE_URL;

public class GeneralizedShapePage extends BasePage {
    public final GeneralizedShapePanel generalizedShapePanel = new GeneralizedShapePanel();
    public final ChangeShapeMenuBtn changeShapeMenuBtn = new ChangeShapeMenuBtn();

    public GeneralizedShapePage openPageWithAsideFalse(int SHAPE_ID) {
        page.navigate(SHAPE_SHOW_PAGE_WITH_ASIDE_FALSE_URL + SHAPE_ID);
        return this;
    }

    public GeneralizedShapePage openPageWithAsideFalseAndMapWait(int SHAPE_ID, String mapZoom) {
        openPageWithAsideFalse(SHAPE_ID);
        waitForMapAndZoom(mapZoom);
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}

