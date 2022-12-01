package pages;

import com.microsoft.playwright.Page;

import static util.Constants.NEW_CALCULATION_URL_WITH_ASIDE_FALSE_URL;
import static util.Constants.NEW_CALCULATION_URL_WITH_ASIDE_TRUE_URL;

public class AddShapePage extends BasePage {

    public AddShapePage openPageWithAsideFalse() {
        page.navigate(NEW_CALCULATION_URL_WITH_ASIDE_FALSE_URL);
        return this;
    }

    public AddShapePage openPageWithAsideTrue() {
        page.navigate(NEW_CALCULATION_URL_WITH_ASIDE_TRUE_URL);
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}
