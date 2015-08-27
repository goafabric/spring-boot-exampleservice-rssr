package org.goafabric.spring.boot.exampleservice.rssr.service.bean.remote;

import org.goafabric.spring.boot.exampleservice.rssr.service.bean.SecurityIT;
import org.goafabric.spring.boot.exampleservice.testconfiguration.SecurityRemoteIntegrationTestConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by amautsch on 30.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SecurityRemoteIntegrationTestConfiguration.class)
public class SecurityRemoteNRIT extends SecurityIT {
}
