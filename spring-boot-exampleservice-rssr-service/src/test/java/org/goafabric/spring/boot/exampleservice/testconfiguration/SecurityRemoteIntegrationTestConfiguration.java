package org.goafabric.spring.boot.exampleservice.testconfiguration;

import org.goafabric.common.client.rest.JerseyProxyBuilder;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.OrderService;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.constants.ExampleServiceConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.ws.rs.core.MediaType;

/**
 * Created by amautsch on 26.06.2015.
 */
@Configuration
@PropertySource("classpath:properties/remote-integration-test.properties")
public class SecurityRemoteIntegrationTestConfiguration {
    @Value("${webservice.url}")
    private String serviceUrl;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public OrderService orderServiceWrongUser() {
        return getBean(OrderService.class, "nouser", "nopassword");
    }


    private <T> T getBean(final Class<T> serviceInterface, final String username, final String password) {
        return JerseyProxyBuilder.newBuilder(serviceUrl + ExampleServiceConstants.ROOT_URL)
                .acceptContentType(MediaType.APPLICATION_JSON_TYPE)
                .authenticationCredentials(username, password)
                .build(serviceInterface);
    }

}
