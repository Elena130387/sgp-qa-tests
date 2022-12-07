package frontend.header;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.SgpMainPage;
import util.JunitExtension;
import util.Util;

import java.util.Arrays;
import java.util.List;

import static util.Constants.BASE_MAP_TYPE;


@ExtendWith(JunitExtension.class)
public class ChooseMapTypeTest {
    static SgpMainPage sgpMainPage;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MainPageHeaderTest/";

    @BeforeAll
    static void openSgpMainPage() {
        sgpMainPage = new SgpMainPage().openMainPageWithHeaderWait();
        sgpMainPage.selectDefaultSettings();
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
