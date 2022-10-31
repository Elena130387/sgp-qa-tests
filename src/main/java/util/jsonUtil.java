package util;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class jsonUtil {
    // serialization
    public static <T> Object getDataFromJsonFile(String path, Class<T> classOfT) throws IOException {
        Gson gson = new Gson();
        String data = new String(Files.readAllBytes(Paths.get(path)));
        return gson.fromJson(data, classOfT);
    }

    public static <T> Object getDataFromJson(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }
}
