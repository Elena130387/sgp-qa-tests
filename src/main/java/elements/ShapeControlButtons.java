package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;
import util.Util;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Util.assertTooltip;

public class ShapeControlButtons {

    //<editor-fold desc="Selectors">
    private static final String SELECTOR_RENAME_SHAPE_BTN = "[data-cy=rename-shape-button]";
    private static final String SELECTOR_RENAME_SHAPE_TOOLTIP = "[data-tooltip=rename-shape]";
    private static final String RENAME_SHAPE_TOOLTIP_TEXT = "Переименовать";
    private static final String SELECTOR_RENAME_SHAPE_HEADER = ".css-195ax2m";
    private static final String RENAME_SHAPE_HEADER_TEXT = "Переименовать область";
    private static final String SELECTOR_RENAME_SHAPE_CLOSE_WINDOW = ".css-g2613m";
    private static final String SELECTOR_RENAME_SHAPE_INPUT = "[data-cy=inputRename]";
    private static final String SELECTOR_RENAME_SHAPE_YES = "[data-cy=rename]";
    private static final String RENAME_SHAPE_YES_TEXT = "Да, переименовать";
    private static final String SELECTOR_RENAME_SHAPE_NO = ".css-awrszv";
    private static final String RENAME_SHAPE_NO_TEXT = "Нет";

    private static final String SELECTOR_DELETE_SHAPE_BTN = "[data-cy=delete-shape-button]";
    private static final String SELECTOR_DELETE_SHAPE_TOOLTIP = "[data-tooltip=delete-shape]";
    private static final String DELETE_SHAPE_TOOLTIP_TEXT = "Удалить";
    private static final String SELECTOR_DELETE_SHAPE_HEADER = ".css-195ax2m";
    private static final String DELETE_SHAPE_HEADER_TEXT = "Удалить область";
    private static final String SELECTOR_DELETE_SHAPE_CLOSE_WINDOW = ".css-g2613m";
    private static final String SELECTOR_DELETE_SHAPE_TEXT = ".css-133bv9i";
    private static final String DELETE_SHAPE_TEXT_START = "Область «";
    private static final String DELETE_SHAPE_TEXT_END = "» будет удалена.";
    private static final String SELECTOR_DELETE_SHAPE_YES = ".css-39ifwx";
    private static final String DELETE_SHAPE_YES_TEXT = "Да, удалить";
    private static final String SELECTOR_DELETE_SHAPE_NO = ".css-awrszv";
    private static final String DELETE_SHAPE_NO_TEXT = "Нет";

    private static final String SELECTOR_EXPAND_BTN = "[data-cy=open-preview-button]";
    private static final String SELECTOR_EXPAND_TOOLTIP = "[data-tooltip=open-preview]";
    private static final String EXPAND_TOOLTIP_TEXT = "Развернуть";

    private static final String SELECTOR_COLLAPSE_BTN = "[data-cy=close-preview-button]";
    private static final String SELECTOR_COLLAPSE_TOOLTIP = "[data-tooltip=close-preview]";
    private static final String COLLAPSE_TOOLTIP_TEXT = "Свернуть";

    private static final String SELECTOR_RENAME_DELETE_EXPAND_ICONS_TOGETHER = ".css-70qvj9";

