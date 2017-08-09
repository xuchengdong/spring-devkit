package com.df.cassecurity.spring.boot.autoconfigure;

import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author xuchengdong@qbao.com on 2017/8/8.
 */
@Configuration
@ConditionalOnClass(CasAuthenticationProvider.class)
@EnableConfigurationProperties(CasSecurityServiceProperties.class)
public class CasSecurityAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    public ServiceProperties serviceProperties(CasSecurityServiceProperties casSecurityServiceProperties) {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(casSecurityServiceProperties.getService());
        serviceProperties.setAuthenticateAllArtifacts(casSecurityServiceProperties.isAuthenticateAllArtifacts());
        return serviceProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationEntryPoint casAuthenticationEntryPoint(CasSecurityServiceProperties casSecurityServiceProperties, ServiceProperties serviceProperties) {
        CasAuthenticationEntryPoint authenticationEntryPoint = new CasAuthenticationEntryPoint();
        authenticationEntryPoint.setServiceProperties(serviceProperties);
        authenticationEntryPoint.setLoginUrl(casSecurityServiceProperties.getLoginUrl());
        return authenticationEntryPoint;
    }

    @Bean
    @ConditionalOnMissingBean
    public Cas20ServiceTicketValidator ticketValidator(CasSecurityServiceProperties casSecurityServiceProperties) {
        return new Cas20ServiceTicketValidator(casSecurityServiceProperties.getCasServerUrlPrefix());
    }

}
