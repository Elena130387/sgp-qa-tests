package pages;

import com.microsoft.playwright.Page;

import static util.Constants.SGP_URL_DEV;

public class ShapeCalcPage extends BasePage {

    public ShapeCalcPage openShapeCalcPage(String SHAPE_LINK) {
        page.navigate(SGP_URL_DEV + SHAPE_LINK);
        return this;
    }

    public ShapeCalcPage openShapeCalcPageWithMapWait(String SHAPE_LINK) {
        openShapeCalcPage(SHAPE_LINK);
        mapBlock.waitForMap();
        mapBlock.waitForStandartZoom();
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}
