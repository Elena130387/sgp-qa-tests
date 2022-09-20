
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMain;
import util.JunitExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.SGP_URL_DEV;

@ExtendWith(JunitExtension.class)
public class SgpMainPageElementsTest {
    SgpMain mainPage;

    @BeforeEach
    void openMainPage() {
        mainPage = new SgpMain().openMainPage();
        mainPage.getPage().waitForLoadState();
    }

    @Test
    void checkLink() {
        assertEquals(SGP_URL_DEV, mainPage.getPage().url());
    }

    @Test
    void checkFullscreenMode() {
        mainPage.openFullScreen();
        mainPage.getPage().waitForTimeout(1000);
    }
}
