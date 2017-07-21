package com.df.multipleds.spring.boot.autoconfigure;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author xuchengdong@qbao.com on 2017/7/21.
 */
public class RoutingDataSource extends AbstractRoutingDataSource {
    protected Object determineCurrentLookupKey() {
        return MultipleDataSourceContextHolder.getDataSourceType();
    }
}
