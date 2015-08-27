package org.goafabric.spring.boot.exampleservice.rssr.adapter;

import org.goafabric.common.spring.service.resilience.HystrixManager;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * Created by amautsch on 28.07.2015.
 */

@Component
@ManagedResource(objectName = AdapterConstants.MBEAN_OBJECT_NAME + ":name=" + AdapterConstants.CANCELATION_SERVICE)
public class CancelationServiceHystrixMBean extends HystrixManager {

    @Override
    protected String getName() {
        return AdapterConstants.CANCELATION_SERVICE;
    }
}
