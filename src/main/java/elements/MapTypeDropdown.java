package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class MapTypeDropdown extends Dropdown {
    //<editor-fold desc="Selectors">
    public static final String SELECTOR_MAP_TYPE_DROPDOWN_MENU = "[data-cy=map-style-dropdown-list]";
    private static final String SELECTOR_DROPDOWN_ITEM = "[data-cy=menuItem]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator mapTypeDropdownMenu;
    private final Locator mapTypeDropdownMenuItem;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getMapTypeDropdownMenu() {
        return mapTypeDropdownMenu;
    }

    public Locator getMapTypeDropdownMenuItem() {
        return mapTypeDropdownMenuItem;
    }

    //</editor-fold>
    private final Page page = JunitExtension.page;

    public MapTypeDropdown() {
        mapTypeDropdownMenu = page.locator(SELECTOR_MAP_TYPE_DROPDOWN_MENU);
        mapTypeDropdownMenuItem = mapTypeDropdownMenu.locator(SELECTOR_DROPDOWN_ITEM);
    }
}
