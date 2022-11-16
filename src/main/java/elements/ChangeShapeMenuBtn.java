package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class ChangeShapeMenuBtn {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_MENU_BTN = "[data-cy=menuButton]";
    private static final String SELECTOR_RENAME_BTN = "[data-cy=rename]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator menuBtn;
    private final Locator renameBtn;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getMenuBtn() {
        return menuBtn;
    }

    public Locator getRenameBtn() {
        return renameBtn;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public ChangeShapeMenuBtn() {
        menuBtn = page.locator(SELECTOR_MENU_BTN);
        renameBtn = menuBtn.locator(SELECTOR_RENAME_BTN);
    }
}
