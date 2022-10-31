package api.client;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static util.Constants.SGP_URL_DEV;

public class baseRequests {

    static {
        RestAssured.baseURI = SGP_URL_DEV;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    // POST
    public static <T> ValidatableResponse postRequestWithBody(Class<T> input, String url) {
        return given()
                .contentType(ContentType.JSON)
                .body(input)
                .when()
                .post(url)
                .then();
    }

    public static ValidatableResponse getRequestWithMultiPart(File file, String url) {
        return given()
                .multiPart("image", file, "application/octet-stream")
                .when()
                .post(url)
                .then();
    }

    // GET
    public static ValidatableResponse getRequest(int id, String url) {
        return given()
                .when()
                .get(url, id)
                .then();
    }

    public static ValidatableResponse getRequestWithParam(int limit, int offset, String url) {
        return given()
                .param("page[limit]", limit)
                .param("page[offset]", offset)
                .when()
                .get(url)
                .then();
    }

    public static ValidatableResponse getRequestWithQueryParam(int id, List<String> types, String version, String url) {
        return given()
                .queryParam("type", types)
                .queryParam("version", version)
                .when()
                .get(url, id)
                .then();
    }

    public static ValidatableResponse getRequestWithQueryParam(List<Integer> ids, String url) {
        return given()
                .queryParam("ids", ids)
                .when()
                .get(url)
                .then();
    }

    public static ValidatableResponse getRequestWithQueryParams(int id, Map<String, String> params, String url) {
        return given()
                .queryParams(params)
                .when()
                .get(url, id)
                .then();
    }

    public static ValidatableResponse getRequestWithQueryParams(Map<String, String> params, String url) {
        return given()
                .queryParams(params)
                .when()
                .get(url)
                .then();
    }
}
