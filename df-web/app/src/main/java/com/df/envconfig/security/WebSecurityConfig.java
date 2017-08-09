package com.df.envconfig.security;

import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author xuchengdong@qbao.com on 2017/8/7.
 */
@EnableConfigurationProperties
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        ApplicationContext context = getApplicationContext();
        AuthenticationProvider authenticationProvider = context.getBean(AuthenticationProvider.class);
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated().and()
                .formLogin();

        ApplicationContext context = http.getSharedObject(ApplicationContext.class);

        AuthenticationEntryPoint casAuthenticationEntryPoint = context.getBean(AuthenticationEntryPoint.class);
        CasAuthenticationFilter casAuthenticationFilter = context.getBean(CasAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint).and().addFilterAt(casAuthenticationFilter, CasAuthenticationFilter.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public CasAuthenticationFilter casAuthenticationFilter(ServiceProperties serviceProperties, AuthenticationManager authenticationManager) {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        casAuthenticationFilter.setServiceProperties(serviceProperties);
        casAuthenticationFilter.setAuthenticationManager(authenticationManager);
        return casAuthenticationFilter;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationProvider casAuthenticationProvider(ServiceProperties serviceProperties, Cas20ServiceTicketValidator ticketValidator, CasUserDetailsService userDetailsService) {
        CasAuthenticationProvider authenticationProvider = new CasAuthenticationProvider();
        authenticationProvider.setServiceProperties(serviceProperties);
        authenticationProvider.setTicketValidator(ticketValidator);
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setKey("password");
        return authenticationProvider;
    }

}
