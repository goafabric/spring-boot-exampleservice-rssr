package org.goafabric.common.service.result;

import java.io.Serializable;

/**
 * @author amautsch
 *         Date: 16.02.15
 *         Time: 18:29
 */
public abstract class ServiceResult implements Serializable {
    private boolean executed;

    private String  errorCode;

    private String  errorDetails;

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
}
