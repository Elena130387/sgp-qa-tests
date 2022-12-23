package backend.interfaces;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static api.client.BaseRequests.getRequestWithParam;

public interface GetObjectsListParamLimitTest {
    String getUrl();

    int getCorrectLimit();

    @Test
    default void getEntity_withIncorrectStringLimit_expect400Error() {
        String limit = "text";
        ValidatableResponse response = RestAssured
                .given()
                .param("page[limit]", limit)
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
                .param("page[limit]", limit)
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
}
