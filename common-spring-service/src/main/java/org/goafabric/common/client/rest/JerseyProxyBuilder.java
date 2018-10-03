package org.goafabric.common.client.rest;

import lombok.NonNull;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * A simple REST clientbuilder class to provide a convenient way to build client proxies for a REST API. Encapsulates
 * the underlying JAX-RS implementation of the client.
 * <p>
 * The simplest way to create a proxy client for your REST API is the following:
 * <pre>
 * ServiceInterface service = JerseyProxyBuilder.newBuilder("http://yourendpoint.de/rest")
 *     .build(ServiceInterface.class);
 * </pre>
 * In this example we create a client for the resource <code>http://yourendpoint.de/rest/&lt;resource&gt;</code> and
 * all supported operations on this resource. The actual resource name is defined as the @Path-annotation in the
 * ServiceInterface.
 * </p>
 */
public final class JerseyProxyBuilder {

    private final String endpointUrl;
    private final JerseyClientBuilder jerseyBuilder;
    private final ContentTypeRequestFilter contentTypeRequestFilter;

    private JerseyProxyBuilder(@NonNull final String endpointUrl) {
        this.endpointUrl = endpointUrl;
        jerseyBuilder = new JerseyClientBuilder();
        contentTypeRequestFilter = new ContentTypeRequestFilter();
    }

    /**
     * Create a new JerseyProxyBuilder for a given REST endpoint.
     * <p>
     * The uri is the root path of the REST API, not the particular resource you want to consume. The resource
     * is defined by the service interface you choose.
     * </p>
     * @param uri mustn't be null.
     * @return A new JerseyProxyBuilder.
     */
    public static JerseyProxyBuilder newBuilder(final String uri) {
        return new JerseyProxyBuilder(uri);
    }

    /**
     * Set your preferred content-type of the representation.
     * <p>
     *     If no content-type is set, the server chooses the representation and the client has to deal with it.
     * </p>
     * @param contentType mustn't be null.
     * @return JerseyProxyBuilder
     */
    public JerseyProxyBuilder acceptContentType(@NonNull final MediaType contentType) {
        if (MediaType.APPLICATION_JSON_TYPE.equals(contentType)) {
            jerseyBuilder.register(JacksonFeature.class);
        }

        contentTypeRequestFilter.addContentType(contentType);
        return this;
    }

    /**
     * Set your user credentials for every request.
     * <p>
     *     <b>NOTE: Only HTTP basic authentication is currently supported.</b>
     * </p>
     * @param username mustn't be null.
     * @param password mustn't be null.
     * @return JerseyProxyBuilder
     */
    public JerseyProxyBuilder authenticationCredentials(
            @NonNull final String username, @NonNull final String password) {
        jerseyBuilder.register(HttpAuthenticationFeature.basic(username, password));
        return this;
    }

    /**
     * Set the connection timeout for your client.
     * @param timeoutInMillis mustn't be null.
     * @return JerseyProxyBuilder
     */
    public JerseyProxyBuilder connectionTimeout(@NonNull final Integer timeoutInMillis) {
        jerseyBuilder.property(ClientProperties.CONNECT_TIMEOUT, timeoutInMillis);
        return this;
    }

    /**
     * Set the read timeout for every request.
     * @param timeoutInMillis mustn't be null.
     * @return JerseyProxyBuilder
     */
    public JerseyProxyBuilder requestTimeout(@NonNull final Integer timeoutInMillis) {
        jerseyBuilder.property(ClientProperties.READ_TIMEOUT, timeoutInMillis);
        return this;
    }

    /**
     * Enable standard logging of http requests
     * @return JerseyProxyBuilder
     */
    public JerseyProxyBuilder enableLogging() {
        jerseyBuilder.register(LoggingFilter.class);
        return this;
    }

    /**
     * Create a proxy which implements your given interface.
     * @param serviceInterface mustn't be null.
     * @param <T> Type of your JAX-RS annotated interface.
     * @return A proxy for that particular resource.
     */
    public <T> T build(@NonNull final Class<T> serviceInterface) {
        jerseyBuilder.register(contentTypeRequestFilter);
        final WebTarget target = jerseyBuilder.build().target(endpointUrl);
        return WebResourceFactory.newResource(serviceInterface, target);
    }
}