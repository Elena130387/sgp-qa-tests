package elements.Header;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;
import util.Util;

public class Header {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_HEADER = "[data-cy=header-div]";
    private static final String SELECTOR_NEW_SHAPE = "[data-cy=create]";
    private static final String SELECTOR_CHOOSE_MAP_TYPE = "[data-cy=chooseSatellite]";
    private static final String SELECTOR_TEXT_LOGO = "[data-cy=text_logo]";
    private static final String SELECTOR_FULL_SCREEN = "[data-cy=fullScreen]";
    private static final String SELECTOR_FULL_SCREEN_ON_TOOLTIP = "[data-tooltip=minimize-window]";
    private static final String SELECTOR_FULL_SCREEN_OFF_TOOLTIP = "[data-tooltip=maximize-window]";
    private static final String SELECTOR_COLOR_MODE = "[data-cy=colorMode]";
    private static final String SELECTOR_COLOR_MODE_TOOLTIP = "[data-tooltip=color-mode]";
    private static final String SELECTOR_IMG_DARK_COLOR = ".css-cjmj0z";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator header;
    private final Locator newShape;
    private final Locator chooseMapType;
    private final Locator textLogo;
    private final Locator fullScreenBtn;
    private final Locator fullScreenOnTooltip;
    private final Locator fullScreenOffTooltip;
    private final Locator colorModeBtn;
    private final Locator colorModeTooltip;
    private final Locator darkModeImg;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getHeader() {
        return header;
    }

    public Locator getNewShape() {
        return newShape;
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

    public Locator getColorModeTooltip() {
        return colorModeTooltip;
    }

    public Locator getFullScreenBtn() {
        return fullScreenBtn;
    }

    public Locator getFullScreenOnTooltip() {
        return fullScreenOnTooltip;
    }

    public Locator getFullScreenOffTooltip() {
        return fullScreenOffTooltip;
    }

    public Locator getDarkModeImg() {
        return darkModeImg;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public Header() {
        header = page.locator(SELECTOR_HEADER);
        newShape = header.locator(SELECTOR_NEW_SHAPE);
        chooseMapType = header.locator(SELECTOR_CHOOSE_MAP_TYPE);
        textLogo = header.locator(SELECTOR_TEXT_LOGO);
        fullScreenBtn = page.locator(SELECTOR_FULL_SCREEN);
        fullScreenOnTooltip = page.locator(SELECTOR_FULL_SCREEN_ON_TOOLTIP);
        fullScreenOffTooltip = page.locator(SELECTOR_FULL_SCREEN_OFF_TOOLTIP);
        colorModeBtn = header.locator(SELECTOR_COLOR_MODE);
        colorModeTooltip = page.locator(SELECTOR_COLOR_MODE_TOOLTIP);
        darkModeImg = colorModeBtn.locator(SELECTOR_IMG_DARK_COLOR);
    }

    public void waitForHeader() {
        page.waitForSelector(SELECTOR_HEADER);
    }

    public void checkFullscreenModeOnTooltip(String expText) {
        Util.assertTooltip(fullScreenBtn,fullScreenOnTooltip, expText);
    }

    public void checkFullscreenModeOffTooltip(String expText) {
        Util.assertTooltip(fullScreenBtn,fullScreenOffTooltip, expText);
    }

    public void checkColorModeTooltip(String expText) {
        Util.assertTooltip(colorModeBtn,colorModeTooltip, expText);
    }
}
