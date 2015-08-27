package org.goafabric.common.client.rest;

import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
public class ResteasyProxyBuilderTest {

    private final ResteasyProxyBuilder testInstance
            = ResteasyProxyBuilder.newBuilder("http://example.com/rest");


    @Test(expected = IllegalArgumentException.class)
    public void testEnpointUrlExceptionIfNull() {
        ResteasyProxyBuilder.newBuilder(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testThrowIfNoConnectionPoolIsSet() {
        testInstance.build(TestInterface.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAcceptContentTypeExceptionIfNull() {
        testInstance.acceptContentType(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUsernameExceptionIfNull() {
        testInstance.authenticationCredentials(null, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordExceptionIfNull() {
        testInstance.authenticationCredentials("", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConnectionTimeoutExceptionIfNull() {
        testInstance.connectionTimeout(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequestTimeoutExceptionIfNull() {
        testInstance.requestTimeout(null);
    }

    @Test
    public void testBuildCompleteProxy() {
        final TestInterface proxy = testInstance.acceptContentType(MediaType.APPLICATION_JSON_TYPE)
                .authenticationCredentials("user", "")
                .connectionPool(1)
                .connectionTimeout(200)
                .requestTimeout(1000)
                .build(TestInterface.class);
        assertThat(proxy).isNotNull();
    }

}