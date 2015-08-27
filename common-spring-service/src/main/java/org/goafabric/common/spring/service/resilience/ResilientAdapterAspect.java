package org.goafabric.common.spring.service.resilience;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({
        "PMD.AvoidCatchingThrowable",
        "PMD.SignatureDeclareThrowsException"
})
public abstract class ResilientAdapterAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    //CHECKSTYLE:OFF
    public Object execute(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final long startTime = System.currentTimeMillis();
        final String signature = proceedingJoinPoint.getSignature().toShortString();

        final HystrixCommand hystrixCommand = createHystrixCommand(proceedingJoinPoint);
        final Object response = hystrixCommand.execute();
        checkNullResponse(response);

        final Long duration = System.currentTimeMillis() - startTime;
        log.debug(getLogPrefix() + "Call to " + " Service method " + signature + " took : " + duration);

        return response;
    }
    //CHECKSTYLE:ON

    //can be overwritten if null check is not desired
    private void checkNullResponse(final Object response) {
        if (response == null) {
            throw new IllegalStateException("Service Response is null");
        }
    }

    public abstract String getName();

    protected String getLogPrefix() {
        return "[" + getName() + "] ";
    }

    private HystrixCommand createHystrixCommand(final ProceedingJoinPoint proceedingJoinPoint) {
        final HystrixCommand.Setter setup = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey( getName() ))
                .andCommandKey(HystrixCommandKey.Factory.asKey( getName() ))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey( getName() ));
        return new HystrixCommand(setup) {
            @Override
            protected Object run() throws Exception {
                try {
                    log.info("Call " + getCommandKey().name());
                    return proceedingJoinPoint.proceed();
                }
                catch (final Exception ex) {
                    throw ex;
                }
                catch (final Throwable ex) {
                    throw new Exception(ex);
                }
            }
        };
    }
}
