
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
    void openMainPage() throws InterruptedException {
        mainPage = new SgpMain().openMainPage();
        mainPage.getPage().waitForTimeout(1500);
    }

    @Test
    void checkLink() throws InterruptedException {
        assertEquals(SGP_URL_DEV, mainPage.getPage().url());
    }

    @Test
    void checkFullscreenMode() {
        mainPage.openFullScreen();
        mainPage.getPage().waitForTimeout(1000);
    }
}
