package pages;

import com.microsoft.playwright.Page;
import elements.*;
import util.JunitExtension;

import static util.Constants.*;

public class BasePage {
    public final Page page = JunitExtension.page;
    public final Header header = new Header();
    public final MapControl mapControl = new MapControl();
    public final MapBlock mapBlock = new MapBlock();
    public final ShapesPanel shapesPanel = new ShapesPanel();

    public final Dropdown dropdown = new Dropdown();

    public void selectDefaultSettings(){
        Integer height = page.viewportSize().height;
        Integer width = page.viewportSize().width;
        if(!height.equals(STANDART_PAGE_HEIGHT) || !width.equals(STANDART_PAGE_WIDTH)){
            page.setViewportSize(STANDART_PAGE_HEIGHT,STANDART_PAGE_WIDTH);
        }
        header.waitForHeader();
        if(!header.getChooseMapType().textContent().equals(BASE_MAP_TYPE)){
            header.checkChooseMapTypeMenu();
            dropdown.clickItemByText(BASE_MAP_TYPE);
        }

        if(!header.getDarkModeImg().isVisible()){
            header.getColorModeBtn().click();
        }
    }
}
