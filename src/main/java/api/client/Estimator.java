package api.client;

import io.restassured.response.ValidatableResponse;

import java.util.List;

import static util.Constants.*;

public class Estimator {
    public static String getJobExecutionsUrl = ESTIMATOR_URL + EXECUTIONS_EP;

    public static ValidatableResponse getEstimatorDataById(int id) {
        return BaseRequests.getRequest(id, ESTIMATOR_JOB_EXECUTION_ID_EP);
    }

    public static int getJobExecutionId(int shapeId) {
        return BaseRequests.getRequestWithParam(1, 0, getJobExecutionsUrl)
                .extract().body().path("content.find { it.jobExecution.shape.shapeId==" + shapeId + "}.jobExecution.id");
    }

    public static List<Integer> getJobExecutionIds(int shapeId, int limit, int offset) {
        return BaseRequests.getRequestWithParam(limit, offset, getJobExecutionsUrl)
                .extract().body().path("content.findAll { it.jobExecution.shape.shapeId==" + shapeId + "}.jobExecution.id");
    }

    public static ValidatableResponse deleteJobExecutionById(List<Integer> jobExecutionIds) {
        return BaseRequests.deleteRequestWithQueryParams(jobExecutionIds, getJobExecutionsUrl);
    }
}
