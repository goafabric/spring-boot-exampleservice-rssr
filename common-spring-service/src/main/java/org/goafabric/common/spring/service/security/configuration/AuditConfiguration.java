package org.goafabric.common.spring.service.security.configuration;

import org.goafabric.common.spring.service.security.AuditAspect;
import org.goafabric.common.spring.service.security.annotation.EnableAudit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author amautsch
 *         Date: 10.05.2016
 *         Time: 17:51
 */
@Configuration
public class AuditConfiguration implements ImportAware {
    private AnnotationAttributes annotationAttributes;

    /*
    @Bean
    public AuditMBean auditMbean() {
        return new AuditMBean();
    }

    @Bean
    public AuditMBeanAspect auditMbeanAspect() {
        return new AuditMBeanAspect();
    }
    */

    @Bean
    public AuditAspect auditAspect() {
        return new AuditAspect(
                annotationAttributes.getString("loggerName"),
                annotationAttributes.getString("applicationNameAndVersion"));
    }

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        final Map<String, Object> map =
                annotationMetadata.getAnnotationAttributes(EnableAudit.class.getName());
        annotationAttributes = AnnotationAttributes.fromMap(map);
        if (this.annotationAttributes == null) {
            throw new IllegalArgumentException(
                    "@EnableAudit is not present on importing class " + annotationMetadata.getClassName());
        }

    }

    /*
    @Bean
    public MBeanExporter mBeanExporter() throws MalformedObjectNameException {
        final MBeanExporter mBeanExporter = new MBeanExporter();
        if (annotationAttributes.getBoolean("enableMBean")) {
            mBeanExporter.setServer(ManagementFactory.getPlatformMBeanServer());
            mBeanExporter.registerManagedResource(auditMbean(),
                    ObjectName.getInstance("Audit", "name", "Audit"));
        }
        return mBeanExporter;
    }
    */

}
