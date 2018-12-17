package org.goafabric.common.spring.service.resilience;

import com.google.common.base.Objects;
import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesFactory;
import org.springframework.jmx.export.annotation.ManagedAttribute;

/**
 * Base class for your own Hystrix MBeans. Allows you to update a given subset of hystrix properties (not all
 * properties are supported).
 * <p/>
 * Subclasses have to implement <code>getName()</code>, which will be used as the command-, group- and
 * threapool-key. Consequence is, every command has it's own threadpool and you can't group multiple commands.
 */
public abstract class HystrixManager {

	//@formatter:off
	private static final HystrixThreadPoolKey DEFAULT_THREADPOOL_KEY = HystrixThreadPoolKey.Factory.asKey("default");

	static final HystrixCommandKey DEFAULT_COMMAND_KEY = HystrixCommandKey.Factory.asKey("default");

	private static final String FORCE_OPEN = "hystrix.command.%s.circuitBreaker.forceOpen";
    private static final String CIRCUIT_BREAKER_ENABLED = "hystrix.command.%s.circuitBreaker.enabled";
    private static final String ROLLING_STATS_WINDOW_MILLIS = "hystrix.command.%s.metrics.rollingStats.timeInMilliseconds";
    private static final String THREAD_TIMEOUT = "hystrix.command.%s.execution.isolation.thread.timeoutInMilliseconds";
    private static final String CIRCUIT_BREAKER_REQ_VOL_THRESHOLD = "hystrix.command.%s.circuitBreaker.requestVolumeThreshold";
    private static final String CIRCUIT_BREAKER_SLEEP_WINDOW_MILLIS = "hystrix.command.%s.circuitBreaker.sleepWindowInMilliseconds";
    private static final String CIRCUIT_BREAKER_ERROR_PERCENTAGE = "hystrix.command.%s.circuitBreaker.errorThresholdPercentage";

    private static final String THREAD_POOL_CORE_SIZE = "hystrix.threadpool.%s.coreSize";
    private static final String THREAD_POOL_MAX_QUEUE_SIZE = "hystrix.threadpool.%s.maxQueueSize";
    private static final String THREAD_POOL_QUEUE_SIZE_REJECTION_THRESHOLD = "hystrix.threadpool.%s.queueSizeRejectionThreshold";
	//@formatter:on

    @ManagedAttribute
    public boolean getCircuitBreakerEnabled() {
        return getBooleanPropertyOrDefault(createConfigKey(CIRCUIT_BREAKER_ENABLED), getDefaultProperties()
                .circuitBreakerEnabled().get());

    }

    @ManagedAttribute
    public void setCircuitBreakerEnabled(boolean bool) {
        setProperty(createConfigKey(CIRCUIT_BREAKER_ENABLED), bool, getDefaultProperties()
                .circuitBreakerEnabled().get());
    }

    @ManagedAttribute
	public boolean getCircuitBreakerForceOpen() {
		return getBooleanPropertyOrDefault(createConfigKey(FORCE_OPEN), getDefaultProperties()
		        .circuitBreakerForceOpen().get());
	}

    @ManagedAttribute
	public void setCircuitBreakerForceOpen(boolean bool) {
		setProperty(createConfigKey(FORCE_OPEN), bool, getDefaultProperties()
		        .circuitBreakerForceOpen().get());
	}

    @ManagedAttribute
    public Integer getRollingStatsTimeInMilliseconds() {
        return getIntegerPropertyOrDefault(createConfigKey(ROLLING_STATS_WINDOW_MILLIS), getDefaultProperties()
                .metricsRollingStatisticalWindowInMilliseconds().get());
    }

    @ManagedAttribute
    public void setRollingStatsTimeInMilliseconds(final Integer timeInMillis) {
        setProperty(createConfigKey(ROLLING_STATS_WINDOW_MILLIS), timeInMillis, getDefaultProperties()
                .metricsRollingStatisticalWindowInMilliseconds().get());
    }

    @ManagedAttribute
	public Integer getCircuitBreakerRequestVolumeThreshold() {
		return getIntegerPropertyOrDefault(createConfigKey(CIRCUIT_BREAKER_REQ_VOL_THRESHOLD), getDefaultProperties()
		        .circuitBreakerRequestVolumeThreshold().get());
	}

    @ManagedAttribute
	public void setCircuitBreakerRequestVolumeThreshold(final Integer threshold) {
		setProperty(createConfigKey(CIRCUIT_BREAKER_REQ_VOL_THRESHOLD), threshold, getDefaultProperties()
		        .circuitBreakerRequestVolumeThreshold().get());
	}

    @ManagedAttribute
	public Integer getCircuitBreakerSleepWindowMillis() {
		return getIntegerPropertyOrDefault(createConfigKey(CIRCUIT_BREAKER_SLEEP_WINDOW_MILLIS), getDefaultProperties()
		        .circuitBreakerSleepWindowInMilliseconds().get());
	}

