package pages;

import com.microsoft.playwright.Page;
import elements.Header;
import elements.MapControl;
import elements.ShapesPanel;
import util.JunitExtension;

public class BasePage {
    public final Page page = JunitExtension.page;
    public final Header header = new Header();
    public final MapControl mapControl = new MapControl();
    public final ShapesPanel shapesPanel = new ShapesPanel();
}
