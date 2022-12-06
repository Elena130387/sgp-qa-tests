package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class ChangeShapeMenuBtn {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_MENU_BTN = "[data-cy=menuButton]";
    private static final String SELECTOR_RENAME_BTN = "[data-cy=rename]";
    private static final String SELECTOR_DELETE_BTN = "[data-cy=delete]";
    private static final String SELECTOR_DELETE_NO = ".css-awrszv";
    private static final String SELECTOR_DELETE_YES = ".css-39ifwx";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator menuBtn;
    private final Locator renameBtn;
    private final Locator deleteBtn;
    private final Locator deleteNo;
    private final Locator deleteYes;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getMenuBtn() {
        return menuBtn;
    }

    public Locator getRenameBtn() {
        return renameBtn;
    }

    public Locator getDeleteBtn() {
        return deleteBtn;
    }

    public Locator getDeleteNo() {
        return deleteNo;
    }

    public Locator getDeleteYes() {
        return deleteYes;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public ChangeShapeMenuBtn() {
        menuBtn = page.locator(SELECTOR_MENU_BTN);
        renameBtn = page.locator(SELECTOR_RENAME_BTN);
        deleteBtn = page.locator(SELECTOR_DELETE_BTN);
        deleteNo = page.locator(SELECTOR_DELETE_NO);
        deleteYes = page.locator(SELECTOR_DELETE_YES);
    }
}
