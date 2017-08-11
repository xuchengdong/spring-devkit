package com.df.envconfig.security;

import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        auth.authenticationProvider(getApplicationContext().getBean(CasAuthenticationProvider.class));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/test/**", "/logoutSuccess/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated().and()
                .logout()
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/logoutSuccess")
//                .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .addLogoutHandler(new SecurityContextLogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        super.logout(request, response, authentication);
                        Cookie[] cookies = request.getCookies();
                        for (Cookie c : cookies) {
                            c.setValue(null);
                            c.setDomain("qbao.com");
                            c.setPath("/");
                            c.setMaxAge(0);
                            response.addCookie(c);
                        }
                    }
                })
                .deleteCookies()
                .and();

        ApplicationContext context = http.getSharedObject(ApplicationContext.class);
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = context.getBean(CasAuthenticationEntryPoint.class);
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
    public CasAuthenticationProvider casAuthenticationProvider(ServiceProperties serviceProperties, Cas20ServiceTicketValidator ticketValidator, CasUserDetailsService userDetailsService) {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setServiceProperties(serviceProperties);
        casAuthenticationProvider.setTicketValidator(ticketValidator);
        casAuthenticationProvider.setUserDetailsService(userDetailsService);
        casAuthenticationProvider.setKey("password");
        return casAuthenticationProvider;
    }

}
