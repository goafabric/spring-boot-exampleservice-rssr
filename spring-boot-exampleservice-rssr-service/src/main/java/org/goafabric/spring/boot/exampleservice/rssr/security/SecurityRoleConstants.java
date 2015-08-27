package org.goafabric.spring.boot.exampleservice.rssr.security;

public abstract class SecurityRoleConstants {

    //The Prefix "ROLE_" has to be added for in Memory Auth, it's not needed for LDAP Auth
    public static final String STANDARD_READ_ROLE =
            "hasRole('ROLE_STANDARD_READ_ROLE')";
    public static final String STANDARD_WRITE_ROLE
            = "hasRole('ROLE_STANDARD_WRITE_ROLE')";
}
