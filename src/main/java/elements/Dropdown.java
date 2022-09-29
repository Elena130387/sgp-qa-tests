package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Dropdown {

    //<editor-fold desc="Selectors">
    private static final String SELECTOR_DROPDOWN_ITEM = "[data-cy=menuItem]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator dropdownMenuItem;
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public Dropdown() {
        this.dropdownMenuItem = page.locator(SELECTOR_DROPDOWN_ITEM);
    }

    public void clickItemByText(String itemText) {
        this.dropdownMenuItem.filter(new Locator.FilterOptions().setHasText(itemText)).click();
    }

    public void checkItemsOrder(List<String> itemsList) {
        for (int itemNum = 0; itemNum < itemsList.size(); ++itemNum) {
            assertEquals(itemsList.get(itemNum),
                    this.dropdownMenuItem.nth(itemNum).textContent(),
                    "Wrong text for the " + itemNum + " position in dropdown menu");
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
            fail("There are no clickable item in the dropdown list");
        }
    }
}
