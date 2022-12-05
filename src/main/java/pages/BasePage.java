package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import elements.Header;
import elements.MapBlock;
import elements.MapControl;
import elements.ShapesPanel;
import util.JunitExtension;

import static util.Constants.*;

public class BasePage {
    public final Page page = JunitExtension.page;
    public final Header header = new Header();
    public final MapControl mapControl = new MapControl();
    public final MapBlock mapBlock = new MapBlock();
    public final ShapesPanel shapesPanel = new ShapesPanel();

    public void selectDefaultSettings() {
        Integer height = page.viewportSize().height;
        Integer width = page.viewportSize().width;

        // page size check
        if (!height.equals(STANDART_PAGE_HEIGHT) || !width.equals(STANDART_PAGE_WIDTH)) {
            page.setViewportSize(STANDART_PAGE_HEIGHT, STANDART_PAGE_WIDTH);
        }

        // checking the base type of the map
        header.waitForHeader();
        if (!header.getChooseMapType().textContent().equals(BASE_MAP_TYPE)) {
            header.getChooseMapType().click();
            header.clickDropdownItemByText(BASE_MAP_TYPE, header.getMapTypeDropdownMenuItem());
        }

        // checking the dark mode is off
        if (!header.getDarkModeImg().isVisible()) {
            header.getColorModeBtn().click();
        }

        // checking that the map is not rotated
        if (!mapControl.getCompassState().equals(MAP_COMPASS_IMG_STANDART)) {
            mapControl.getCompassBtn().click();
            page.waitForTimeout(1000);
        }

        // checking that the shapes panel is open
        if (shapesPanel.getShapesPanelVisible().isHidden()) {
            mapControl.getPolygonsSectionBtn().click();
        }
    }

    public void waitForMapAndZoom(String mapZoom) {
        mapBlock.waitForMap();
        mapBlock.waitForMapZoom(mapZoom);
    }

    public void openShapeWithName(String name) {
        shapesPanel.getAddedShapes().filter(new Locator.FilterOptions().setHasText(name)).click();
    }
}
