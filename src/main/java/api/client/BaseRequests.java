package api.client;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static util.Constants.SGP_URL_DEV;

public class BaseRequests {

    static {
        RestAssured.baseURI = SGP_URL_DEV;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    //<editor-fold desc="POST Requests">
    public static ValidatableResponse postRequestWithId(int id, String url) {
        return given()
                .contentType(ContentType.JSON)
                .post(url, id)
                .then();
    }

    public static <T> ValidatableResponse postRequestWithBody(T input, String url) {
        return given()
                .contentType(ContentType.JSON)
                .body(input)
                .when()
                .post(url)
                .then();
    }

    public static ValidatableResponse postRequestWithMultiPart(File file, String url) {
        return given()
                .multiPart("image", file, "application/octet-stream")
                .when()
                .post(url)
                .then();
    }
    //</editor-fold>

    //<editor-fold desc="GET Requests">
    public static ValidatableResponse getRequest(String url) {
        return given()
                .when()
                .get(url)
                .then();
    }

    public static ValidatableResponse getRequestWithId(int id, String url) {
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

    public static ValidatableResponse getRequestWithParam(String sort, String url) {
        return given()
                .param("sort", sort)
                .when()
                .get(url)
                .then();
    }

    public static ValidatableResponse getRequestWithParam(int limit, String url) {
        return given()
                .param("filter[limit]", limit)
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

    public static ValidatableResponse getRequestWithIdAndQueryParams(int id, Map<String, String> params, String url) {
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
    //</editor-fold>

    //<editor-fold desc="PUT Request">
    public static <T> ValidatableResponse putRequestWithId(T input, int id, String url) {
        return given()
                .contentType(ContentType.JSON)
                .body(input)
                .put(url, id)
                .then();
    }
    //</editor-fold>

    //<editor-fold desc="DELETE Request">
    public static ValidatableResponse deleteRequestWithId(int id, String url) {
        return given()
                .contentType(ContentType.JSON)
                .delete(url, id)
                .then();
    }

    public static ValidatableResponse deleteRequestWithQueryParams(List<Integer> ids, String url) {
        return given()
                .queryParams("ids", ids)
                .delete(url)
                .then();
    }
    //</editor-fold>

}
