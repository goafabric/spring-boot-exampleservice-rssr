package org.goafabric.common.client.rest;

import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
public class JerseyProxyBuilderTest {

    private final JerseyProxyBuilder jerseyProxyBuilder
            = JerseyProxyBuilder.newBuilder("http://example.com/rest");


    @Test(expected = IllegalArgumentException.class)
    public void testEnpointUrlExceptionIfNull() {
        JerseyProxyBuilder.newBuilder(null);
    }

    @Test
    public void testBuildMinimalProxy() {
        assertThat(jerseyProxyBuilder.build(TestInterface.class)).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAcceptContentTypeExceptionIfNull() {
        jerseyProxyBuilder.acceptContentType(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUsernameExceptionIfNull() {
        jerseyProxyBuilder.authenticationCredentials(null, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordExceptionIfNull() {
        jerseyProxyBuilder.authenticationCredentials("", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConnectionTimeoutExceptionIfNull() {
        jerseyProxyBuilder.connectionTimeout(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequestTimeoutExceptionIfNull() {
        jerseyProxyBuilder.requestTimeout(null);
    }

    @Test
    public void testBuildCompleteProxy() {
        final TestInterface proxy = jerseyProxyBuilder.acceptContentType(MediaType.APPLICATION_JSON_TYPE)
                .authenticationCredentials("user", "password")
                .connectionTimeout(200)
                .requestTimeout(1000)
                .enableLogging()
                .build(TestInterface.class);
        assertThat(proxy).isNotNull();
    }

}