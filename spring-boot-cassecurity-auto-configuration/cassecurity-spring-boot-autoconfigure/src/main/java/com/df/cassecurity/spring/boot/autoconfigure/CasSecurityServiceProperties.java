package com.df.cassecurity.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xuchengdong@qbao.com on 2017/8/7.
 */
@ConfigurationProperties(prefix = "security.cas")
public class CasSecurityServiceProperties {

    private String service;
    private String casServerUrlPrefix;
    private String loginUrl;
    private boolean authenticateAllArtifacts;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCasServerUrlPrefix() {
        return casServerUrlPrefix;
    }

    public void setCasServerUrlPrefix(String casServerUrlPrefix) {
        this.casServerUrlPrefix = casServerUrlPrefix;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public boolean isAuthenticateAllArtifacts() {
        return authenticateAllArtifacts;
    }

    public void setAuthenticateAllArtifacts(boolean authenticateAllArtifacts) {
        this.authenticateAllArtifacts = authenticateAllArtifacts;
    }
}
