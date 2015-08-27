package org.goafabric.spring.boot.exampleservice.rssr.logic.exception;


import org.goafabric.common.spring.service.sustainability.exception.ApplicationException;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.constants.ErrorCode;

/**
 * @author amautsch
 *         Date: 19.02.15
 *         Time: 13:27
 */
public class InputValidationApplicationException extends ApplicationException {
    public InputValidationApplicationException(final String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return ErrorCode.INPUT_VALIDATION_APPLICATION_EXCEPTION;
    }
}
