package pages;

import com.microsoft.playwright.Page;
import elements.ShapeCreationPanel;

import static util.Constants.NEW_CALCULATION_URL_WITH_ASIDE_TRUE_URL;

public class ShapeCreationPage extends BasePage {
    public final ShapeCreationPanel shapeCreationPanel = new ShapeCreationPanel();

    public ShapeCreationPage openShapeCreationPage() {
        page.navigate(NEW_CALCULATION_URL_WITH_ASIDE_TRUE_URL);
        return this;
    }

    public ShapeCreationPage openShapeCreationPageWithPanelWait(){
        openShapeCreationPage();
        shapeCreationPanel.waitForShapeCreationPanel();
        return this;
    }

    public Page getPage() {
        return this.page;
    }

    public void drawRectangleByScreenPositions(double minX, double minY, double maxX, double maxY) {
        page.mouse().click(minX, minY);
        page.mouse().click(maxX, minY);
        page.mouse().click(maxX, maxY);
        page.mouse().click(minX, maxY);
        page.mouse().click(minX, minY);
    }
}
