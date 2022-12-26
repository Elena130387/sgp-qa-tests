package api.helper;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static api.client.Estimator.getEstimatorStatusById;
import static api.client.Estimator.getJobExecutionIds;
import static api.dto.StatusesList.*;
import static util.Util.timeoutIsReached;

public class CalculationHelper {

    public static void waitForCalculationStarting(int shapeId, int timeoutInSeconds, int durationInSeconds) throws TimeoutException {
        String errorMsg = "Калькуляция не начата за ожидаемое время: " + timeoutInSeconds + " секунд";
        List<Integer> jobExecutionIds = getJobExecutionIds(shapeId, 10, 0);
        for (Integer jobExecutionId : jobExecutionIds) {
            waitForCalcStatus(jobExecutionId, timeoutInSeconds, durationInSeconds, STARTING.getStatusName(), errorMsg);
        }
    }

    public static void waitForCalculationStop(int shapeId, int timeoutInSeconds, int durationInSeconds) throws TimeoutException {
        String errorMsg = "Калькуляция не была остановлена за ожидаемое время: " + timeoutInSeconds + " секунд";
        List<Integer> jobExecutionIds = getJobExecutionIds(shapeId, 10, 0);
        for (Integer jobExecutionId : jobExecutionIds) {
            waitForCalcStatus(jobExecutionId, timeoutInSeconds, durationInSeconds, STOPPED.getStatusName(), errorMsg);
        }
    }

    private static void waitForCalcStatus(int jobExecutionId, int timeoutInSeconds, int durationInSeconds, String statusName, String errorMsg) throws TimeoutException {
        long start = System.currentTimeMillis();
        while (!timeoutIsReached(start, timeoutInSeconds, durationInSeconds)) {
            String status = getJobExecutionStatus(jobExecutionId);
            if (status.equals(statusName)) {
                return;
            } else {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(durationInSeconds));
                } catch (InterruptedException exception) {
                    System.out.println("Ожидание изменения статуса калькуляции прервано");
                }
            }
        }
        throw new TimeoutException(errorMsg);
    }

    public static void waitForCalculationEnding(int jobExecutionId, int timeoutInSeconds, int durationInSeconds) throws TimeoutException {
        long start = System.currentTimeMillis();
        while (!timeoutIsReached(start, timeoutInSeconds, durationInSeconds)) {
            String status = getJobExecutionStatus(jobExecutionId);
            if (status.equals(COMPLETED.getStatusName())) {
                return;
            } else if (status.equals(FAILED.getStatusName()) || status.equals(STOPPED.getStatusName())) {
                throw new RuntimeException("Калькуляция остановлена");
            } else {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(durationInSeconds));
                } catch (InterruptedException exception) {
                    System.out.println("Ожидание окончания калькуляции прервано");
                }
            }
        }
        throw new TimeoutException("Калькуляция не выполнена за ожидаемое время: " + timeoutInSeconds + " seconds");
    }

    public static String getJobExecutionStatus(int jobExecutionId) {
        return getEstimatorStatusById(jobExecutionId);
    }
}
