package util;

import com.google.gson.Gson;
import io.restassured.response.ValidatableResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.hasKey;

public class JsonUtil {
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

    public static int getIntFromJson(ValidatableResponse response, String name) {
        response.assertThat().body("$", hasKey(name));
        return response.extract().body().jsonPath().getInt(name);
    }

    public static String getStringFromJson(ValidatableResponse response, String name) {
        response.assertThat().body("$", hasKey(name));
        return response.extract().body().jsonPath().getString(name);
    }
}
