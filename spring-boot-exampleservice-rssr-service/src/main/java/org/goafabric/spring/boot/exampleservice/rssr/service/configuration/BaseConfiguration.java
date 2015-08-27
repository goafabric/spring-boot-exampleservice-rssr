package org.goafabric.spring.boot.exampleservice.rssr.service.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author amautsch
 *         Date: 10.02.15
 *         Time: 14:18
 */
@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"org.goafabric.spring.boot.exampleservice.rssr"})
public class BaseConfiguration {
}
