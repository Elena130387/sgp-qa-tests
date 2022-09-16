package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class Header {
    private final Page page;
    public final Locator addedShapes;
    public final Locator chooseMapType;
    public final Locator textLogo;
    public final Locator fullScreenBtn;
    public final Locator colorModeBtn;

    public Header() {
        this.page = JunitExtension.page;
        this.addedShapes = page.locator("[data-cy=newShape]");
        this.chooseMapType = page.locator("[data-cy=chooseSatellite]");
        this.textLogo = page.locator("[data-cy=text_logo]");
        this.fullScreenBtn = page.locator("[data-cy=fullScreen]");
        this.colorModeBtn = page.locator("[data-cy=colorMode]");
    }
}
