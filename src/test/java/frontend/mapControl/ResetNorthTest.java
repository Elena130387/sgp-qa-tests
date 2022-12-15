package frontend.mapControl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SgpMainPage;
import util.JunitExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static util.Constants.MAP_COMPASS_IMG_STANDART;
import static util.Util.assertTooltipInAtrTitle;

@ExtendWith(JunitExtension.class)
public class ResetNorthTest {
    SgpMainPage sgpMainPage;

    @Test
    void checkResetNorth() {
        sgpMainPage = new SgpMainPage().openMainPage();

        sgpMainPage.mapBlock.turnMapToTheLeft(2);
        String compassState = sgpMainPage.mapControl.getCompassState();
        assertNotEquals(
                MAP_COMPASS_IMG_STANDART,
                compassState,
                "Поворот карты влево не выполнен");

        sgpMainPage.mapControl.getCompassBtn().click();
        sgpMainPage.getPage().waitForTimeout(1000);

        compassState = sgpMainPage.mapControl.getCompassState();
        assertEquals(
                MAP_COMPASS_IMG_STANDART,
                compassState,
                "Поворот карты к базовому расположению не выполнен");

        assertTooltipInAtrTitle(sgpMainPage.mapControl.getCompassBtn(), "Reset North");
    }
}
