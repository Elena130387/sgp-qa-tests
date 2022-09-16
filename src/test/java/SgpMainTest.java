import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMain;
import util.JunitExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.SGP_URL_DEV;

@ExtendWith(JunitExtension.class)

public class SgpMainTest {
    @Test
    void openMainPage() throws InterruptedException {
        var page = new SgpMain().openMainPage();
        page.getPage().waitForTimeout(1500);
        assertEquals(SGP_URL_DEV, page.getPage().url());
    }

    @Test
    void checkFullscreenMode() {
        var mainPage = new SgpMain().openMainPage();
        mainPage.getPage().waitForTimeout(1000);
        mainPage.openFullScreen();
        mainPage.getPage().waitForTimeout(1000);
    }
}
