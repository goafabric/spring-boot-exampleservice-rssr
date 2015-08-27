package org.goafabric.spring.boot.exampleservice.rssr.service.bean.remote;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.OrderService;
import org.goafabric.spring.boot.exampleservice.testconfiguration.RemoteIntegrationTestConfiguration;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by amautsch on 26.06.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RemoteIntegrationTestConfiguration.class)
public class OrderServiceBeanRemoteLoadNRIT {
    @Autowired
    private OrderService orderService;

    @Rule
    public ContiPerfRule rule = new ContiPerfRule();

    @PerfTest(invocations=5000, threads=100)
    @Test
    public void testDelete() {
        orderService.deleteOrder(1000l).isExecuted();
    }
}
