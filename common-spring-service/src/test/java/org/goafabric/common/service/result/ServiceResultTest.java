package org.goafabric.common.service.result;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author amautsch
 *         Date: 02.03.15
 *         Time: 16:29
 */
public class ServiceResultTest {
    private static final class MockServiceResult extends ServiceResult {

    }

    @Test
    public void testServiceResult() {
        ServiceResult serviceResult = new MockServiceResult();
        serviceResult.setExecuted(false);
        serviceResult.setErrorCode(CommonErrorCode.GENERAL_APPLICATION_EXCEPTION);
        serviceResult.setErrorDetails("errordetails");

        assertThat(serviceResult.isExecuted()).isFalse();
        assertThat(serviceResult.getErrorCode()).isEqualTo(CommonErrorCode.GENERAL_APPLICATION_EXCEPTION);
        assertThat(serviceResult.getErrorDetails()).isEqualTo("errordetails");
    }
}
