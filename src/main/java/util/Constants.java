package util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {

    public static final String SGP_URL_DEV = "https://sgp-dev.syncretis.com/";

    public static final int CALCULATION_TIMEOUT_SEC = 60;


    //<editor-fold desc="Frontend tests urls and dirs">
    public static final String SHAPE_CHOOSE_URL_MID = SGP_URL_DEV + "?shape=";
    public static final String SHAPE_CHOOSE_URL_MID_TRUE = SGP_URL_DEV + "?showAside=true&shape=";

    public static final String SHAPE_DETAILS_URL_END = "&detailed=true";

    public static final String NEW_CALCULATION_URL = SGP_URL_DEV + "?newCalculation=true&showAside=true";

    public static final String SHAPE_SHOW_PAGE_URL = SGP_URL_DEV + "?showAside=false&shape=";

    public static final Path STORAGE_PATH = Paths.get("storage-state.json");

    public static final String FAILURE_SCREENSHOTS_DIR = "failureScreenshots/";

    public static final String EXPECTED_SCREENSHOTS_DIR = "expectedScreenshots/";
    //</editor-fold>

    //<editor-fold desc="Basic UI Settings">
    public static final String BASE_MAP_TYPE = "Google Satellite";

    public static final int STANDART_PAGE_HEIGHT = 1280;

    public static final int STANDART_PAGE_WIDTH = 720;

    public static final String MAP_COMPASS_IMG_STANDART = "transform: rotate(0deg)";

    public static final String MAP_ZOOM_STANDART = "300 m";
    //</editor-fold>

    //<editor-fold desc="Basic urls for project's models">
    public static final String ESTIMATOR_URL = "/estimator";

    public static final String CALC_MANAGEMENT_URL = "/calculation-management";

    public static final String IMAGE_SERVICE_URL = "/image-service";

    public static final String POLYGON_SPLITTER_URL = "/polygon-splitter";

    public static final String FOOTPRINT_MODEL_URL = "/footprint-model";

    public static final String BUILDING_HEIGHT_MODEL_URL = "/bhm";

    public static final String POPULATION_MODEL_URL = "/pm";

    public static final String LAND_USE_MODEL_URL = "/lum";
    //</editor-fold>

    //<editor-fold desc="Endpoints">
    public static final String EXECUTIONS_EP = "/executions";

    public static final String SHAPES_EP = "/shapes";

    public static final String ESTIMATOR_EXECUTIONS_EP = ESTIMATOR_URL + EXECUTIONS_EP;

    public static final String ESTIMATOR_JOB_EXECUTION_ID_EP = ESTIMATOR_EXECUTIONS_EP + "/{id}";

    public static final String ESTIMATOR_JOB_EXECUTION_STATUS_EP = ESTIMATOR_JOB_EXECUTION_ID_EP + "/status";

    public static final String ESTIMATOR_JOB_EXECUTION_STOP = ESTIMATOR_JOB_EXECUTION_ID_EP + ":stop";

    public static final String CALC_MAN_SHAPES_EP = CALC_MANAGEMENT_URL + SHAPES_EP;

    public static final String CALC_MAN_SHAPES_ID_EP = CALC_MAN_SHAPES_EP + "/{id}";

    public static final String CALC_MAN_SHAPES_SET_NAME_EP = CALC_MAN_SHAPES_ID_EP + ":set-name";

    //</editor-fold>
};
