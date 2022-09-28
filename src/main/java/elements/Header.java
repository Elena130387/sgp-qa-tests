package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Header {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_HEADER = ".jsx-1466478689";
    private static final String SELECTOR_NEW_SHAPE = "[data-cy=newShape]";
    private static final String SELECTOR_CHOOSE_MAP_TYPE = "[data-cy=chooseSatellite]";
    private static final String SELECTOR_TEXT_LOGO = "[data-cy=text_logo]";
    private static final String SELECTOR_FULL_SCREEN = "[data-cy=fullScreen]";
    private static final String SELECTOR_COLOR_MODE = "[data-cy=colorMode]";
    private static final String SELECTOR_DROPDOWN_MENU = ".css-1jwepc6";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator header;
    private final Locator newShape;
    private final Locator chooseMapType;
    private final Locator textLogo;
    private final Locator fullScreenBtn;
    private final Locator colorModeBtn;
    private final Locator addShapeDropdownMenu;
    private final Locator chooseMapTypeDropdownMenu;
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


    public Locator getAddShapeDropdownMenu() {
        return addShapeDropdownMenu;
    }

    public Locator getChooseMapTypeDropdownMenu() {
        return chooseMapTypeDropdownMenu;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public Header() {
        this.header = page.locator(SELECTOR_HEADER);
        this.newShape = this.header.locator(SELECTOR_NEW_SHAPE);
        this.chooseMapType = this.header.locator(SELECTOR_CHOOSE_MAP_TYPE);
        this.textLogo = this.header.locator(SELECTOR_TEXT_LOGO);
        this.fullScreenBtn = this.header.locator(SELECTOR_FULL_SCREEN);
        this.colorModeBtn = this.header.locator(SELECTOR_COLOR_MODE);
        this.addShapeDropdownMenu = this.newShape.locator("..").locator("..").locator(SELECTOR_DROPDOWN_MENU);
        this.chooseMapTypeDropdownMenu = this.chooseMapType.locator("..").locator("..").locator(SELECTOR_DROPDOWN_MENU);
    }

    public void waitForHeader(){
        this.page.waitForSelector(SELECTOR_HEADER);
    }

    public void checkAddShapeMenu(){
        assertThat(this.newShape).isEnabled();
        this.newShape.click();
        assertThat(this.addShapeDropdownMenu).isVisible();
    }

    public void checkChooseMapTypeMenu(){
        assertThat(this.chooseMapType).isEnabled();
        this.chooseMapType.click();
        assertThat(this.chooseMapTypeDropdownMenu).isVisible();
    }
}
