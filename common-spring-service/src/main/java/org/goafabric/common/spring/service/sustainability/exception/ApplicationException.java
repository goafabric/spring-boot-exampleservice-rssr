package org.goafabric.common.spring.service.sustainability.exception;

/**
* Base class for business, non-technical exceptions
* Exceptions of this type are non-recoverable
* <p/>
* All Exceptions should be RuntimeExceptions
* (@see clean code)
*
* @author amautsch
*/

public abstract class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApplicationException(String message) {
        super(message);
    }

    public abstract String getErrorCode();
}
