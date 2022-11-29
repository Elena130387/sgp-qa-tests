package frontend.header;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMain;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.NEW_CALCULATION_URL;

@ExtendWith(JunitExtension.class)
public class AddShapeManualBtnTest {
    SgpMain mainPage;

    @Test
    void checkAddShapeMenuCreateManualClick() {
        mainPage = new SgpMain().openMainPage();
        mainPage.header.checkAddShapeMenu();
        mainPage.header.clickDropdownItemByText("Создать вручную", mainPage.header.getAddShapeDropdownMenuItem());
        assertEquals(NEW_CALCULATION_URL, mainPage.getPage().url(), "Неверный URL");
        assertThat(mainPage.header.getNewShape()).isHidden();
    }
}
