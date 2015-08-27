package org.goafabric.common.spring.service.resilience;

public class DummyMBean extends HystrixManager {
    @Override
    protected String getName() {
        return "DummyMBean";
    }
}
