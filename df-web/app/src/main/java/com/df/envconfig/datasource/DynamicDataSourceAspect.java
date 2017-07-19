package com.df.envconfig.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切换数据源Advice
 *
 * @author 单红宇(365384722)
 * @myblog http://blog.csdn.net/catoop/
 * @create 2016年1月23日
 */
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    private static final int SLAVE = 3;

    private static final String MASTER = "df";

    @Before("@within(ds)")
    public void changeDataSource4ClassAnnotation(JoinPoint point, TargetDataSource ds) throws Throwable {
        setDataSourceType(point, ds);
    }

    @After("@within(ds)")
    public void restoreDataSource4ClassAnnotation(JoinPoint point, TargetDataSource ds) {
        clearDataSourceType(point, ds);
    }

    @Before("@annotation(ds)")
    public void changeDataSource4MethodAnnotation(JoinPoint point, TargetDataSource ds) throws Throwable {
        setDataSourceType(point, ds);
    }

    @After("@annotation(ds)")
    public void restoreDataSource4MethodAnnotation(JoinPoint point, TargetDataSource ds) {
        clearDataSourceType(point, ds);
    }

    private void setDataSourceType(JoinPoint point, TargetDataSource ds) {
        int lastIndex = point.getArgs().length - 1;
        Object dbIndex = lastIndex >= 0 ? point.getArgs()[lastIndex] : "";

        String dsId = ds.name();
        if (!MASTER.equals(dsId)) {
            if (dbIndex instanceof Integer || dbIndex instanceof Long) {
                long dbSuffix = Long.valueOf(dbIndex.toString()) % SLAVE;
                dsId += String.format("%02d", dbSuffix == 0 ? SLAVE : dbSuffix);
            } else if (lastIndex > 0) {
                dsId += (dbIndex != null ? dbIndex : "");
            }
        }
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            LOGGER.error("数据源[{}]不存在，使用默认数据源 > {}", dsId, point.getSignature());
        } else {
            LOGGER.debug("Use DataSource : {} > {}", dsId, point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(dsId);
        }
    }

    private void clearDataSourceType(JoinPoint point, TargetDataSource ds) {
        LOGGER.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}
