package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class Header extends Dropdown {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_HEADER = "[data-cy=header-div]";
    private static final String SELECTOR_NEW_SHAPE = "[data-cy=create]";
    private static final String SELECTOR_CHOOSE_MAP_TYPE = "[data-cy=chooseSatellite]";
    private static final String SELECTOR_TEXT_LOGO = "[data-cy=text_logo]";
    private static final String SELECTOR_FULL_SCREEN = "[data-cy=fullScreen]";
    private static final String SELECTOR_COLOR_MODE = "[data-cy=colorMode]";
    private static final String SELECTOR_IMG_DARK_COLOR = ".css-cjmj0z";
    public static final String SELECTOR_MAP_TYPE_DROPDOWN_MENU = "[data-cy=map-style-dropdown-list]";
    private static final String SELECTOR_DROPDOWN_ITEM = "[data-cy=menuItem]";

    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator header;
    private final Locator newShape;
    private final Locator chooseMapType;
    private final Locator textLogo;
    private final Locator fullScreenBtn;
    private final Locator colorModeBtn;
    private final Locator darkModeImg;
    private final Locator mapTypeDropdownMenu;
    private final Locator mapTypeDropdownMenuItem;
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

    public Locator getFullScreenBtn() {
        return fullScreenBtn;
    }

    public Locator getDarkModeImg() {
        return darkModeImg;
    }

    public Locator getMapTypeDropdownMenu() {
        return mapTypeDropdownMenu;
    }

    public Locator getMapTypeDropdownMenuItem() {
        return mapTypeDropdownMenuItem;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public Header() {
        header = page.locator(SELECTOR_HEADER);
        newShape = header.locator(SELECTOR_NEW_SHAPE);
        chooseMapType = header.locator(SELECTOR_CHOOSE_MAP_TYPE);
        mapTypeDropdownMenu = page.locator(SELECTOR_MAP_TYPE_DROPDOWN_MENU);
        mapTypeDropdownMenuItem = mapTypeDropdownMenu.locator(SELECTOR_DROPDOWN_ITEM);
        textLogo = header.locator(SELECTOR_TEXT_LOGO);
        fullScreenBtn = page.locator(SELECTOR_FULL_SCREEN);
        colorModeBtn = header.locator(SELECTOR_COLOR_MODE);
        darkModeImg = colorModeBtn.locator(SELECTOR_IMG_DARK_COLOR);
    }

    public void waitForHeader() {
        page.waitForSelector(SELECTOR_HEADER);
    }
}
