package frontend.header;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMainPage;
import util.JunitExtension;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.NEW_CALCULATION_URL_WITH_ASIDE_TRUE_URL;

@ExtendWith(JunitExtension.class)
public class AddShapeBtnTest {
    SgpMainPage sgpMainPage;

    @Test
    void checkAddShapeBtnClick() {
        sgpMainPage = new SgpMainPage().openMainPageWithHeaderWait();

        sgpMainPage.header.getNewShape().click();

        assertAll(
                () -> assertEquals(NEW_CALCULATION_URL_WITH_ASIDE_TRUE_URL, sgpMainPage.getPage().url(), "Неверный URL"),
                () -> assertThat(sgpMainPage.header.getNewShape()).isHidden()
        );
    }
}
