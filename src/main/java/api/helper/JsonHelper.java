package api.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.response.ValidatableResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.hasKey;

public class JsonHelper {
    // serialization
    public static <T> Object getDataFromJsonFile(String path, Class<T> classOfT) throws IOException {
        Gson gson = new Gson();
        String data = new String(Files.readAllBytes(Paths.get(path)));
        return gson.fromJson(data, classOfT);
    }

    public static int getIntFromJson(ValidatableResponse response, String name) {
        response.assertThat().body("$", hasKey(name));
        return response.extract().body().jsonPath().getInt(name);
    }

    public static String getStringFromJson(ValidatableResponse response, String name) {
        response.assertThat().body("$", hasKey(name));
        return response.extract().body().jsonPath().getString(name);
    }

    public static <T> List getObjectsListFromJson(ValidatableResponse response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = response.extract().body().asPrettyString();
        List<T> result = mapper.readValue(json, new TypeReference<>() {
        });
        return result;
    }
}
