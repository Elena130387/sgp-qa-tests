package api.client;

import io.restassured.response.ValidatableResponse;

import java.util.List;

import static util.Constants.*;

public class Estimator {

    public static ValidatableResponse getEstimatorDataById(int id) {
        return BaseRequests.getRequestWithId(id, ESTIMATOR_JOB_EXECUTION_ID_EP);
    }

    public static int getJobExecutionId(int shapeId) {
        return BaseRequests.getRequestWithParam(1, 0, ESTIMATOR_EXECUTIONS_EP)
                .extract().body().path("content.find { it.jobExecution.shape.shapeId==" + shapeId + "}.jobExecution.id");
    }

    public static List<Integer> getJobExecutionIds(int shapeId, int limit, int offset) {
        return BaseRequests.getRequestWithParam(limit, offset, ESTIMATOR_EXECUTIONS_EP)
                .extract().body().path("content.findAll { it.jobExecution.shape.shapeId==" + shapeId + "}.jobExecution.id");
    }

    public static ValidatableResponse deleteJobExecutionById(List<Integer> jobExecutionIds) {
        return BaseRequests.deleteRequestWithQueryParams(jobExecutionIds, ESTIMATOR_EXECUTIONS_EP);
    }

    public static ValidatableResponse stopJobExecutionById(int jobExecutionId) {
        return BaseRequests.postRequestWithId(jobExecutionId, ESTIMATOR_JOB_EXECUTION_STOP);
    }
}
