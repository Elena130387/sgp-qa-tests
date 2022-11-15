package api.helper;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static api.client.Estimator.getEstimatorDataById;
import static api.client.Estimator.getJobExecutionIds;
import static api.dto.StatusesList.*;
import static util.Util.timeoutIsReached;

public class CalculationHelper {

    public static void waitForCalculationStarting(int shapeId, int timeoutInSeconds, int durationInSeconds) throws InterruptedException, TimeoutException {
        String errorMsg = "Калькуляция не начата за ожидаемое время: " + timeoutInSeconds + " секунд";
        List<Integer> jobExecutionIds = getJobExecutionIds(shapeId, 10, 0);
        for (Integer jobExecutionId : jobExecutionIds) {
            waitForCalcStatus(jobExecutionId, timeoutInSeconds, durationInSeconds, STARTING.getStatusName(), errorMsg);
        }
    }

    public static void waitForCalcStatus(int jobExecutionId, int timeoutInSeconds, int durationInSeconds, String statusName, String errorMsg) throws InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        while (!timeoutIsReached(start, timeoutInSeconds, durationInSeconds)) {
            String status = getJobExecutionStatus(jobExecutionId);
            if (status.equals(statusName)) {
                return;
            } else {
                Thread.sleep(TimeUnit.SECONDS.toMillis(durationInSeconds));
            }
        }
        throw new TimeoutException(errorMsg);
    }

    public static void waitForCalculationEnding(int jobExecutionId, int timeoutInSeconds, int durationInSeconds) throws InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        while (!timeoutIsReached(start, timeoutInSeconds, durationInSeconds)) {
            String status = getJobExecutionStatus(jobExecutionId);
            if (status.equals(COMPLETED.getStatusName())) {
                return;
            } else if (status.equals(FAILED.getStatusName()) || status.equals(STOPPED.getStatusName())) {
                throw new RuntimeException("Калькуляция остановлена");
            } else {
                Thread.sleep(TimeUnit.SECONDS.toMillis(durationInSeconds));
            }
        }
        throw new TimeoutException("Калькуляция не выполнена за ожидаемое время: " + timeoutInSeconds + " seconds");
    }

    public static String getJobExecutionStatus(int jobExecutionId) {
        return getEstimatorDataById(jobExecutionId).extract().body().path("jobExecution.status");
    }
}
