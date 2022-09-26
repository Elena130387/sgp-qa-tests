package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.SGP_URL_DEV;

public class SgpMain extends BasePage {

    //<editor-fold desc="Selectors">
    private static final String SELECTOR_ADDED_SHAPES = "[data-cy=shape]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator addedShapes;
    //</editor-fold>

    public SgpMain() {
        addedShapes = page.locator("[data-cy=shape]");
    }

    public SgpMain openMainPage() {
        page.navigate(SGP_URL_DEV);
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}
