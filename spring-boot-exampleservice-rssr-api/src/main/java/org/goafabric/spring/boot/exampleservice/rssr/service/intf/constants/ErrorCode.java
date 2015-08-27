package org.goafabric.spring.boot.exampleservice.rssr.service.intf.constants;

/**
 * Created by amautsch on 27.07.2015.
 */
public final class ErrorCode {
    private ErrorCode() {
    }

    public static final String INPUT_VALIDATION_APPLICATION_EXCEPTION = "Validation of input data failed";
    public static final String ADAPTER_SYSTEM_EXCEPTION = "Call to external service failed";

}
