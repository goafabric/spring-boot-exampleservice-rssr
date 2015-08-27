package org.goafabric.spring.boot.exampleservice.testconfiguration;

import org.goafabric.spring.boot.exampleservice.rssr.service.configuration.BaseConfiguration;
import org.goafabric.spring.boot.exampleservice.rssr.service.bean.OrderServiceBean;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.OrderService;
import org.goafabric.spring.boot.exampleservice.rssr.util.SecurityCollaboratorUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by amautsch on 26.06.2015.
 */
@Configuration
@PropertySource("classpath:application.properties")
@Import({BaseConfiguration.class})
public class LocalIntegrationTestConfiguration {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public SecurityCollaboratorUtility securityCollaboratorUtility() {
        return new SecurityCollaboratorUtility();
    }


    @Bean
    public OrderService orderService() {
        return new OrderServiceBean();
    }

}
