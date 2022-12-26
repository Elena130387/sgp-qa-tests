package frontend.shapesPanel;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import elements.ShapeCreationPanel;
import elements.ShapesPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.GeneralizedShapePage;
import pages.SgpMainPage;
import pages.ShapeCreationPage;
import util.JunitExtension;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.*;
import static util.JunitExtension.page;

@ExtendWith(JunitExtension.class)
public class ShapesPanelTest {
    private static final String SHAPE_SEARCH_INPUT_PLACEHOLDER = "Поиск области";
    private static final String SEARCH_BY_TEXT = "checkShapeSearch";
    private static final int ORDINAL_NUMBER_OF_CHECKED_SHAPE = 0;
    private static final String SHAPE_NAME_FOR_RENAMING = "(НЕ УДАЛЯТЬ) Авто-тест: checkShapeRenaming";
    private static final String ONE_POLYGON_JSON = "./src/test/resources/shapeInput/onePolygonForUiTests.json";
    private static final String SHAPE_NAME_FOR_DELETION = "checkShapeDeletion";
    private static final String SHAPE_NAME_FOR_EXPAND_OR_OPENING = "(НЕ УДАЛЯТЬ) Авто-тест: GeneralizedShapePanelTest";
    private static final int SHAPE_ID_FOR_EXPAND_OR_OPENING = 1557;

    SgpMainPage sgpMainPage;
    ShapesPanel shapesPanel;
    ShapeCreationPage shapeCreationPage;
    ShapeCreationPanel shapeCreationPanel;

    @BeforeEach
    void openShapeCreationPage() {
        sgpMainPage = new SgpMainPage().openMainPageWithShapesPanelWait();
        shapesPanel = sgpMainPage.shapesPanel;
    }

    @Test
    void checkShapesPanelCommonView() {
        assertAll(
                () -> assertThat(shapesPanel.getShapesPanelVisible()).isVisible(),
                () -> assertThat(shapesPanel.getShapeSearch()).isVisible(),
                () -> assertThat(shapesPanel.getShapeSearch()).hasAttribute("placeholder", SHAPE_SEARCH_INPUT_PLACEHOLDER),
                () -> assertThat(shapesPanel.getShapeInShapesList().nth(ORDINAL_NUMBER_OF_CHECKED_SHAPE)).isVisible(),
                () -> assertThat(shapesPanel.getRenameShapeBtn()).isHidden(),
                () -> assertThat(shapesPanel.getDeleteShapeBtn()).isHidden(),
                () -> assertThat(shapesPanel.getExpandBtn()).isHidden(),
                () -> assertThat(shapesPanel.getCollapseBtn()).isHidden()
        );
    }

    @Test
    void checkShapeSearch() {
        Locator searchResults = shapesPanel.searchShapeByName(SEARCH_BY_TEXT, true);
        assertEquals(2, searchResults.count(), "Количество найденных областей не соотвествует ожидаемому");
    }

    @Test
    void checkShapeNameTooltip() {
        String nameOfCheckedShape = shapesPanel.getShapeName().nth(ORDINAL_NUMBER_OF_CHECKED_SHAPE).textContent();
        shapesPanel.checkShapeNameTooltip(ORDINAL_NUMBER_OF_CHECKED_SHAPE, nameOfCheckedShape);
    }

    @Test
    void checkIconsAppearanceWhenHoveringMouseOverShapeName() {
        shapesPanel.getShapeInShapesList().nth(ORDINAL_NUMBER_OF_CHECKED_SHAPE).hover();
        assertAll(
                () -> assertThat(shapesPanel.getRenameShapeBtn()).isVisible(),
                () -> assertThat(shapesPanel.getDeleteShapeBtn()).isVisible(),
                () -> assertThat(shapesPanel.getExpandBtn()).isVisible(),
                () -> assertThat(shapesPanel.getCollapseBtn()).isHidden()
        );
    }

    @Test
    @Disabled //TODO: сделать data-cy селектор на эту группу элементов и можно будет включать
    void checkRenameDeleteExpandIconsTogetherOnShapesPanel() {
        shapesPanel.getShapeInShapesList().nth(ORDINAL_NUMBER_OF_CHECKED_SHAPE).hover();
        shapesPanel.checkRenameDeleteExpandIconsTogether("heckRenameDeleteExpandIconsTogetherOnShapesPanel");
    }

    @Test
    void checkRenameIconAndItsTooltipOnShapesPanel() {
        shapesPanel.getShapeInShapesList().nth(ORDINAL_NUMBER_OF_CHECKED_SHAPE).hover();
        shapesPanel.checkRenameIconAndItsTooltip("checkRenameIconAndItsTooltipOnShapesPanel");
    }

