package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

import static util.Constants.SGP_URL_DEV;

public class SgpMain extends BasePage {
    private final Locator polygonSectionBtn;
    private final Locator geoSearch;
    private final Locator zoomInBtn;
    private final Locator zoomOutBtn;
    private final Locator compassBtn;
    private final Locator coverageBtn;
    private final Locator globalGridBtn;
    private final Locator gridLinesBtn;

    public SgpMain() {
        this.polygonSectionBtn = page.locator("[data-cy=polygon]");
        this.geoSearch = page.locator("[data-cy=geoSearch]");
        this.zoomOutBtn = page.locator(".mapboxgl-ctrl-zoom-out");
        this.zoomInBtn = page.locator(".mapboxgl-ctrl-zoom-in");
        this.compassBtn = page.locator(".mapboxgl-ctrl-compass");
        this.coverageBtn = page.locator("[data-cy=coverage]");
        this.globalGridBtn = page.locator("[data-cy=globalGrid]");
        this.gridLinesBtn = page.locator("[data-cy=gridLines]");
    }

    public SgpMain openMainPage() {
        this.page.navigate(SGP_URL_DEV);
        return new SgpMain();
    }

    public Page getPage() {
        return this.page;
    }

    public SgpMain openFullScreen() {
       // this.page.fullScreenBtn.click();
        this.header.fullScreenBtn.click();
        return new SgpMain();
    }
}
