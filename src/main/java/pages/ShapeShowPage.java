package pages;

import com.microsoft.playwright.Page;

import static util.Constants.SGP_URL_DEV;

public class ShapeShowPage extends BasePage {

    public ShapeShowPage openShapeShowPage(int SHAPE_ID) {
        page.navigate(SGP_URL_DEV + "?showAside=false&shape=" + SHAPE_ID);
        return this;
    }

    public ShapeShowPage openShapeShowPageWithMapWait(int SHAPE_ID) {
        openShapeShowPage(SHAPE_ID);
 //       mapBlock.waitForMap();
        mapBlock.waitForStandartZoom();
        mapControl.getCompassBtn().click();
        page.waitForTimeout(1000);
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}
