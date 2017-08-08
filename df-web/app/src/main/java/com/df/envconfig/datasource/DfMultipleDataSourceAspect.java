package com.df.envconfig.datasource;

import com.df.multipleds.spring.boot.autoconfigure.MultipleDataSourceAspect;
import com.df.multipleds.spring.boot.autoconfigure.MultipleDataSourceContextHolder;
import com.df.multipleds.spring.boot.autoconfigure.MultipleDataSourceProperties;
import com.df.multipleds.spring.boot.autoconfigure.TargetDataSource;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xuchengdong@qbao.com on 2017/7/22.
 */
@Component
public class DfMultipleDataSourceAspect extends MultipleDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DfMultipleDataSourceAspect.class);

    @Resource
    private MultipleDataSourceProperties multipleDataSourceProperties;

    @Override
    public void setDataSourceType(JoinPoint point, TargetDataSource ds) {
        int lastIndex = point.getArgs().length - 1;
        Object dbIndex = lastIndex >= 0 ? point.getArgs()[lastIndex] : "";

        String dsId = ds.name();
        if (!multipleDataSourceProperties.getDefaultDataSourcesName().equals(dsId)) {
            int slave = MultipleDataSourceContextHolder.dataSourceIds.size() - 1;
            if (dbIndex instanceof Integer || dbIndex instanceof Long) {
                long dbSuffix = Long.valueOf(dbIndex.toString()) % slave;
                dsId += String.format("%02d", dbSuffix == 0 ? slave : dbSuffix);
            } else if (lastIndex > 0) {
                dsId += (dbIndex != null ? dbIndex : "");
            }
        }
        if (!MultipleDataSourceContextHolder.containsDataSource(dsId)) {
            logger.error("数据源[{}]不存在，使用默认数据源 > {}", dsId, point.getSignature());
        } else {
            logger.debug("Use DataSource : {} > {}", dsId, point.getSignature());
            MultipleDataSourceContextHolder.setDataSourceType(dsId);
        }
    }
}
