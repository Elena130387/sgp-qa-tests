import elements.Dropdown;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.SgpMain;
import util.JunitExtension;
import util.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.NEW_CALCULATION_URL;
import static util.Constants.SGP_URL_DEV;


@ExtendWith(JunitExtension.class)
public class SgpMainPageElementsTest {
    SgpMain mainPage;
    Dropdown dropdown = new Dropdown();
    String BASE_MAP_TYPE = "Bing Satellite";
    List<String> MAP_TYPES_LIST = Arrays.asList(
            "Bing Satellite",
            "Google Satellite",
            "Mapbox Satellite",
            "Mapbox Dark",
            "Mapbox Light");

    @BeforeEach
    void openMainPage() {
        mainPage = new SgpMain().openMainPage();
        mainPage.header.waitForHeader();
        if(!mainPage.header.getChooseMapType().textContent().equals(BASE_MAP_TYPE)){
            mainPage.header.checkChooseMapTypeMenu();
            dropdown.clickItemByText(BASE_MAP_TYPE);
        }
    }

    @Test
    void checkMainPageViewAndUrl() throws IOException {
        assertEquals(SGP_URL_DEV, mainPage.getPage().url(), "Неверный URL");
        Util.checkScreenshot("actMainPageView", "expMainPageView", "checkMainPageView");
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
    void checkAddShapeMenuItemsOrder() {
        List<String> itemsList = Arrays.asList("Создать вручную",
                "Импортировать .json");
        mainPage.header.checkAddShapeMenu();
        dropdown.checkItemsOrder(itemsList);
    }

    @Test
    void checkAddShapeMenuCreateManualClick() {
        mainPage.header.checkAddShapeMenu();
        dropdown.clickItemByText("Создать вручную");
        assertEquals(NEW_CALCULATION_URL, mainPage.getPage().url(), "Неверный URL");
        assertThat(mainPage.header.getNewShape()).isHidden();
    }

    @Test
    void checkLogoClick() {
        mainPage.getPage().navigate(NEW_CALCULATION_URL);
        mainPage.header.waitForHeader();
        assertThat(mainPage.header.getNewShape()).isHidden();
        mainPage.header.getTextLogo().click();
        assertEquals(SGP_URL_DEV, mainPage.getPage().url(), "Неверный URL");
        mainPage.header.waitForHeader();
        assertThat(mainPage.header.getNewShape()).isEnabled();
    }

    @Test
    void checkChooseMapTypeItemsOrder() {
        mainPage.header.checkChooseMapTypeMenu();
        dropdown.checkItemsOrder(MAP_TYPES_LIST);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Bing Satellite,actBingSatellite,expBingSatellite,checkBingSatelliteOn",
            "Google Satellite,actGoogleSatellite,expGoogleSatellite,checkGoogleSatelliteOn",
            "Mapbox Satellite,actMapboxSatellite,expMapboxSatellite,checkMapboxSatelliteOn",
            "Mapbox Dark,actMapboxDark,expMapboxDark,checkMapboxDarkOn",
            "Mapbox Light,actMapboxLight,expMapboxLight,checkMapboxLightOn"})
    public void checkChooseMapTypeItemClick(String mapTypeItem) throws IOException {
        List<String> mapTypeParams = Arrays.asList(mapTypeItem.split(","));
        mainPage.header.checkChooseMapTypeMenu();
        if (mapTypeParams.get(0).equals(BASE_MAP_TYPE)) {
            dropdown.clickFirstClickableItem();
            mainPage.header.checkChooseMapTypeMenu();
        }
        dropdown.clickItemByText(mapTypeParams.get(0));
        Util.checkScreenshot(mapTypeParams.get(1), mapTypeParams.get(2), mapTypeParams.get(3));
    }
}
