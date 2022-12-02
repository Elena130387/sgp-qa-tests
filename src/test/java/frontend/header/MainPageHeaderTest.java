package frontend.header;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.SgpMainPage;
import util.JunitExtension;
import util.Util;

import java.util.Arrays;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static util.Constants.BASE_MAP_TYPE;


@ExtendWith(JunitExtension.class)
public class MainPageHeaderTest {
    SgpMainPage sgpMainPage;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MainPageHeaderTest/";

    @BeforeEach
    void openMainPage() {
        sgpMainPage = new SgpMainPage().openMainPage();
        sgpMainPage.selectDefaultSettings();

        //close the list of created shapes if it is necessary
        if (sgpMainPage.shapesPanel.getShapesPanelVisible().isVisible()) {
            sgpMainPage.mapControl.getPolygonsSectionBtn().click();
        }
    }

/*  FIREFOX: When running a test with authorization through an account
    Microsoft (i.e without a file storage-state.json), the button FullScreenMod is not pressed,
    although the element locator is determined correctly, the test falls. When starting a test
    with an already generated file storage-state.json, the button FullScreenMod is pressed,
    the test does not fall.

    CHROME: When starting the test in the mode setHeadless(true), the button FullScreenMod is not
    pressed, the test falls. When starting the test in the mode setHeadless(false), the button
    FullScreenMod is pressed, the test does not fall. The presence or absence of the file
    storage-state.json does not matter when running the test in this browser.

    A task has been created to solve the problem:
    https://dev.azure.com/Syncretis/SmartGeoPlatform-Ecomonitoring/_workitems/edit/23939
    */

    /* In the browser Firefox, when running the test checkFullscreenMode(), the screenshot
   for the mode FullScreenModeOn does not fit on the screen. For this reason, for the browser
   Firefox, screen sizes are increased relative to standard sizes.
   */

    @Test
    void checkFullscreenMode() {

        assertThat(sgpMainPage.header.getFullScreenBtn()).isEnabled();
        sgpMainPage.header.getFullScreenBtn().focus();
        sgpMainPage.header.getFullScreenBtn().click();
        Util.checkScreenshotForElement(
                sgpMainPage.header.getFullScreenBtn(),
                "actFullScreenModeOn",
                "expFullScreenModeOn",
                "checkFullScreenModeOn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        sgpMainPage.header.getFullScreenBtn().click();
        Util.checkScreenshotForElement(
                sgpMainPage.header.getFullScreenBtn(),
                "actFullScreenModeOff",
                "expFullScreenModeOff",
                "checkFullScreenModeOff",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @Test
    void checkColorMode() {
        assertThat(sgpMainPage.header.getColorModeBtn()).isEnabled();
        sgpMainPage.header.getColorModeBtn().click();
        Util.checkScreenshotWithWait(
                "actColorModeOn",
                "expColorModeOn",
                "checkColorModeOn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        sgpMainPage.header.getColorModeBtn().click();
        Util.checkScreenshotWithWait(
                "actColorModeOff",
                "expColorModeOff",
                "checkColorModeOff",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Bing Satellite,actBingSatellite,expBingSatellite,checkBingSatelliteOn,No",
            "Mapbox Dark,actMapboxDark,expMapboxDark,checkMapboxDarkOn,longWaiting",
            "Mapbox Light,actMapboxLight,expMapboxLight,checkMapboxLightOn,longWaiting",
            "Google Satellite,actGoogleSatellite,expGoogleSatellite,checkGoogleSatelliteOn,No",
            "Mapbox Satellite,actMapboxSatellite,expMapboxSatellite,checkMapboxSatelliteOn,longWaiting"})
    public void checkChooseMapTypeItemClick(String mapTypeItem) {
        List<String> mapTypeParams = Arrays.asList(mapTypeItem.split(","));
        sgpMainPage.header.getChooseMapType().click();
        if (mapTypeParams.get(0).equals(BASE_MAP_TYPE)) {
            sgpMainPage.header.clickDropdownFirstClickableItem(sgpMainPage.header.getMapTypeDropdownMenuItem());
            sgpMainPage.header.getChooseMapType().click();
        }
        sgpMainPage.header.clickDropdownItemByText(mapTypeParams.get(0), sgpMainPage.header.getMapTypeDropdownMenuItem());

        if (mapTypeParams.get(4).equals("longWaiting")) {
            Util.checkScreenshotForElementWithLongWait(
                    sgpMainPage.mapBlock.getMap(),
                    mapTypeParams.get(1),
                    mapTypeParams.get(2),
                    mapTypeParams.get(3),
                    EXPSCREENSHOTS_TEST_CLASS_DIR);
        } else {
            Util.checkScreenshotForElementWithWait(
                    sgpMainPage.mapBlock.getMap(),
                    mapTypeParams.get(1),
                    mapTypeParams.get(2),
                    mapTypeParams.get(3),
                    EXPSCREENSHOTS_TEST_CLASS_DIR);
        }
    }
}
