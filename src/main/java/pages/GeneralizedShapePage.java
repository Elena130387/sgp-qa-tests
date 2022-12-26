package pages;

import com.microsoft.playwright.Page;
import elements.GeneralizedShapePanel;

import static util.Constants.GENERALIZED_SHAPE_PAGE_WITH_ASIDE_TRUE_URL;

public class GeneralizedShapePage extends BasePage {
    public final GeneralizedShapePanel generalizedShapePanel = new GeneralizedShapePanel();

    public GeneralizedShapePage openGeneralizedShapePageWithAsideTrue(int SHAPE_ID) {
        page.navigate(GENERALIZED_SHAPE_PAGE_WITH_ASIDE_TRUE_URL + SHAPE_ID);
        return this;
    }

    public GeneralizedShapePage openPageWithAsideFalseAndMapWait(int SHAPE_ID, String mapZoom) {
        openGeneralizedShapePageWithAsideTrue(SHAPE_ID);
        waitForMapAndZoom(mapZoom);
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}