    @Test
    void checkDeleteIconAndItsTooltipOnShapesPanel() {
        shapesPanel.getShapeInShapesList().nth(ORDINAL_NUMBER_OF_CHECKED_SHAPE).hover();
        shapesPanel.checkDeleteIconAndItsTooltip("checkDeleteIconAndItsTooltipOnShapesPanel");
    }

    @Test
    void checkExpandIconAndItsTooltipOnShapesPanel() {
        shapesPanel.getShapeInShapesList().nth(ORDINAL_NUMBER_OF_CHECKED_SHAPE).hover();
        shapesPanel.checkExpandIconAndItsTooltip("checkExpandIconAndItsTooltipOnShapesPanel");
    }

    @Test
    void checkRenameWindowCommonViewFromShapesPanel() {
        shapesPanel.searchShapeByName(SHAPE_NAME_FOR_EXPAND_OR_OPENING, true);
        shapesPanel.getShapeName().nth(ORDINAL_NUMBER_OF_CHECKED_SHAPE).hover();
        shapesPanel.checkRenameWindowCommonView(SHAPE_NAME_FOR_EXPAND_OR_OPENING);
    }

    @Test
    void checkShapeRenamingFromShapesPanel() {
        String newShapeName = "26-12-2022 Некое уникальное имя";
        shapesPanel.searchShapeByName(SHAPE_NAME_FOR_RENAMING, true);
        shapesPanel.getShapeName().nth(ORDINAL_NUMBER_OF_CHECKED_SHAPE).hover();
        shapesPanel.checkShapeRenaming(SHAPE_NAME_FOR_RENAMING, newShapeName);

        //clean
        shapesPanel.searchShapeByName(newShapeName, false);
        shapesPanel.getShapeName().nth(ORDINAL_NUMBER_OF_CHECKED_SHAPE).hover();
        shapesPanel.renameShape(SHAPE_NAME_FOR_RENAMING);
    }

    @Test
    void checkDeleteWindowCommonViewFromShapesPanel(){
        shapesPanel.searchShapeByName(SHAPE_NAME_FOR_EXPAND_OR_OPENING, true);
        shapesPanel.getShapeName().nth(ORDINAL_NUMBER_OF_CHECKED_SHAPE).hover();
        shapesPanel.checkDeleteWindowCommonView(SHAPE_NAME_FOR_EXPAND_OR_OPENING);
    }

    @Test
    void checkShapeDeletionFromShapesPanel() {
        shapeCreationPage = new ShapeCreationPage().openShapeCreationPageWithPanelWait();
        shapeCreationPanel = shapeCreationPage.shapeCreationPanel;

        FileChooser fileChooser = page.waitForFileChooser(() -> shapeCreationPanel.getChooseFileBtn().click());
        fileChooser.setFiles(Paths.get(ONE_POLYGON_JSON));

        shapeCreationPanel.getShapeNameInput().click();
        shapeCreationPanel.getShapeNameInput().clear();
        shapeCreationPanel.getShapeNameInput().type(SHAPE_NAME_FOR_DELETION);

        shapeCreationPanel.getCreateProcessBtn().click();

        GeneralizedShapePage generalizedShapePage = new GeneralizedShapePage();
        generalizedShapePage.generalizedShapePanel.waitForProgressBar();

        shapesPanel.deleteShapeByName(SHAPE_NAME_FOR_DELETION);

        Locator searchResults = shapesPanel.searchShapeByName(SHAPE_NAME_FOR_DELETION, false);
        assertEquals(0, searchResults.count(), "Область не была удалена");
    }

    @Test
    void checkShapeExpanding(){
        shapesPanel.searchShapeByName(SHAPE_NAME_FOR_EXPAND_OR_OPENING, true);
        shapesPanel.getShapeName().nth(ORDINAL_NUMBER_OF_CHECKED_SHAPE).hover();
        shapesPanel.getExpandBtn().click();
        assertEquals(SGP_URL_DEV + SHAPE_URL_MIDDLE + SHAPE_ID_FOR_EXPAND_OR_OPENING, sgpMainPage.getPage().url(),
                "Неверный URL");
    }

    @Test
    void checkClickByShapeName(){
        shapesPanel.searchShapeByName(SHAPE_NAME_FOR_EXPAND_OR_OPENING, true);
        shapesPanel.getShapeName().click();
        assertEquals(SGP_URL_DEV + SHAPE_URL_MIDDLE + SHAPE_ID_FOR_EXPAND_OR_OPENING + SHAPE_DETAILS_URL_END,
                sgpMainPage.getPage().url(), "Неверный URL");
    }

}
