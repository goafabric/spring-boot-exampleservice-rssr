package org.goafabric.common.spring.service.sustainability.exception;

/**
* Base class for technical exceptions
* Exceptions of this type may be recoverable
* <p/>
* All Exceptions should be RuntimeExceptions
* (@see clean code)
*
* @author amautsch
*/

public abstract class SystemException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract String getErrorCode();
}
