package frontend.header;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMain;
import util.JunitExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(JunitExtension.class)
public class MenuItemsOrderTest {
    SgpMain mainPage;
    List<String> MAP_TYPES_LIST = Arrays.asList(
            "Bing Satellite",
            "Google Satellite",
            "Mapbox Satellite",
            "Mapbox Dark",
            "Mapbox Light");

    @BeforeEach
    void openMainPage() {
        mainPage = new SgpMain().openMainPage();
    }

    @Test
    void checkAddShapeMenuItemsOrder() {
        List<String> itemsList = Arrays.asList("Создать вручную",
                "Импортировать .json");
        mainPage.header.checkAddShapeMenu();
        mainPage.header.checkDropdownItemsOrder(itemsList, mainPage.header.getAddShapeDropdownMenuItem());
    }


    @Test
    void checkChooseMapTypeItemsOrder() {
        mainPage.header.checkChooseMapTypeMenu();
        mainPage.header.checkDropdownItemsOrder(MAP_TYPES_LIST, mainPage.header.getMapTypeDropdownMenuItem());
    }
}
