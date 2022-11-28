package backend.interfaces;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static api.client.BaseRequests.deleteRequestWithId;

public interface DeleteByIdControllerTest {
    String getUrl();

    int getCorrectId();

    int getDeletedId();

    @Test
    default void deleteEntity_withIncorrectId_expect400Error() {
        String id = "text";
        ValidatableResponse response = RestAssured
                .given()
                .when()
                .delete(getUrl(), id)
                .then();
        response.statusCode(400);
    }

    @Test
    default void deleteEntity_withNonexistentId_expect204noContent() {
        int id = 100000000;
        ValidatableResponse response = deleteRequestWithId(id, getUrl());
        response.statusCode(200);
    }

    @Test
    default void deleteEntity_withDeletedId_expect204noContent() {
        ValidatableResponse response = deleteRequestWithId(getCorrectId(), getUrl());
        response.statusCode(200);
    }

    @Test
    default void deleteEntity_withCorrectId_expect200ok() {
        ValidatableResponse response = deleteRequestWithId(getDeletedId(), getUrl());
        response.statusCode(200);
    }
}
