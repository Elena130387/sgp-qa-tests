package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

import static util.Constants.SGP_URL_DEV;

public class SgpMain {
    private final Page page;
    private final Locator addedShapes;
    private final Locator chooseMapType;
    private final Locator textLogo;
    private final Locator fullScreenBtn;
    private final Locator colorModeBtn;
    private final Locator polygonSectionBtn;
    private final Locator geoSearch;
    private final Locator zoomInBtn;
    private final Locator zoomOutBtn;
    private final Locator compassBtn;
    private final Locator coverageBtn;
    private final Locator globalGridBtn;
    private final Locator gridLinesBtn;

    public SgpMain() {
        this.page = JunitExtension.page;
        addedShapes = page.locator("[data-cy=newShape]");
        chooseMapType = page.locator("[data-cy=chooseSatellite]");
        textLogo = page.locator("[data-cy=text_logo]");
        fullScreenBtn = page.locator("[data-cy=fullScreen]");
        colorModeBtn = page.locator("[data-cy=colorMode]");
        polygonSectionBtn = page.locator("[data-cy=polygon]");
        geoSearch = page.locator("[data-cy=geoSearch]");
        zoomOutBtn = page.locator(".mapboxgl-ctrl-zoom-out");
        zoomInBtn = page.locator(".mapboxgl-ctrl-zoom-in");
        compassBtn = page.locator(".mapboxgl-ctrl-compass");
        coverageBtn = page.locator("[data-cy=coverage]");
        globalGridBtn = page.locator("[data-cy=globalGrid]");
        gridLinesBtn = page.locator("[data-cy=gridLines]");
    }

    public SgpMain openMainPage() {
        this.page.navigate(SGP_URL_DEV);
        return new SgpMain();
    }

    public Page getPage() {
        return this.page;
    }

    public SgpMain openFullScreen() {
        this.fullScreenBtn.click();
        return new SgpMain();
    }
}
