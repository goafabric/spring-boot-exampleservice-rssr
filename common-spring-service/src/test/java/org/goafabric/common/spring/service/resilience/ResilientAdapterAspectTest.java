package org.goafabric.common.spring.service.resilience;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

/**
 * @author amautsch
 *         Date: 02.03.15
 *         Time: 11:25
 */
@RunWith(MockitoJUnitRunner.class)
public class ResilientAdapterAspectTest {
    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private MethodSignature methodSignature;

    @InjectMocks
    private final ResilientAdapterAspect ccdResilientAdapterAspect =
            new ResilientAdapterAspect() {
                @Override
                public String getName() {
                    return "";
                }
            };


    @Before
    public void setup() {
        when(proceedingJoinPoint.getSignature()).thenReturn(methodSignature);
    }

    //Generic AdapterAspect Tests
    @Test
    public void testExecute() throws Throwable {
        final Object mockResponse = new Object();
        when(proceedingJoinPoint.proceed()).thenReturn(mockResponse);

        final Object response = ccdResilientAdapterAspect.execute(proceedingJoinPoint);
        assertThat(response).isEqualTo(mockResponse);
    }

    @Test(expected = HystrixRuntimeException.class)
    public void testExecuteAdapterSystemException() throws Throwable {
        when(proceedingJoinPoint.proceed()).thenThrow(IllegalStateException.class);
        ccdResilientAdapterAspect.execute(proceedingJoinPoint);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testExecuteNull() throws Throwable {
        when(proceedingJoinPoint.proceed()).thenReturn(null);
        ccdResilientAdapterAspect.execute(proceedingJoinPoint);
        fail();
    }

    @Test(expected = HystrixRuntimeException.class)
    public void testOutOfMemoryError() throws Throwable {
        when(proceedingJoinPoint.proceed()).thenThrow(OutOfMemoryError.class);
        ccdResilientAdapterAspect.execute(proceedingJoinPoint);
        fail();
    }
}