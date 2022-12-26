package api.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.ValidatableResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.hasKey;

public class JsonHelper {
    // serialization
    public static <T> Object getDataFromJsonFile(String path, Class<T> classOfT) {
        Gson gson = new Gson();
        try {
            String data = new String(Files.readAllBytes(Paths.get(path)));
            return gson.fromJson(data, classOfT);
        } catch (JsonPathException exception) {
            System.out.println("Не удалось создать область из файла  " + path);
        } catch (IOException exception) {
            System.out.println("Не удалось считать файла  " + path);
        }
        return null;
    }

    public static int getIntFromJson(ValidatableResponse response, String name) {
        response.assertThat().body("$", hasKey(name));
        return response.extract().body().jsonPath().getInt(name);
    }

    public static String getStringFromJson(ValidatableResponse response, String name) {
        response.assertThat().body("$", hasKey(name));
        return response.extract().body().jsonPath().getString(name);
    }

    public static <T> T getObjectsListFromJson(ValidatableResponse response, Class<T> classOfT) {
        String json = response.extract().body().asPrettyString();
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }

    public static <T> List getObjectsListFromJson(ValidatableResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        String json = response.extract().body().asPrettyString();
        try {
            List<T> result = mapper.readValue(json, new TypeReference<>() {
            });
            return result;
        } catch (JsonProcessingException exception) {
            System.out.println("Структура объектов в json не совпадает с ожидаемой структурой" + json);
        }
        return null;
    }
}
