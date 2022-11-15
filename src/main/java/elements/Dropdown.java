package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Dropdown {

    //<editor-fold desc="Selectors">
    public static final String SELECTOR_DROPDOWN_MENU = ".css-1jwepc6";
    private static final String SELECTOR_DROPDOWN_ITEM = "[data-cy=menuItem]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator dropdownMenuItem;
    private final Locator dropdownMenu;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getDropdownMenu() {
        return dropdownMenu;
    }

    public Locator getDropdownMenuItem() {
        return dropdownMenuItem;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public Dropdown() {
        this.dropdownMenu = page.locator(SELECTOR_DROPDOWN_MENU);
        this.dropdownMenuItem = this.dropdownMenu.locator(SELECTOR_DROPDOWN_ITEM);
    }

    public void clickItemByText(String itemText) {
        this.dropdownMenuItem.filter(new Locator.FilterOptions().setHasText(itemText)).click();
    }

    public void checkItemsOrder(List<String> itemsList) {
        for (int itemNum = 0; itemNum < itemsList.size(); ++itemNum) {
            assertEquals(itemsList.get(itemNum),
                    this.dropdownMenuItem.nth(itemNum).textContent(),
                    "Текст позиции " + itemNum + " в выпадающем списке не соответствует ожиданию");
        }
    }

    public void clickFirstClickableItem() {
        int itemNum;
        int menuItemCount = this.dropdownMenuItem.count();

        for (itemNum = 0; itemNum <= menuItemCount; ++itemNum) {
            if (this.dropdownMenuItem.nth(itemNum).isEnabled()) {
                this.dropdownMenuItem.nth(itemNum).click();
                break;
            }
        }
        if (itemNum > menuItemCount) {
            fail("В выпадающем списке нет элементов для нажатия");
        }
    }
}
