package util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {
    public static final String SGP_URL_DEV = "https://sgp-dev.syncretis.com/";

    public static final String NEW_CALCULATION_URL = SGP_URL_DEV + "?newCalculation=true&showAside=true";

    public static final Path STORAGE_PATH = Paths.get("storage-state.json");

    public static final String FAILURE_SCREENSHOTS_DIR = "failureScreenshots\\";

    public static final String EXPECTED_SCREENSHOTS_DIR = "expectedScreenshots\\";

};
