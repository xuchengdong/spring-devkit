package com.df.multipleds.spring.boot.autoconfigure;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuchengdong@qbao.com on 2017/7/21.
 */
public class MultipleDataSourceBuilder {
    public static MultipleDataSourceBuilder create() {
        return new MultipleDataSourceBuilder();
    }

    public AbstractRoutingDataSource build(Map<String, Map<String, Object>> multipleDataSourcesPropertys, MultipleDataSourceProperties properties) {
        Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
        for (Map.Entry<String, Map<String, Object>> entry : multipleDataSourcesPropertys.entrySet()) {
            Map<String, Object> dataSourcesProperty = entry.getValue();
            dataSourceMap.put(entry.getKey(), dataBinder(buildDataSource(dataSourcesProperty), properties));
            MultipleDataSourceContextHolder.dataSourceIds.add(entry.getKey());
        }

        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(dataSourceMap.get(properties.getDefaultDataSourcesName()));
        routingDataSource.setTargetDataSources(dataSourceMap);
        return routingDataSource;
    }

    @SuppressWarnings("unchecked")
    private DataSource buildDataSource(Map<String, Object> dsMap) {
        try {
            Object type = dsMap.get("type");
            if (type == null)
                type = "org.apache.tomcat.jdbc.pool.DataSource";

            Class<? extends DataSource> dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);

            String driverClassName = (String) dsMap.get("driver-class-name");
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();

            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DataSource dataBinder(DataSource dataSource, MultipleDataSourceProperties properties) {
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        dataBinder.setIgnoreNestedProperties(false);
        dataBinder.setIgnoreInvalidFields(false);
        dataBinder.setIgnoreUnknownFields(true);
        dataBinder.bind(new MutablePropertyValues(properties.getMultipleDataSourcesPropertyResolver().getSubProperties(".")));
        return dataSource;
    }
}
