package org.goafabric.spring.boot.exampleservice.rssr.adapter.exception;

import org.goafabric.common.spring.service.sustainability.exception.SystemException;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.constants.ErrorCode;

/**
 * @author amautsch
 *         Date: 02.03.15
 *         Time: 15:09
 */
public class AdapterSystemException extends SystemException {
    public AdapterSystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getErrorCode() {
        return ErrorCode.ADAPTER_SYSTEM_EXCEPTION;
    }
}
