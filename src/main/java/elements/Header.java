package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class Header {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_HEADER = ".jsx-1466478689";
    private static final String SELECTOR_NEW_SHAPE = "[data-cy=newShape]";
    private static final String SELECTOR_CHOOSE_MAP_TYPE = "[data-cy=chooseSatellite]";
    private static final String SELECTOR_TEXT_LOGO = "[data-cy=text_logo]";
    private static final String SELECTOR_FULL_SCREEN = "[data-cy=fullScreen]";
    private static final String SELECTOR_COLOR_MODE = "[data-cy=colorMode]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator header;
    private final Locator addedShapes;
    private final Locator chooseMapType;
    private final Locator textLogo;
    private final Locator fullScreenBtn;
    private final Locator colorModeBtn;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getHeader() {
        return header;
    }

    public Locator getAddedShapes() {
        return addedShapes;
    }

    public Locator getChooseMapType() {
        return chooseMapType;
    }

    public Locator getTextLogo() {
        return textLogo;
    }

    public Locator getColorModeBtn() {
        return colorModeBtn;
    }

    public Locator getFullScreenBtn() {
        return fullScreenBtn;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public Header() {
        this.header = page.locator(SELECTOR_HEADER);
        this.addedShapes = this.header.locator(SELECTOR_NEW_SHAPE);
        this.chooseMapType = this.header.locator(SELECTOR_CHOOSE_MAP_TYPE);
        this.textLogo = this.header.locator(SELECTOR_TEXT_LOGO);
        this.fullScreenBtn = this.header.locator(SELECTOR_FULL_SCREEN);
        this.colorModeBtn = this.header.locator(SELECTOR_COLOR_MODE);
    }

    public void waitForHeader(){
        this.page.waitForSelector(SELECTOR_HEADER);
    }
}
