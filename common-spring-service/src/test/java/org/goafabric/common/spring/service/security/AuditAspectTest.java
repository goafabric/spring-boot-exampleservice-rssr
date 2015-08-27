package org.goafabric.common.spring.service.security;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author amautsch
 *         Date: 05.10.14
 *         Time: 12:52
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class AuditAspectTest {

    final AuditAspect exampleSecurityAuditInterceptor
            = new AuditAspect(this.getClass().getName(), "ExampleService 1.0");

    @Mock
    ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    Authentication authentication;

    @Test
    public void testBuildAllPositive() {
        final String calleeSignature = exampleSecurityAuditInterceptor.buildCalleSignature
                ("org.goafabric.exampleservice", "MagicService.doMagic");


        final String callerText = exampleSecurityAuditInterceptor.buildCallerUser("pjunittests");

        final String logMessage = exampleSecurityAuditInterceptor.createLogMessage(calleeSignature, callerText);
        log.info(logMessage);

        assertThat(logMessage).isNotNull();
        final String expectedMessage = "Application: ExampleService 1.0; org.goafabric.exampleservice.MagicService" +
                ".doMagic(...); User: pjunittests";
        assertThat(logMessage).isEqualTo(expectedMessage);
    }

    /*
    @Test
    public void testLogBadcredentials() throws Throwable {
        final Object object = new Object();
        when(proceedingJoinPoint.proceed()).thenReturn(object);

        Object objectNew = exampleSecurityAuditInterceptor.logBadCredentials(
                proceedingJoinPoint, authentication);
        assertThat(objectNew).isEqualTo(object);
    }

    @Test(expected = BadCredentialsException.class)
    public void testLogBadcredentialsException() throws Throwable {
        when(proceedingJoinPoint.proceed()).thenThrow(BadCredentialsException.class);

        try {
            exampleSecurityAuditInterceptor.logBadCredentials(proceedingJoinPoint, authentication);
        } catch (BadCredentialsException e) {
            verify(authentication, times(1)).getPrincipal();
            throw e;
        }
    }
    */
}

