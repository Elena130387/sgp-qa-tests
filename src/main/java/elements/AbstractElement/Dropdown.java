package elements.AbstractElement;

import com.microsoft.playwright.Locator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class Dropdown {

    public void clickDropdownItemByText(String itemText, Locator dropdownMenuItem) {
        dropdownMenuItem.filter(new Locator.FilterOptions().setHasText(itemText)).click();
    }

    public void checkDropdownItemsOrder(List<String> itemsList, Locator dropdownMenuItem) {
        for (int itemNum = 0; itemNum < itemsList.size(); ++itemNum) {
            assertEquals(itemsList.get(itemNum),
                    dropdownMenuItem.nth(itemNum).textContent(),
                    "Текст позиции " + itemNum + " в выпадающем списке не соответствует ожиданию");
        }
    }

    public void clickDropdownFirstClickableItem(Locator dropdownMenuItem) {
        int itemNum;
        int menuItemCount = dropdownMenuItem.count();

        for (itemNum = 0; itemNum <= menuItemCount; ++itemNum) {
            if (dropdownMenuItem.nth(itemNum).isEnabled()) {
                dropdownMenuItem.nth(itemNum).click();
                break;
            }
        }

        if (itemNum > menuItemCount) {
            fail("В выпадающем списке нет элементов для нажатия");
        }
    }
}
