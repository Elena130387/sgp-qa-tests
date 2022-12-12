package elements.ShapeEstimatedTab;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import util.JunitExtension;

public class ObjectsLayer {
    //<editor-fold desc="Selectors">
    private static final String SELECTOR_OBJECTS_TITLE = "[data-cy=objects-title]";
    private static final String SELECTOR_SMALL_OBJECTS_TITLE = "[data-cy=малые-объекты-title]";
    private static final String SELECTOR_SMALL_OBJECTS_VALUE = "[data-cy=малые-объекты-count]";
    private static final String SELECTOR_LARGE_OBJECTS_TITLE = "[data-cy=большие-объекты-title]";
    private static final String SELECTOR_LARGE_OBJECTS_VALUE = "[data-cy=большие-объекты-count]";
    private static final String SELECTOR_OBJECTS_DROPDOWN = "[.css-1im46kq]";
    private static final String SELECTOR_SMALL_VEHICLE_VALUE = "[data-cy=small-vehicle-value]";
    private static final String SELECTOR_LARGE_VEHICLE_VALUE = "[data-cy=large-vehicle-value]";
    private static final String SELECTOR_SWIMMING_POOL_VALUE = "[data-cy=swimming-pool-value]";
    private static final String SELECTOR_ROUNDABOUT_VALUE = "[data-cy=roundabout-value]";
    private static final String SELECTOR_TENNIS_COURT_VALUE = "[data-cy=tennis-court-value]";
    private static final String SELECTOR_STORAGE_TANK_VALUE = "[data-cy=storage-tank-value]";
    private static final String SELECTOR_BASKETBALL_COURT_VALUE = "[data-cy=basketball-court-value]";
    private static final String SELECTOR_SHIP_VALUE = "[data-cy=ship-value]";
    private static final String SELECTOR_HARBOR_VALUE = "[data-cy=harbor-value]";
    private static final String SELECTOR_SOCCER_FIELD_VALUE = "[data-cy=soccer-ball-field-value]";
    private static final String SELECTOR_GR_TRACK_FIELD_VALUE = "[data-cy=ground-track-field-value]";
    private static final String SELECTOR_BASEBALL_FIELD_VALUE = "[data-cy=baseball-diamond-value]";
    private static final String SELECTOR_BRIDGE_VALUE = "[data-cy=bridge-value]";
    //</editor-fold>

    //<editor-fold desc="Elements">
    private final Locator objectsTitle;
    private final Locator smallObjectsTitle;
    private final Locator smallObjectsValue;
    private final Locator largeObjectsTitle;
    private final Locator largeObjectsValue;
    private final Locator objectsDropdown;
    private final Locator smallVehicleValue;
    private final Locator largeVehicleValue;
    private final Locator swimmingPoolValue;
    private final Locator roundaboutValue;
    private final Locator tennisCourtValue;
    private final Locator storageTankValue;
    private final Locator basketballCourtValue;
    private final Locator shipValue;
    private final Locator harborValue;
    private final Locator soccerFieldValue;
    private final Locator grTrackFieldValue;
    private final Locator baseballFieldValue;
    private final Locator bridgeValue;
    //</editor-fold>

    //<editor-fold desc="Getters">
    public Locator getObjectsTitle() {
        return objectsTitle;
    }

    public Locator getSmallObjectsTitle() {
        return smallObjectsTitle;
    }

    public Locator getSmallObjectsValue() {
        return smallObjectsValue;
    }

    public Locator getLargeObjectsTitle() {
        return largeObjectsTitle;
    }

    public Locator getLargelObjectsValue() {
        return largeObjectsValue;
    }

    public Locator getObjectsDropdown() {
        return objectsDropdown;
    }

    public Locator getSmallVehicleValue() {
        return smallVehicleValue;
    }

    public Locator getLargeVehicleValue() {
        return largeVehicleValue;
    }

    public Locator getSwimmingPoolValue() {
        return swimmingPoolValue;
    }

    public Locator getRoundaboutValue() {
        return roundaboutValue;
    }

    public Locator getTennisCourtValue() {
        return tennisCourtValue;
    }

    public Locator getStorageTankValue() {
        return storageTankValue;
    }

    public Locator getBasketballCourtValue() {
        return basketballCourtValue;
    }

    public Locator getShipValue() {
        return shipValue;
    }

    public Locator getHarborValue() {
        return harborValue;
    }

    public Locator getSoccerFieldValue() {
        return soccerFieldValue;
    }

    public Locator getGrTrackFieldValue() {
        return grTrackFieldValue;
    }

    public Locator getBaseballFieldValue() {
        return baseballFieldValue;
    }

    public Locator getBridgeValue() {
        return bridgeValue;
    }

    //</editor-fold>

    private final Page page = JunitExtension.page;

    public ObjectsLayer() {
        objectsTitle = page.locator(SELECTOR_OBJECTS_TITLE);
        smallObjectsTitle = page.locator(SELECTOR_SMALL_OBJECTS_TITLE);
        smallObjectsValue = page.locator(SELECTOR_SMALL_OBJECTS_VALUE);
        largeObjectsTitle = page.locator(SELECTOR_LARGE_OBJECTS_TITLE);
        largeObjectsValue = page.locator(SELECTOR_LARGE_OBJECTS_VALUE);
        objectsDropdown = page.locator(SELECTOR_OBJECTS_DROPDOWN);
        smallVehicleValue = page.locator(SELECTOR_SMALL_VEHICLE_VALUE);
        largeVehicleValue = page.locator(SELECTOR_LARGE_VEHICLE_VALUE);
        swimmingPoolValue = page.locator(SELECTOR_SWIMMING_POOL_VALUE);
        roundaboutValue = page.locator(SELECTOR_ROUNDABOUT_VALUE);
        tennisCourtValue = page.locator(SELECTOR_TENNIS_COURT_VALUE);
        storageTankValue = page.locator(SELECTOR_STORAGE_TANK_VALUE);
        basketballCourtValue = page.locator(SELECTOR_BASKETBALL_COURT_VALUE);
        shipValue = page.locator(SELECTOR_SHIP_VALUE);
        harborValue = page.locator(SELECTOR_HARBOR_VALUE);
        soccerFieldValue = page.locator(SELECTOR_SOCCER_FIELD_VALUE);
        grTrackFieldValue = page.locator(SELECTOR_GR_TRACK_FIELD_VALUE);
        baseballFieldValue = page.locator(SELECTOR_BASEBALL_FIELD_VALUE);
        bridgeValue = page.locator(SELECTOR_BRIDGE_VALUE);
    }
}
