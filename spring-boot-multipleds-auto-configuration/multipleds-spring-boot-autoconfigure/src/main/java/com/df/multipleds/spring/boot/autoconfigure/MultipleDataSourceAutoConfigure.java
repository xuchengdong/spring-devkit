package com.df.multipleds.spring.boot.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author xuchengdong@qbao.com on 2017/7/20.
 */
@Configuration
@EnableConfigurationProperties(MultipleDataSourceProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class MultipleDataSourceAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "spring.datasource.multiple", value = "enabled", havingValue = "true")
    DataSource dataSource(MultipleDataSourceProperties properties, Environment env) {
        Map<String, Map<String, Object>> multipleDataSourcesPropertys = properties.init(env).getMultipleDataSourcesPropertys();
        for (Map.Entry<String, Map<String, Object>> entry : multipleDataSourcesPropertys.entrySet()) {
            Map<String, Object> dataSourcesProperty = entry.getValue();
            if (dataSourcesProperty.get("type") == null) {
                dataSourcesProperty.put("type", env.getProperty("spring.datasource.type"));
            }
            if (dataSourcesProperty.get("username") == null) {
                dataSourcesProperty.put("username", env.getProperty("spring.datasource.username"));
            }
            if (dataSourcesProperty.get("password") == null) {
                dataSourcesProperty.put("password", env.getProperty("spring.datasource.password"));
            }
            if (dataSourcesProperty.get("url") == null) {
                dataSourcesProperty.put("url", env.getProperty("spring.datasource.url"));
            }
            if (dataSourcesProperty.get("driver-class-name") == null) {
                dataSourcesProperty.put("driver-class-name", env.getProperty("spring.datasource.driver-class-name"));
            }
        }
        return MultipleDataSourceBuilder.create().build(multipleDataSourcesPropertys, properties);
    }

}
