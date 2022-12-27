package backend.interfaces;

import api.client.CalcManagement;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static api.client.BaseRequests.getRequestWithParam;
import static api.helper.JsonHelper.getStringFromJson;
import static org.junit.jupiter.api.Assertions.assertEquals;

public interface GetObjectsListParamLimitTest {

    String PAGE_SIZE_ERROR_MESSAGE = "Page size must not be less than one!";

    String getUrl();

    int getCorrectLimit();

    @Test
    default void getEntity_withIncorrectStringLimit_expect400Error() {
        String limit = "text";
        ValidatableResponse response = RestAssured
                .given()
                .param("filter[limit]", limit)
                .when()
                .get(getUrl())
                .then();
        response.statusCode(400);
    }

    @Test
    default void getEntity_withIncorrectDoubleLimit_expect400Error() {
        double limit = 2.7;
        ValidatableResponse response = RestAssured
                .given()
                .param("filter[limit]", limit)
                .when()
                .get(getUrl())
                .then();
        response.statusCode(400);
    }

    @Test
    default void getEntity_withCorrectLimit_expect200ok() {
        ValidatableResponse response = getRequestWithParam(getCorrectLimit(), getUrl());
        response.statusCode(200);
    }

    @Test
    default void getEntity_withNegativeLimit_expect500Error() {
        int limit = -4;
        ValidatableResponse response = getRequestWithParam(limit, getUrl());
        response.statusCode(500);
    }

     @Test
     default void getEntity_withNegativeLimit_expectErrorMessage() {
        int limit = -4;
        ValidatableResponse response = getRequestWithParam(limit, getUrl());
        String message = getStringFromJson(response, "message");
        assertEquals(PAGE_SIZE_ERROR_MESSAGE,
                message, "Сообщение об ошибке не соответвтует ожидаемому");
    }

    @Test
    default void getEntity_withZeroLimit_expect500Error() {
        int limit = 0;
        ValidatableResponse response = CalcManagement.getLimitNumberOfShapes(limit);
        String message = getStringFromJson(response, "message");
        assertEquals(PAGE_SIZE_ERROR_MESSAGE,
                message, "Сообщение об ошибке не соответвтует ожидаемому");
        response.statusCode(500);
    }
}
