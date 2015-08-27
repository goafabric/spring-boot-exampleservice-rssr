package org.goafabric.common.spring.service.sustainability;

import org.goafabric.common.service.result.CommonErrorCode;
import org.goafabric.common.service.result.ServiceResult;
import org.goafabric.common.spring.service.sustainability.exception.ApplicationException;
import org.goafabric.common.spring.service.sustainability.exception.SystemException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;

/**
 * @author amautsch
 *         Date: 19.12.14
 *         Time: 17:47
 */

@Aspect
public class ResultHandlerAspect {
    private final Logger log;

    public ResultHandlerAspect(final String loggerName) {
        this.log = LoggerFactory.getLogger(loggerName);
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("unchecked")
    @Around("execution(public * *(..)) && within(@org.goafabric.common.spring.service.sustainability.annotation.ResultHandler *)")
    public <T extends ServiceResult> T handleExceptionForResult(final ProceedingJoinPoint proceedingJoinPoint) throws
            Throwable {
        final MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        final Class<? extends ServiceResult> resultType = signature.getReturnType();
        T result;

        final long startTime = System.currentTimeMillis();
        try {
            log.info("Entering method : " + signature.toShortString());
            result = (T) proceedingJoinPoint.proceed();
            result.setExecuted(true);
        } catch (Exception e) {
            e = mapException(e);
            result = (T) mapExceptionToResult(proceedingJoinPoint, signature, e, resultType);
            result.setExecuted(false);
        } finally {
            final long duration = System.currentTimeMillis() - startTime;
            log.info("Exiting method : " + signature.toShortString() + ", call took : " + duration + " ms");
        }
        return result;
    }
    //CHECKSTYLE:ON

    protected Exception mapException(Exception e) {
        return e;
    }

    private <T extends ServiceResult> T mapExceptionToResult(final ProceedingJoinPoint proceedingJoinPoint,
                                                             final MethodSignature signature, final Throwable e,
                                                             final Class<? extends T> resultType)
            throws InstantiationException, IllegalAccessException {

        //create new Empty result
        final T result = resultType.newInstance();
        final String signatureInfo = getSignatureInfo(proceedingJoinPoint, signature);

        if (e instanceof ApplicationException) {
            result.setErrorCode( ((ApplicationException) e).getErrorCode() );
            result.setErrorDetails(signatureInfo + e.getMessage());
            log.warn(result.getErrorDetails());
        }
        else if (e instanceof SystemException) {
            result.setErrorCode( ((SystemException) e).getErrorCode());
            result.setErrorDetails(signatureInfo + e.getMessage());
            log.error(result.getErrorDetails(), e);
        }
        else {
            handleGeneralApplicationException(e, result, signatureInfo);
        }

        return result;
    }

    private <T extends ServiceResult> void handleGeneralApplicationException(
            Throwable e, T result, String signatureInfo) {

        Throwable     resultThrowable = e;
        if (e instanceof UndeclaredThrowableException) {
            final UndeclaredThrowableException ue = (UndeclaredThrowableException) e;
            if (ue.getUndeclaredThrowable() != null) {
                resultThrowable = ue.getUndeclaredThrowable();
            }
        }
        result.setErrorCode(CommonErrorCode.GENERAL_APPLICATION_EXCEPTION);
        result.setErrorDetails(signatureInfo + resultThrowable.toString()); //we have to use toString, because getMessage could be null
        log.error(result.getErrorDetails(), e);
    }

    private String getSignatureInfo(ProceedingJoinPoint proceedingJoinPoint, MethodSignature signature) {
        final String method = signature.toShortString();
        final String args = Arrays.toString(proceedingJoinPoint.getArgs());
        return "Error while proceessing: " + method + " with arguments: " + args + ";\nErrordetails: ";
    }
}
