package org.goafabric.spring.boot.exampleservice.rssr.service.bean.local;

import org.goafabric.spring.boot.exampleservice.rssr.service.bean.OrderServiceBeanIT;
import org.goafabric.spring.boot.exampleservice.rssr.util.SecurityCollaboratorUtility;
import org.goafabric.spring.boot.exampleservice.testconfiguration.LocalIntegrationTestConfiguration;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Local Tests, that to not startup the web container, but only all spring container Beans
 * Somewhat redundant to the embedded tests, but might be slighly faster during startup
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LocalIntegrationTestConfiguration.class)
public class OrderServiceBeanLocalIT extends OrderServiceBeanIT {
    @Autowired
    private SecurityCollaboratorUtility securityCollaboratorUtility;

    @Before
    public void setup() {
        securityCollaboratorUtility.authenticate();
    }

}
