package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static util.Constants.SGP_URL_DEV;

public class ShapeShowPage extends BasePage {

    public ShapeShowPage openShapeShowPage(int SHAPE_ID) {
        page.navigate(SGP_URL_DEV + "?showAside=false&shape=" + SHAPE_ID);
        return this;
    }

    public ShapeShowPage openShapeShowPageWithMapWait(int SHAPE_ID, String standartMapZoom) {
        openShapeShowPage(SHAPE_ID);
        waitStandartMapZoom(standartMapZoom);
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}
