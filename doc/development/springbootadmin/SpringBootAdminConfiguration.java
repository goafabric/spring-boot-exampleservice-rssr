package org.goafabric.spring.boot.exampleservice.rssr.service.configuration;

import de.codecentric.boot.admin.config.SpringBootAdminClientAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty("spring.boot.admin.url")
@Import(value = SpringBootAdminClientAutoConfiguration.class)
public class SpringBootAdminConfiguration {
}
