package util;

import com.github.romankh3.image.comparison.ImageComparisonUtil;
import io.github.cdimascio.dotenv.Dotenv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static util.Constants.IMAGE_COMPARISON_DIR;

public class Util {
    public static String getTimestampNowAsString() {
        var now = LocalDateTime.now();
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return now.format(formatter);
    }

    public static String getVariable(String variableName) {
        var systemVariable = System.getenv(variableName);
        if (systemVariable != null) {
            return systemVariable;
        } else {
            return Dotenv.configure().load().get(variableName);
        }
    }

    public static void imageComparison(String imgSource, String imgNew, String testName) {
        var localPath = IMAGE_COMPARISON_DIR;
        BufferedImage source = ImageComparisonUtil.readImageFromResources(imgSource);
        BufferedImage newImg = ImageComparisonUtil.readImageFromResources(imgNew);

        File diffFile = new File(IMAGE_COMPARISON_DIR + "image_comparison_" + testName + Util.getTimestampNowAsString() + ".png");

    }
}
