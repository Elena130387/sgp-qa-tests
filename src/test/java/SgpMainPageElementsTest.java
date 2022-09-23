import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMain;
import util.JunitExtension;
import util.Util;
import java.io.IOException;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.*;


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
        assertEquals(SGP_URL_DEV, mainPage.getPage().url());
    }

    @Test
    void checkFullscreenMode() throws IOException {
       assertThat(mainPage.header.getFullScreenBtn()).isVisible();
       mainPage.header.getFullScreenBtn().click();
       mainPage.getPage().waitForTimeout(2500);
       Util.checkScreenshot("checkFullscreenModeOn", "FullscreenModeOn", "FullscreenModeOn");
    }
}
