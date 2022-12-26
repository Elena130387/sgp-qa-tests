package elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.Util.assertTooltip;

public class ShapesPanel extends ShapeControlButtons {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_SHAPES_PANEL_VISIBLE = "[data-cy=aside-visible]";
    private static final String SELECTOR_SHAPES_PANEL_INVISIBLE = "[data-cy=aside-invisible]";
    private static final String SELECTOR_SHAPE_IN_SHAPES_LIST = "[data-cy=shape]";
    private static final String SELECTOR_SHAPE_NAME = "[data-cy=name]";
    private static final String SELECTOR_SHAPE_NAME_TOOLTIP = "[data-tooltip=shape-name]";
    private static final String SELECTOR_SHAPE_SEARCH = "[data-cy=search]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator shapesPanelVisible;
    private final Locator shapesPanelInvisible;
    private final Locator shapeInShapesList;
    private final Locator shapeName;
    private final Locator shapeNameTooltip;
    private final Locator shapeSearch;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getShapesPanelVisible() {
        return shapesPanelVisible;
    }

    public Locator getShapesPanelInvisible() {
        return shapesPanelInvisible;
    }

    public Locator getShapeInShapesList() {
        return shapeInShapesList;
    }

    public Locator getShapeName() {
        return shapeName;
    }

    public Locator getShapeNameTooltip() {
        return shapeNameTooltip;
    }

    public Locator getShapeSearch() {
        return shapeSearch;
    }
    //</editor-fold>

    private final Page page = JunitExtension.page;

    public ShapesPanel() {
        shapesPanelVisible = page.locator(SELECTOR_SHAPES_PANEL_VISIBLE);
        shapesPanelInvisible = page.locator(SELECTOR_SHAPES_PANEL_INVISIBLE);
        shapeInShapesList = shapesPanelVisible.locator(SELECTOR_SHAPE_IN_SHAPES_LIST);
        shapeName = shapesPanelVisible.locator(SELECTOR_SHAPE_NAME);
        shapeNameTooltip = page.locator(SELECTOR_SHAPE_NAME_TOOLTIP);
        shapeSearch = page.locator(SELECTOR_SHAPE_SEARCH);
    }

    public void waitForShapesPanel() {
        page.waitForSelector(SELECTOR_SHAPES_PANEL_VISIBLE);
    }

    public void checkShapeNameTooltip(int shapeOrdinalNumber, String expText) {
        assertTooltip(shapeName.nth(shapeOrdinalNumber), shapeNameTooltip, expText);
    }

    public Locator searchShapeByName(String name, boolean assertNoResults) {
        shapeSearch.click();
        shapeSearch.clear();
        shapeSearch.type(name);
        Locator searchResults = shapeName;
        if (assertNoResults) {
            assertTrue(searchResults.count() > 0, "Поиск не дал результатов");
        }
        return searchResults;
    }

    public void deleteShapeByName(String name) {
        Locator searchResults = searchShapeByName(name, false);
        if (searchResults.count() == 0) return;
        for (int i = searchResults.count() - 1; i >= 0; --i) {
            searchResults.nth(i).hover();
            getDeleteShapeBtn().click();
            getDeleteShapeYes().click();
        }
    }

    public void renameShape(String newShapeName) {
        getRenameShapeBtn().click();
        getRenameShapeInput().click();
        getRenameShapeInput().clear();
        getRenameShapeInput().type(newShapeName);
        getRenameShapeYes().click();
    }

    public void checkShapeRenaming(String oldShapeName, String newShapeName) {
        renameShape(newShapeName);

        Locator searchByNewShapeName = searchShapeByName(newShapeName, false);
        assertEquals(1, searchByNewShapeName.count(), "После переименования область не найдена по новому имени");

        Locator searchByOldShapeName = searchShapeByName(oldShapeName, false);
        assertEquals(0, searchByOldShapeName.count(), "После переименования область найдена по старому имени");
    }

}
