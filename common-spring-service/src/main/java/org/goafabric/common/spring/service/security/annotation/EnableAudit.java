package org.goafabric.common.spring.service.security.annotation;

import org.goafabric.common.spring.service.security.configuration.AuditConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author amautsch
 *         Date: 10.05.2016
 *         Time: 17:48
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AuditConfiguration.class)
public @interface EnableAudit {
    String loggerName();
    String applicationNameAndVersion();
    boolean enableMBean() default true;
}
