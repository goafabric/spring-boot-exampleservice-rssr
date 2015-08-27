package org.goafabric.spring.boot.exampleservice.rssr.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author amautsch
 *         Date: 23.12.14
 *         Time: 15:50
 */
public class SecurityCollaboratorUtility {
    @Autowired
    private AuthenticationManager authenticationManager;

    //@Value("${test.collaborator.user}")
    private String testUsername = "admin";
    //@Value("${test.collaborator.password}")
    private String testUserPassword = "admin";

    public void authenticate() {
        authenticate(testUsername, testUserPassword);
    }

    public void authenticate(final String userName, final String password) {
        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
        final Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

    /*
    public static void setupTruststore() {
        final URL url = TestCollaboratorUtility.class.getResource("/truststore/junit-truststore.jks");
        final String path = url.getPath().replace("target/test-classes","src/test/resources");
        System.setProperty("javax.net.ssl.trustStore", path);
        System.setProperty("javax.net.ssl.trustStorePassword", "secret");
    }
    */

}
