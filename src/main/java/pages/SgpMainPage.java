package pages;

import com.microsoft.playwright.Page;

import static util.Constants.SGP_URL_DEV;

public class SgpMainPage extends BasePage {

    public SgpMainPage openMainPage() {
        page.navigate(SGP_URL_DEV);
        return this;
    }

    public SgpMainPage openMainPageWithHeaderWait() {
        page.navigate(SGP_URL_DEV);
        header.waitForHeader();
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
