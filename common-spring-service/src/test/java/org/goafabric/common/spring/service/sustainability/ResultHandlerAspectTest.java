package org.goafabric.common.spring.service.sustainability;

import org.goafabric.common.service.result.CommonErrorCode;
import org.goafabric.common.service.result.ServiceResult;
import org.goafabric.common.spring.service.sustainability.exception.ApplicationException;
import org.goafabric.common.spring.service.sustainability.exception.SystemException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.UndeclaredThrowableException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author amautsch
 *         Date: 26.02.15
 *         Time: 11:38
 */

@RunWith(MockitoJUnitRunner.class)
public class ResultHandlerAspectTest {

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private MethodSignature methodSignature;

    @InjectMocks
    private final ResultHandlerAspect resultHandlerAspect =
            new ResultHandlerAspect("");

    @Test
    public void handleExceptionForResultPositiveTest() throws Throwable {
        MockServiceResult mockServiceResult = new MockServiceResult();
        mockServiceResult.setExecuted(true);

        when(proceedingJoinPoint.proceed()).thenReturn(mockServiceResult);
        when(proceedingJoinPoint.getSignature()).thenReturn(methodSignature);

        ServiceResult result = resultHandlerAspect.handleExceptionForResult(proceedingJoinPoint);
        assertThat(result.isExecuted()).isTrue();
    }

    @Test
    public void testHandleApplicationException() throws Throwable {
        assertThat(checkException(new MockApplicationException("")))
                .isEqualTo(MockApplicationException.ERROR_CODE);
    }

    @Test
    public void testHandleSystemException() throws Throwable {
        assertThat(checkException(new MockSystemException("", null)))
                .isEqualTo(MockSystemException.ERROR_CODE);
    }

    @Test
    public void testHandleGeneralException() throws Throwable {
        assertThat(checkException(new RuntimeException()))
                .isEqualTo(CommonErrorCode.GENERAL_APPLICATION_EXCEPTION);
    }

    @Test
    public void testHandleUndeclaredThrowable() throws Throwable {
        assertThat(checkException(new UndeclaredThrowableException(new RuntimeException())))
                .isEqualTo(CommonErrorCode.GENERAL_APPLICATION_EXCEPTION);
    }

    @Test
    public void testHandleUndeclaredThrowableNull() throws Throwable {
        assertThat(checkException(new UndeclaredThrowableException(null)))
                .isEqualTo(CommonErrorCode.GENERAL_APPLICATION_EXCEPTION);
    }

    private String checkException(Exception exception) throws Throwable {
        when(proceedingJoinPoint.proceed()).thenThrow(exception);
        when(proceedingJoinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getReturnType()).thenReturn(MockServiceResult.class);

        final ServiceResult result = resultHandlerAspect.handleExceptionForResult(proceedingJoinPoint);
        assertThat(result.isExecuted()).isFalse();
        assertThat(result.getErrorCode()).isNotNull();
        return result.getErrorCode();
    }

    /********** innner classes **************/


    private static class MockServiceResult extends ServiceResult {
        public MockServiceResult() {
        }
    }

    private static class MockApplicationException extends ApplicationException {
        public static final String ERROR_CODE = "1";

        public MockApplicationException(String message) {
            super(message);
        }

        @Override
        public String getErrorCode() {
            return ERROR_CODE;
        }
    }

    private static class MockSystemException extends SystemException {
        public static final String ERROR_CODE = "2";

        public MockSystemException(String message, Throwable cause) {
            super(message, cause);
        }

        @Override
        public String getErrorCode() {
            return ERROR_CODE;
        }
    }

}
