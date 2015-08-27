package org.goafabric.common.spring.service.resilience;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HystrixManagerTest {
    private DummyMBean testMBean = new DummyMBean();

    @Test
    public void testServiceNameIsCorrect() {
        assertThat(testMBean.getName()).isEqualTo("DummyMBean");
    }

    @Test
    public void testCircuitBreakerEnabled() {
        testMBean.setCircuitBreakerEnabled(false);
        assertThat(testMBean.getCircuitBreakerEnabled()).isFalse();
    }

    @Test
    public void testCircuitBreakerForceOpen() {
        testMBean.setCircuitBreakerForceOpen(true);
        assertThat(testMBean.getCircuitBreakerForceOpen()).isTrue();
    }

    @Test
    public void testRollingStatsTimeInMilliseconds() {
        testMBean.setRollingStatsTimeInMilliseconds(50000);
        assertThat(testMBean.getRollingStatsTimeInMilliseconds()).isEqualTo(50000);
    }

    @Test
    public void testCircuitBreakerRequestVolumeThreshold() {
        testMBean.setCircuitBreakerRequestVolumeThreshold(5);
        assertThat(testMBean.getCircuitBreakerRequestVolumeThreshold()).isEqualTo(5);
    }

    @Test
    public void testCircuitBreakerSleepWindowMillis() {
        testMBean.setCircuitBreakerSleepWindowMillis(3000);
        assertThat(testMBean.getCircuitBreakerSleepWindowMillis()).isEqualTo(3000);
    }

    @Test
    public void testCircuitBreakerErrorThresholdPercentage() {
        testMBean.setCircuitBreakerErrorThresholdPercentage(50);
        assertThat(testMBean.getCircuitBreakerErrorThresholdPercentage()).isEqualTo(50);
    }

    @Test
    public void testThreadTimeoutInMillis() {
        testMBean.setThreadTimeoutInMillis(8000);
        assertThat(testMBean.getThreadTimeoutInMillis()).isEqualTo(8000);
    }

    @Test
    public void testThreadPoolCoreSize() {
        testMBean.setThreadPoolCoreSize(15);
        assertThat(testMBean.getThreadPoolCoreSize()).isEqualTo(15);
    }

    @Test
    public void testThreadPoolQueueSizeRejectionThreshold() {
        testMBean.setThreadPoolQueueSizeRejectionThreshold(10);
        assertThat(testMBean.getThreadPoolQueueSizeRejectionThreshold()).isEqualTo(10);
    }

    @Test
    public void testThreadPoolMaxQueueSize() {
        testMBean.setThreadPoolMaxQueueSize(300);
        assertThat(testMBean.getThreadPoolMaxQueueSize()).isEqualTo(300);
    }
}