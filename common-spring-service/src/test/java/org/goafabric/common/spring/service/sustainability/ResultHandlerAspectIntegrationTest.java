package org.goafabric.common.spring.service.sustainability;

import org.goafabric.common.service.result.CommonErrorCode;
import org.goafabric.common.spring.service.helper.BasicResult;
import org.goafabric.common.spring.service.helper.ExampleLogicBean;
import org.goafabric.common.testconfiguration.IntegrationTestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by amautsch on 14.08.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class ResultHandlerAspectIntegrationTest {
    @Autowired
    ExampleLogicBean exampleLogicBean;

    @Test
    public void testIsExecutedTrue() {
        assertThat(exampleLogicBean.create().isExecuted()).isTrue();
        assertThat(exampleLogicBean.get().isExecuted()).isTrue();
    }

    @Test
    public void testIsExecutedFalse() {
        final BasicResult basicResult = exampleLogicBean.update();
        Assertions.assertThat(basicResult.isExecuted()).isFalse();
        Assertions.assertThat(basicResult.getErrorCode()).isEqualTo(CommonErrorCode.GENERAL_APPLICATION_EXCEPTION);
    }
}