    private static final String EXPSCREENSHOTS_TEST_CLASS_DIR = "ShapeControlButtons/";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator renameShapeBtn;
    private final Locator renameShapeTooltip;
    private final Locator renameShapeHeader;
    private final Locator renameShapeCloseWindow;
    private final Locator renameShapeInput;
    private final Locator renameShapeYes;
    private final Locator renameShapeNo;
    private final Locator deleteShapeBtn;
    private final Locator deleteShapeTooltip;
    private final Locator deleteShapeHeader;
    private final Locator deleteShapeText;
    private final Locator deleteShapeCloseWindow;
    private final Locator deleteShapeYes;
    private final Locator deleteShapeNo;
    private final Locator expandBtn;
    private final Locator expandTooltip;
    private final Locator collapseBtn;
    private final Locator collapseTooltip;
    private final Locator renameDeleteExpandIconsTogether;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getRenameShapeBtn() {
        return renameShapeBtn;
    }
    public Locator getRenameShapeTooltip() {
        return renameShapeTooltip;
    }
    public Locator getRenameShapeHeader() {
        return renameShapeHeader;
    }
    public Locator getRenameShapeCloseWindow() {
        return renameShapeCloseWindow;
    }
    public Locator getRenameShapeInput() {
        return renameShapeInput;
    }
    public Locator getRenameShapeYes() {
        return renameShapeYes;
    }
    public Locator getRenameShapeNo() {
        return renameShapeNo;
    }
    public Locator getDeleteShapeBtn() {
        return deleteShapeBtn;
    }
    public Locator getDeleteShapeTooltip() {
        return deleteShapeTooltip;
    }
    public Locator getDeleteShapeHeader() {
        return deleteShapeHeader;
    }
    public Locator getDeleteShapeCloseWindow() {
        return deleteShapeCloseWindow;
    }
    public Locator getDeleteShapeText() {
        return deleteShapeText;
    }
    public Locator getDeleteShapeYes() {
        return deleteShapeYes;
    }
    public Locator getDeleteShapeNo() {
        return deleteShapeNo;
    }
    public Locator getExpandBtn() {
        return expandBtn;
    }
    public Locator getExpandTooltip() {
        return expandTooltip;
    }
    public Locator getCollapseBtn() {
        return collapseBtn;
    }
    public Locator getCollapseTooltip() {
        return collapseTooltip;
    }
    public Locator getRenameDeleteExpandIconsTogether() {
        return renameDeleteExpandIconsTogether;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public ShapeControlButtons() {
        renameShapeBtn = page.locator(SELECTOR_RENAME_SHAPE_BTN);
        renameShapeTooltip = page.locator(SELECTOR_RENAME_SHAPE_TOOLTIP);
        renameShapeHeader = page.locator(SELECTOR_RENAME_SHAPE_HEADER);
        renameShapeCloseWindow = page.locator(SELECTOR_RENAME_SHAPE_CLOSE_WINDOW);
        renameShapeInput = page.locator(SELECTOR_RENAME_SHAPE_INPUT);
        renameShapeYes = page.locator(SELECTOR_RENAME_SHAPE_YES);
        renameShapeNo = page.locator(SELECTOR_RENAME_SHAPE_NO);
        deleteShapeBtn = page.locator(SELECTOR_DELETE_SHAPE_BTN);
        deleteShapeTooltip = page.locator(SELECTOR_DELETE_SHAPE_TOOLTIP);
        deleteShapeHeader = page.locator(SELECTOR_DELETE_SHAPE_HEADER);
        deleteShapeCloseWindow = page.locator(SELECTOR_DELETE_SHAPE_CLOSE_WINDOW);
        deleteShapeText = page.locator(SELECTOR_DELETE_SHAPE_TEXT);
        deleteShapeYes = page.locator(SELECTOR_DELETE_SHAPE_YES);
        deleteShapeNo = page.locator(SELECTOR_DELETE_SHAPE_NO);
        expandBtn = page.locator(SELECTOR_EXPAND_BTN);
        expandTooltip = page.locator(SELECTOR_EXPAND_TOOLTIP);
        collapseBtn = page.locator(SELECTOR_COLLAPSE_BTN);
        collapseTooltip = page.locator(SELECTOR_COLLAPSE_TOOLTIP);
        renameDeleteExpandIconsTogether = page.locator(SELECTOR_RENAME_DELETE_EXPAND_ICONS_TOGETHER);
    }

    public void checkRenameIconAndItsTooltip(String testName) {
        Util.checkScreenshotForElement(
                renameShapeBtn,
                "actRenameIcon",
                "expRenameIcon",
                testName,
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        assertTooltip(renameShapeBtn, renameShapeTooltip, RENAME_SHAPE_TOOLTIP_TEXT);
    }

    public void checkDeleteIconAndItsTooltip(String testName) {
        Util.checkScreenshotForElement(
                deleteShapeBtn,
                "actDeleteIcon",
                "expDeleteIcon",
                testName,
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        assertTooltip(deleteShapeBtn, deleteShapeTooltip, DELETE_SHAPE_TOOLTIP_TEXT);
    }

    public void checkExpandIconAndItsTooltip(String testName) {
        Util.checkScreenshotForElement(
                expandBtn,
                "actExpandIcon",
                "expExpandIcon",
                testName,
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        assertTooltip(expandBtn, expandTooltip, EXPAND_TOOLTIP_TEXT);
    }

    public void checkCollapseIconAndItsTooltip(String testName) {
        Util.checkScreenshotForElement(
                collapseBtn,
                "actCollapseIcon",
                "expCollapseIcon",
                testName,
                EXPSCREENSHOTS_TEST_CLASS_DIR);
        assertTooltip(collapseBtn, collapseTooltip, COLLAPSE_TOOLTIP_TEXT);
    }

    public void checkRenameDeleteExpandIconsTogether(String testName) {
        Util.checkScreenshotForElement(
                renameDeleteExpandIconsTogether,
                "actRenameDeleteExpandIconsTogether",
                "expRenameDeleteExpandIconsTogether",
                testName,
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    public void checkRenameWindowCommonView(String expectedShapeName) {
        renameShapeBtn.click();
        assertAll(
                () -> assertEquals(RENAME_SHAPE_HEADER_TEXT, renameShapeHeader.textContent(),
                        "Неверный заголовок окна 'Переименовать область'"),
                () -> assertThat(renameShapeCloseWindow).isVisible(),
                () -> assertThat(renameShapeInput).hasAttribute("value", expectedShapeName),
                () -> assertEquals(RENAME_SHAPE_NO_TEXT, renameShapeNo.textContent(),
                        "Неверное название кнопки отказа от переименования области"),
                () -> assertEquals(RENAME_SHAPE_YES_TEXT, renameShapeYes.textContent(),
                        "Неверное название кнопки подтверждения переименования области")
        );
    }

    public void checkDeleteWindowCommonView(String expectedShapeName) {
        deleteShapeBtn.click();
        assertAll(
                () -> assertEquals(DELETE_SHAPE_HEADER_TEXT, deleteShapeHeader.textContent(),
                        "Неверный заголовок окна 'Удалить область'"),
                () -> assertThat(deleteShapeCloseWindow).isVisible(),
                () -> assertEquals(DELETE_SHAPE_TEXT_START + expectedShapeName + DELETE_SHAPE_TEXT_END,
                        deleteShapeText.textContent(), "Автоматически подставлено неверное название области"),
                () -> assertEquals(DELETE_SHAPE_NO_TEXT, deleteShapeNo.textContent(),
                        "Неверное название кнопки отказа от удаления области"),
                () -> assertEquals(DELETE_SHAPE_YES_TEXT, deleteShapeYes.textContent(),
                        "Неверное название кнопки подтверждения удаления области")
        );
    }
}

