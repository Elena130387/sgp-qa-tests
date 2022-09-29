package pages;

import com.microsoft.playwright.Page;
import static util.Constants.SGP_URL_DEV;

public class SgpMain extends BasePage {

    public SgpMain openMainPage() {
        page.navigate(SGP_URL_DEV);
        return this;
    }

    public Page getPage() {
        return this.page;
    }
}
