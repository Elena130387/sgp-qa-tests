package pages;

import com.microsoft.playwright.Page;

import static util.Constants.SGP_URL_DEV;

public class ShapeCalcPage extends BasePage {

    public ShapeCalcPage openShapeCalcPage(int SHAPE_ID) {
        page.navigate(SGP_URL_DEV + "?showAside=true&shape=" + SHAPE_ID + "&detailed=true");
        return this;
    }

    public ShapeCalcPage openShapeCalcPageWithMapWait(int SHAPE_ID) {
        openShapeCalcPage(SHAPE_ID);
        waitStandartMapZoom();
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}