    @ManagedAttribute
	public void setCircuitBreakerSleepWindowMillis(final Integer sleepWindowMillis) {
		setProperty(createConfigKey(CIRCUIT_BREAKER_SLEEP_WINDOW_MILLIS), sleepWindowMillis, getDefaultProperties()
		        .circuitBreakerSleepWindowInMilliseconds().get());
	}

    @ManagedAttribute
	public Integer getCircuitBreakerErrorThresholdPercentage() {
		return getIntegerPropertyOrDefault(createConfigKey(CIRCUIT_BREAKER_ERROR_PERCENTAGE), getDefaultProperties()
		        .circuitBreakerErrorThresholdPercentage().get());
	}

    @ManagedAttribute
	public void setCircuitBreakerErrorThresholdPercentage(final Integer threshold) {
		setProperty(createConfigKey(CIRCUIT_BREAKER_ERROR_PERCENTAGE), threshold, getDefaultProperties()
		        .circuitBreakerErrorThresholdPercentage().get());
	}

    @ManagedAttribute
	public Integer getThreadTimeoutInMillis() {
		return getIntegerPropertyOrDefault(createConfigKey(THREAD_TIMEOUT), getDefaultProperties()
		        .executionTimeoutInMilliseconds().get());
	}

    @ManagedAttribute
	public void setThreadTimeoutInMillis(final Integer timeout) {
		setProperty(createConfigKey(THREAD_TIMEOUT), timeout, getDefaultProperties()
		        .executionTimeoutInMilliseconds().get());
	}

    @ManagedAttribute
	public Integer getThreadPoolCoreSize() {
		return getIntegerPropertyOrDefault(createConfigKey(THREAD_POOL_CORE_SIZE), getDefaultThreadPoolProperties().coreSize()
		        .get());
	}

    @ManagedAttribute
	public void setThreadPoolCoreSize(final Integer coreSize) {
		setProperty(createConfigKey(THREAD_POOL_CORE_SIZE), coreSize, getDefaultThreadPoolProperties().coreSize()
		        .get());
	}

    @ManagedAttribute
	public Integer getThreadPoolQueueSizeRejectionThreshold() {
		return getIntegerPropertyOrDefault(createConfigKey(THREAD_POOL_QUEUE_SIZE_REJECTION_THRESHOLD),
		        getDefaultThreadPoolProperties().queueSizeRejectionThreshold().get());
	}

    @ManagedAttribute
	public void setThreadPoolQueueSizeRejectionThreshold(final Integer threshold) {
		setProperty(createConfigKey(THREAD_POOL_QUEUE_SIZE_REJECTION_THRESHOLD), threshold,
		        getDefaultThreadPoolProperties().queueSizeRejectionThreshold().get());
	}

    @ManagedAttribute
	public Integer getThreadPoolMaxQueueSize() {
		return getIntegerPropertyOrDefault(createConfigKey(THREAD_POOL_MAX_QUEUE_SIZE), getDefaultThreadPoolProperties()
		        .maxQueueSize().get());
	}

    @ManagedAttribute
	public void setThreadPoolMaxQueueSize(final Integer queueSize) {
		setProperty(createConfigKey(THREAD_POOL_MAX_QUEUE_SIZE), queueSize, getDefaultThreadPoolProperties()
		        .maxQueueSize().get());
	}

	protected  boolean getBooleanPropertyOrDefault(final String propertyKey, final Boolean def) {
		return ConfigurationManager.getConfigInstance().getBoolean(propertyKey, def);
	}

	protected  Integer getIntegerPropertyOrDefault(final String propertyKey, final Integer def) {
		return ConfigurationManager.getConfigInstance().getInteger(propertyKey, def);
    }

	protected  HystrixCommandProperties getDefaultProperties() {
		return HystrixPropertiesFactory.getCommandProperties(DEFAULT_COMMAND_KEY, null);
	}

	protected  HystrixThreadPoolProperties getDefaultThreadPoolProperties() {
		return HystrixPropertiesFactory.getThreadPoolProperties(DEFAULT_THREADPOOL_KEY, null);
	}

	protected  void setProperty(final String propertyKey, Object value, Object fallbackValue) {
        final Object currentValue = ConfigurationManager.getConfigInstance().getProperty(propertyKey);

		if ((currentValue != null && !Objects.equal(currentValue, value))
				|| (currentValue == null && !Objects.equal(fallbackValue, value))) {
			if (ConfigurationManager.getConfigInstance() instanceof ConcurrentCompositeConfiguration) {
				((ConcurrentCompositeConfiguration) ConfigurationManager.getConfigInstance()).setOverrideProperty(
				        propertyKey, value);
			} else {
				ConfigurationManager.getConfigInstance().setProperty(propertyKey, value);
			}
		}
	}

    protected abstract String getName();

    private String createConfigKey(final String keyTemplate) {
        return String.format(keyTemplate, getName());
    }
}
