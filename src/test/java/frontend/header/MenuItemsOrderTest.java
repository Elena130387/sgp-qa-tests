package frontend.header;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMainPage;
import util.JunitExtension;

import java.util.Arrays;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ExtendWith(JunitExtension.class)
public class MenuItemsOrderTest {
    SgpMainPage sgpMainPage;
    List<String> MAP_TYPES_LIST = Arrays.asList(
            "Bing Satellite",
            "Google Satellite",
            "Mapbox Satellite",
            "Mapbox Dark",
            "Mapbox Light");

    @Test
    void checkChooseMapTypeItemsOrder() {
        sgpMainPage = new SgpMainPage().openMainPage();
        sgpMainPage.header.waitForHeader();

        sgpMainPage.header.getChooseMapType().click();
        assertThat(sgpMainPage.header.getMapTypeDropdownMenu()).isVisible();
        sgpMainPage.header.checkDropdownItemsOrder(MAP_TYPES_LIST, sgpMainPage.header.getMapTypeDropdownMenuItem());
    }
}
