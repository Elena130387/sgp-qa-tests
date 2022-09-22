package util;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import io.github.cdimascio.dotenv.Dotenv;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.fail;
import static util.Constants.FAILURE_SCREENSHOTS_DIR;

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

    public static void imageComparison(Path imgNew, Path imgExpect, String testName) throws IOException {
        BufferedImage source = ImageComparisonUtil.readImageFromResources(String.valueOf(imgExpect));
        BufferedImage newImg = ImageComparisonUtil.readImageFromResources(String.valueOf(imgNew));

        File diffFile = new File(FAILURE_SCREENSHOTS_DIR + "image_comparison_" + testName + Util.getTimestampNowAsString() + ".png");
        ImageComparison comparison = new ImageComparison(source,newImg, diffFile);
        ImageComparisonResult result = comparison.compareImages();
        Files.delete(imgNew);
        float diffPercentage = result.getDifferencePercent();
        if (diffPercentage > 0.01) {
            BufferedImage resultImage = result.getResult();
            ImageComparisonUtil.saveImage(diffFile, resultImage);

            fail("Comparison failed, difference is " + diffPercentage);
        }
    }
}
