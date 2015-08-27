package org.goafabric.common.client.webservice;

import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author amautsch
 *         Date: 12.02.15
 *         Time: 14:22
 */
public abstract class JaxWsPortProxyFactory {
    @SuppressWarnings("unchecked")
    public static <T> T getBean(
            Class<T> serviceInterface, String serviceName, String serviceUrl, String wsdlUrl)
            throws MalformedURLException {

        final JaxWsPortProxyFactoryBean jaxWsPortProxyFactoryBean =
                createFactoryBean(serviceInterface, serviceName, serviceUrl, wsdlUrl,
                        null, null, null);

        jaxWsPortProxyFactoryBean.afterPropertiesSet();
        return (T) jaxWsPortProxyFactoryBean.getObject();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(
            Class<T> serviceInterface, String serviceName, String serviceUrl, String wsdlUrl,
            Integer timeoutOut)
            throws MalformedURLException {

        final JaxWsPortProxyFactoryBean jaxWsPortProxyFactoryBean =
                createFactoryBean(serviceInterface, serviceName, serviceUrl, wsdlUrl,
                        timeoutOut, null, null);

        jaxWsPortProxyFactoryBean.afterPropertiesSet();
        return (T) jaxWsPortProxyFactoryBean.getObject();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(
            Class<T> serviceInterface, String serviceName, String serviceUrl, String wsdlUrl,
            Integer timeOut, String username, String password)
            throws MalformedURLException {

        final JaxWsPortProxyFactoryBean jaxWsPortProxyFactoryBean =
                createFactoryBean(serviceInterface, serviceName, serviceUrl, wsdlUrl,
                        timeOut, username, password);

        jaxWsPortProxyFactoryBean.afterPropertiesSet();
        return (T) jaxWsPortProxyFactoryBean.getObject();
    }


    private static <T> JaxWsPortProxyFactoryBean createFactoryBean(
            Class<T> serviceInterface, String serviceName, String serviceUrl, String wsdlUrl,
            Integer timeOut, String username, String password)
            throws MalformedURLException {

        final JaxWsPortProxyFactoryBean factory = new JaxWsPortProxyFactoryBean();

        factory.setServiceInterface(serviceInterface);
        factory.setServiceName(serviceName);
        factory.setEndpointAddress(serviceUrl);
        factory.setWsdlDocumentUrl(new URL(wsdlUrl));
        factory.setUsername(username);
        factory.setPassword(password);

        if (timeOut != null)
        {
            // JAX WS
            factory.addCustomProperty("com.sun.xml.ws.connect.timeout", timeOut);
            factory.addCustomProperty("com.sun.xml.ws.request.timeout", timeOut);
            // Sun JAX WS
            factory.addCustomProperty("com.sun.xml.internal.ws.connect.timeout", timeOut);
            factory.addCustomProperty("com.sun.xml.internal.ws.request.timeout", timeOut);
            // Javax
            factory.addCustomProperty("javax.xml.ws.client.connectionTimeout", timeOut);
            factory.addCustomProperty("javax.xml.ws.client.receiveTimeout", timeOut);
        }
        return factory;
    }

}
