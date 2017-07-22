package com.df.multipleds.spring.boot.autoconfigure;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
@ConditionalOnMissingBean(com.df.multipleds.spring.boot.autoconfigure.MultipleDataSourceAspect.class)
public class MultipleDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(MultipleDataSourceAspect.class);

    @Before("@within(ds)")
    protected void changeDataSource4ClassAnnotation(JoinPoint point, TargetDataSource ds) throws Throwable {
        setDataSourceType(point, ds);
    }

    @After("@within(ds)")
    protected void restoreDataSource4ClassAnnotation(JoinPoint point, TargetDataSource ds) {
        clearDataSourceType(point, ds);
    }

    @Before("@annotation(ds)")
    protected void changeDataSource4MethodAnnotation(JoinPoint point, TargetDataSource ds) throws Throwable {
        setDataSourceType(point, ds);
    }

    @After("@annotation(ds)")
    protected void restoreDataSource4MethodAnnotation(JoinPoint point, TargetDataSource ds) {
        clearDataSourceType(point, ds);
    }

    public void setDataSourceType(JoinPoint point, TargetDataSource ds) {
        String dsId = ds.name();
        if (!MultipleDataSourceContextHolder.containsDataSource(dsId)) {
            logger.error("数据源[{}]不存在，使用默认数据源 > {}", ds.name(), point.getSignature());
        } else {
            logger.debug("Use DataSource : {} > {}", ds.name(), point.getSignature());
            MultipleDataSourceContextHolder.setDataSourceType(ds.name());
        }
    }

    protected void clearDataSourceType(JoinPoint point, TargetDataSource ds) {
        logger.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
        MultipleDataSourceContextHolder.clearDataSourceType();
    }

}
