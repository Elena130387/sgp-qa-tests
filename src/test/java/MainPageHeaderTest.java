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
import static util.Constants.*;


@ExtendWith(JunitExtension.class)
public class MainPageHeaderTest {
    SgpMain mainPage;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MainPageHeaderTest\\";
    List<String> MAP_TYPES_LIST = Arrays.asList(
            "Bing Satellite",
            "Google Satellite",
            "Mapbox Satellite",
            "Mapbox Dark",
            "Mapbox Light");

    @BeforeEach
    void openMainPage() {
        mainPage = new SgpMain().openMainPage();
        mainPage.selectDefaultSettings();
    }

    @Test
    void checkMainPageViewAndUrl() throws IOException {
        assertEquals(SGP_URL_DEV, mainPage.getPage().url(), "Неверный URL");
        Util.checkScreenshot(
                "actMainPageView",
                "expMainPageView",
                "checkMainPageView",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkFullscreenMode() throws IOException {
        assertThat(mainPage.header.getFullScreenBtn()).isEnabled();
        mainPage.header.getFullScreenBtn().focus();
        mainPage.header.getFullScreenBtn().click();
        mainPage.getPage().pause();
        Util.checkScreenshot(
                "actFullScreenModeOn",
                "expFullScreenModeOn",
                "checkFullscreenModeOn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        mainPage.header.getFullScreenBtn().click();
        Util.checkScreenshot(
                "actFullScreenModeOff",
                "expFullScreenModeOff",
                "checkFullscreenModeOff",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkColorMode() throws IOException {
        assertThat(mainPage.header.getColorModeBtn()).isEnabled();
        mainPage.header.getColorModeBtn().click();
        Util.checkScreenshot(
                "actColorModeOn",
                "expColorModeOn",
                "checkColorModeOn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        mainPage.header.getColorModeBtn().click();
        Util.checkScreenshot(
                "actColorModeOff",
                "expColorModeOff",
                "checkColorModeOff",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkAddShapeMenuItemsOrder() {
        List<String> itemsList = Arrays.asList("Создать вручную",
                "Импортировать .json");
        mainPage.header.checkAddShapeMenu();
        mainPage.dropdown.checkItemsOrder(itemsList);
    }

    @Test
    void checkAddShapeMenuCreateManualClick() {
        mainPage.header.checkAddShapeMenu();
        mainPage.dropdown.clickItemByText("Создать вручную");
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
        mainPage.dropdown.checkItemsOrder(MAP_TYPES_LIST);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Bing Satellite,actBingSatellite,expBingSatellite,checkBingSatelliteOn,No",
            "Mapbox Dark,actMapboxDark,expMapboxDark,checkMapboxDarkOn,longWaiting",
            "Mapbox Light,actMapboxLight,expMapboxLight,checkMapboxLightOn,longWaiting",
            "Google Satellite,actGoogleSatellite,expGoogleSatellite,checkGoogleSatelliteOn,No",
            "Mapbox Satellite,actMapboxSatellite,expMapboxSatellite,checkMapboxSatelliteOn,longWaiting"})
    public void checkChooseMapTypeItemClick(String mapTypeItem) throws IOException {
        List<String> mapTypeParams = Arrays.asList(mapTypeItem.split(","));
        mainPage.header.checkChooseMapTypeMenu();
        if (mapTypeParams.get(0).equals(BASE_MAP_TYPE)) {
            mainPage.dropdown.clickFirstClickableItem();
            mainPage.header.checkChooseMapTypeMenu();
        }
        mainPage.dropdown.clickItemByText(mapTypeParams.get(0));

        if (mapTypeParams.get(4).equals("longWaiting")) {
            Util.checkScreenshotLongWaiting(
                    mapTypeParams.get(1),
                    mapTypeParams.get(2),
                    mapTypeParams.get(3),
                    EXPSCREENSHOTS_TEST_CLASS_DIR);
        } else {
            Util.checkScreenshot(
                    mapTypeParams.get(1),
                    mapTypeParams.get(2),
                    mapTypeParams.get(3),
                    EXPSCREENSHOTS_TEST_CLASS_DIR);
        }
    }
}
