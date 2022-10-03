import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMain;
import util.JunitExtension;
import util.Util;

import java.io.IOException;

@ExtendWith(JunitExtension.class)
public class MapControlTest {
    SgpMain mainPage;
    private final String EXPSCREENSHOTS_TEST_CLASS_DIR = "MapControlTest\\";

    @BeforeEach
    void openMainPage() {
        mainPage = new SgpMain().openMainPage();
        mainPage.selectDefaultSettings();
    }

    @Test
    void checkZoom() throws IOException {
        mainPage.mapControl.clickZoomIn(1);
        Util.checkScreenshot(
                "actMainPageZoomIn",
                "expMainPageZoomIn",
                "checkMainPageZoomIn",
                EXPSCREENSHOTS_TEST_CLASS_DIR);

        mainPage.mapControl.clickZoomOut(2);
        Util.checkScreenshot(
                "actMainPageZoomOut",
                "expMainPageZoomOut",
                "checkMainPageZoomOut",
                EXPSCREENSHOTS_TEST_CLASS_DIR);
    }
}
