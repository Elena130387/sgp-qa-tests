package pages;

import com.microsoft.playwright.Page;
import elements.Header;
import util.JunitExtension;

public class BasePage {
    public final Page page = JunitExtension.page;
    public final Header header = new Header();
}
