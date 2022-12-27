package pages;

import com.microsoft.playwright.Page;
import elements.ShapesPanel;

import static util.Constants.SGP_URL_DEV;

public class SgpMainPage extends BasePage {
    public final ShapesPanel shapesPanel = new ShapesPanel();

    public SgpMainPage openMainPage() {
        page.navigate(SGP_URL_DEV);
        return this;
    }

    public SgpMainPage openMainPageWithHeaderWait() {
        page.navigate(SGP_URL_DEV);
        header.waitForHeader();
        return this;
    }

    public SgpMainPage openMainPageWithShapesPanelWait() {
        page.navigate(SGP_URL_DEV);
        shapesPanel.waitForShapesPanel();
        return this;
    }

    public SgpMainPage openMainPageWithDefaultSettings() {
        page.navigate(SGP_URL_DEV);
        selectDefaultSettings();
        return this;
    }

    public ShapeCreationPage openShapeCreationPageAsUser() {
        page.navigate(SGP_URL_DEV);
        header.waitForHeader();
        header.getNewShape().click();
        return new ShapeCreationPage();
    }

    public Page getPage() {
        return this.page;
    }
}
