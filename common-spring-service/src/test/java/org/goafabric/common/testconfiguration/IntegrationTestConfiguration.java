package org.goafabric.common.testconfiguration;

import org.goafabric.common.spring.service.helper.ExampleLogicBean;
import org.goafabric.common.spring.service.security.AuditAspect;
import org.goafabric.common.spring.service.sustainability.ResultHandlerAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by amautsch on 14.08.2015.
 */
@Configuration
@EnableAspectJAutoProxy
public class IntegrationTestConfiguration {

    @Bean
    public ExampleLogicBean exampleLogicBean() {
        return new ExampleLogicBean();
    }

    @Bean
    public ResultHandlerAspect resultHandlerAspect() {
        return new ResultHandlerAspect(ResultHandlerAspect.class.getName());
    }

    @Bean
    public AuditAspect auditLog() {
        return new AuditAspect("test_1_0", "AuditLog");
    }

}
