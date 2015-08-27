package org.goafabric.spring.boot.exampleservice.rssr.service.bean.remote;

import org.goafabric.spring.boot.exampleservice.rssr.service.bean.OrderServiceBeanIT;
import org.goafabric.spring.boot.exampleservice.testconfiguration.RemoteIntegrationTestConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Remote Tests that communicate to a remote service via REST
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RemoteIntegrationTestConfiguration.class)
public class OrderServiceBeanRemoteNRIT extends OrderServiceBeanIT {

}
