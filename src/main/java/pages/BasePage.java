package pages;

import com.microsoft.playwright.Page;
import elements.Dropdown;
import elements.Header;
import elements.MapControl;
import elements.ShapesPanel;
import util.JunitExtension;

import static util.Constants.BASE_MAP_TYPE;

public class BasePage {
    public final Page page = JunitExtension.page;
    public final Header header = new Header();
    public final MapControl mapControl = new MapControl();
    public final ShapesPanel shapesPanel = new ShapesPanel();

    public final Dropdown dropdown = new Dropdown();

    public void selectDefaultSettings(){
        header.waitForHeader();
        if(!header.getChooseMapType().textContent().equals(BASE_MAP_TYPE)){
            header.checkChooseMapTypeMenu();
            dropdown.clickItemByText(BASE_MAP_TYPE);
        }

        if(!header.getColorModeBtn().locator(".css-cjmj0z").isVisible()){
            header.getColorModeBtn().click();
        }
    }
}
