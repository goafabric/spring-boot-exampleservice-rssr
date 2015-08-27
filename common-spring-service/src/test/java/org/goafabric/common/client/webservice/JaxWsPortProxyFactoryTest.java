package org.goafabric.common.client.webservice;

import org.junit.Test;

import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * Created by amautsch on 07.08.2015.
 */
public class JaxWsPortProxyFactoryTest {
    @Test(expected = IllegalArgumentException.class)
    public void testGetBean() throws Exception {
        JaxWsPortProxyFactory.getBean(null, "", "http://", "http://");
        failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBeanWithTimeout() throws Exception {
        JaxWsPortProxyFactory.getBean(null, "", "http://", "http://", 0);
        failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBeanWithUserNamePassword() throws Exception {
        JaxWsPortProxyFactory.getBean(null, "", "http://", "http://", 0, "", "");
        failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
    }
}