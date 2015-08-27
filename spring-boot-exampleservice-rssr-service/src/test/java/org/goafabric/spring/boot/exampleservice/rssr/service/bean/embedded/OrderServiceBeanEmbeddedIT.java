package org.goafabric.spring.boot.exampleservice.rssr.service.bean.embedded;

import org.goafabric.spring.boot.exampleservice.rssr.service.configuration.BaseConfiguration;
import org.goafabric.spring.boot.exampleservice.rssr.service.bean.OrderServiceBeanIT;
import org.goafabric.spring.boot.exampleservice.testconfiguration.RemoteIntegrationTestConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Embedded Tests that startup the webcontainer and execute remote REST calls at localhost
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {BaseConfiguration.class, RemoteIntegrationTestConfiguration.class})
@WebIntegrationTest
public class OrderServiceBeanEmbeddedIT extends OrderServiceBeanIT {

}
