package org.goafabric.common.spring.service.security;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * Intercepter for writing audit logs for all EJB3 invocations.
 * <p/>
 * The following information should be logged:
 * <p/>
 * 1. Callers principle name 2. The EJB or Service Interface that had been
 * called 3. The operation of the Service Interface that had been called
 * 4. The thread name of the worker thread (in the hope, that the IP address will be identifiable)
 */

@Aspect
public class AuditAspect {

    private final String applicationNameAndVersion;
    private final Logger log;

    public AuditAspect(
            final String loggerName, final String applicationNameAndVersion) {
        log = LoggerFactory.getLogger(loggerName);
        this.applicationNameAndVersion = applicationNameAndVersion;
    }

    @Before("execution(public * *(..)) && within(@org.goafabric.common.spring.service.security.annotation.AuditLog *)")
    public void logAudit(final JoinPoint joinPoint) {
        try {
            //performIntegrityChecks(invocationContext);

            final String beanName   = joinPoint.getTarget().getClass().getName();
            final String methodName = joinPoint.getSignature().getName();

            final String callerPrincipleName = getCallerName();

            final String calleeSignature = buildCalleSignature(beanName, methodName);

            final String callerUser = buildCallerUser(callerPrincipleName);

            final String logMessage = createLogMessage(calleeSignature, callerUser);

            log.info(logMessage);
        } catch (Exception e) {
            log.warn("Internal error during audit log : " + e.getMessage());
        }
    }

    /*
    @Around("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))"
            + "&& args(authentication)")
    public Object logBadCredentials(ProceedingJoinPoint pjp, Authentication authentication) throws Throwable {

        try {
            //log.debug("Entering authentication ...");
            final Long time = System.currentTimeMillis();
            final Object retVal = pjp.proceed();
            final Long duration = System.currentTimeMillis() - time;
            log.debug("Authentication took: " + duration);
            //System.err.println("Authentication took: " + duration);
            return retVal;
        } catch (BadCredentialsException e) {
            if (authentication != null) {
                log.error("Authentication failed for user: " + authentication.getPrincipal());
            }
            throw e;
        }
    }
    */


    private String getCallerName() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : "<n/a>";
    }

    String buildCalleSignature(String beanName, String methodName) {
        final StringBuilder calleeSignature = new StringBuilder();
        calleeSignature.append("Application: " + applicationNameAndVersion);
        calleeSignature.append("; " + beanName);
        calleeSignature.append(".");
        calleeSignature.append(methodName);
        calleeSignature.append("(...)");
        return calleeSignature.toString();
    }


    String buildCallerUser(String callerPrincipleName) {
        final StringBuilder callerText = new StringBuilder();
        callerText.append("; User: ");
        callerText.append(callerPrincipleName);
        return callerText.toString();
    }

    String createLogMessage(String calleeSignature, String callerUser) {
        final StringBuilder logMessage = new StringBuilder();
        logMessage.append(calleeSignature);
        logMessage.append(callerUser);
        return logMessage.toString();
    }

}
