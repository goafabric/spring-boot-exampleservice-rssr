package org.goafabric.spring.boot.exampleservice.rssr.security.configuration;

import org.goafabric.common.spring.service.security.AuditAspect;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.constants.ExampleServiceConstants;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.constants.VersionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * In memory security configuration
 * Real applications should use a more sophosticated approach like LDAP
 * For a proper example @see the doc/ldapsecurity folder
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SuppressWarnings("PMD.SignatureDeclareThrowsException")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").
                roles("STANDARD_READ_ROLE", "STANDARD_WRITE_ROLE");
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers(
                        "/management/hystrix.stream", "/hystrix/**", "/webjars/**", "/proxy.stream",
                        "/management/info", "/management/health", "/management/metrics",
                        "/", "/welcome/**",
                        ExampleServiceConstants.ROOT_URL + "/application.wadl"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    AuditAspect auditAspect() {
        return new AuditAspect("AuditLog", VersionData.APP_NAME_MAJOR_MINOR);
    }

}

