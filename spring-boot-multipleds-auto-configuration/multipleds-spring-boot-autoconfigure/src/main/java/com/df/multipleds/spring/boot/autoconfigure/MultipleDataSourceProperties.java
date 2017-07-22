package com.df.multipleds.spring.boot.autoconfigure;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuchengdong@qbao.com on 2017/7/20.
 */
@ConfigurationProperties(prefix = MultipleDataSourceProperties.MULTIPLEDATASOURCE_PREFIX)
public class MultipleDataSourceProperties {

    public static final String MULTIPLEDATASOURCE_PREFIX = "spring.datasource.multiple";

    //spring.datasource.multiple.defaultDataSourcesName=default
    //spring.datasource.multiple.default
    private String defaultDataSourcesName;

    //spring.datasource.multiple.multipleDataSourcesNames=user01,user02,user03
    //spring.datasource.multiple.user01.
    //spring.datasource.multiple.user02
    //spring.datasource.multiple.user03
    private String multipleDataSourcesNames;

    private RelaxedPropertyResolver multipleDataSourcesPropertyResolver;

    private Map<String, Object> defaultDataSourcesProperty = new HashMap<>();

    private Map<String, Map<String, Object>> multipleDataSourcesPropertys = new HashMap<>();


    public MultipleDataSourceProperties init(Environment env) {
        multipleDataSourcesPropertyResolver = new RelaxedPropertyResolver(env, MULTIPLEDATASOURCE_PREFIX + ".");

        String[] names = multipleDataSourcesNames.split(",");
        for (String multipleDataSourcesName : names) {
            multipleDataSourcesPropertys.put(multipleDataSourcesName, new HashMap<>(multipleDataSourcesPropertyResolver.getSubProperties(multipleDataSourcesName + ".")));
        }

        if (defaultDataSourcesName != null) {
            defaultDataSourcesProperty.putAll(multipleDataSourcesPropertyResolver.getSubProperties(defaultDataSourcesName + "."));
            multipleDataSourcesPropertys.put(defaultDataSourcesName, defaultDataSourcesProperty);
        } else {
            this.setDefaultDataSourcesName(names[0]);
        }

        for (Map.Entry<String, Map<String, Object>> entry : multipleDataSourcesPropertys.entrySet()) {
            Map<String, Object> dataSourcesProperty = entry.getValue();
            if (dataSourcesProperty.get("type") == null) {
                dataSourcesProperty.put("type", multipleDataSourcesPropertyResolver.getProperty("type"));
            }
        }
        return this;
    }

    public String getDefaultDataSourcesName() {
        return defaultDataSourcesName;
    }

    public void setDefaultDataSourcesName(String defaultDataSourcesName) {
        this.defaultDataSourcesName = defaultDataSourcesName;
    }

    public String getMultipleDataSourcesNames() {
        return multipleDataSourcesNames;
    }

    public void setMultipleDataSourcesNames(String multipleDataSourcesNames) {
        this.multipleDataSourcesNames = multipleDataSourcesNames;
    }

    public Map<String, Object> getDefaultDataSourcesProperty() {
        return defaultDataSourcesProperty;
    }


    public Map<String, Map<String, Object>> getMultipleDataSourcesPropertys() {
        return multipleDataSourcesPropertys;
    }

    public RelaxedPropertyResolver getMultipleDataSourcesPropertyResolver() {
        return multipleDataSourcesPropertyResolver;
    }

}
