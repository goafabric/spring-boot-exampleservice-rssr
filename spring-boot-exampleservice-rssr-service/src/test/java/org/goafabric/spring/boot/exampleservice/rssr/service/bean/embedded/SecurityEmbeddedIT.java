package org.goafabric.spring.boot.exampleservice.rssr.service.bean.embedded;

import org.goafabric.spring.boot.exampleservice.rssr.service.bean.SecurityIT;
import org.goafabric.spring.boot.exampleservice.rssr.service.configuration.BaseConfiguration;
import org.goafabric.spring.boot.exampleservice.testconfiguration.SecurityRemoteIntegrationTestConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by amautsch on 30.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {BaseConfiguration.class, SecurityRemoteIntegrationTestConfiguration.class})
@WebIntegrationTest
public class SecurityEmbeddedIT extends SecurityIT {

}
