package org.goafabric.common.spring.service.sustainability.exception;

/**
* Base class for technical exceptions
* Exceptions of this type may be recoverable
* <p/>
* All Exceptions should be RuntimeExceptions
* (@see clean code)
* Naming conventions are derived from the 1&1 SOA Board
* (@see http://wiki.intranet.1and1.com/bin/view/DSL/EjbExceptions)
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
