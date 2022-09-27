import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMain;
import util.JunitExtension;
import util.Util;
import java.io.IOException;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.NEW_CALCULATION_URL;
import static util.Constants.SGP_URL_DEV;


@ExtendWith(JunitExtension.class)
public class SgpMainPageElementsTest {
    SgpMain mainPage;

    @BeforeEach
    void openMainPage() {
        mainPage = new SgpMain().openMainPage();
        mainPage.header.waitForHeader();
    }

    @Test
    void checkLink() {
        assertEquals(SGP_URL_DEV, mainPage.getPage().url(), "Неверный URL");
    }

    @Test
    void checkFullscreenMode() throws IOException {
        assertThat(mainPage.header.getFullScreenBtn()).isEnabled();
        mainPage.header.getFullScreenBtn().click();
        Util.checkScreenshot("actFullScreenModeOn", "expFullScreenModeOn", "checkFullscreenModeOn");

        mainPage.header.getFullScreenBtn().click();
        Util.checkScreenshot("actFullScreenModeOff", "expFullScreenModeOff", "checkFullscreenModeOff");
    }

    @Test
    void checkColorMode() throws IOException {
        assertThat(mainPage.header.getColorModeBtn()).isEnabled();
        mainPage.header.getColorModeBtn().click();
        Util.checkScreenshot("actColorModeOn", "expColorModeOn", "checkColorModeOn");

        mainPage.header.getColorModeBtn().click();
        Util.checkScreenshot("actColorModeOff", "expColorModeOff", "checkColorModeOff");
    }

    @Test
    void checkAddShapeMenuItemsOrder()  {
        mainPage.header.checkAddShapePopupMenu();
        mainPage.header.checkMenuItemText(0, "Создать вручную");
        mainPage.header.checkMenuItemText(1, "Импортировать .json");
    }

    @Test
    void checkAddShapeMenuCreateManualClick()  {
        mainPage.header.checkAddShapePopupMenu();
        mainPage.header.getPopupMenuItem().nth(0).click();
        assertEquals(NEW_CALCULATION_URL, mainPage.getPage().url(), "Неверный URL");
        assertThat(mainPage.header.getNewShape()).isHidden();
    }
}
