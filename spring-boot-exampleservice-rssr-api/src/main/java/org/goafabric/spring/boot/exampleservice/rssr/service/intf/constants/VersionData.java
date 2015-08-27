package org.goafabric.spring.boot.exampleservice.rssr.service.intf.constants;

/**
 * Created by amautsch on 26.06.2015.
 *
 * This is just a dummy example
 * !! Real application should use maven filtering to replace the version numbers !!
 */
public class VersionData {
    public static final String APP_NAME = "orderservice";
    public static final String JNDI_MAJOR_MINOR = "1_0";
    public static final String APP_NAME_MAJOR_MINOR = APP_NAME + JNDI_MAJOR_MINOR;

    public VersionData() {
    }
}
