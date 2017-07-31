package com.df.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @author xuchengdong@qbao.com on 2017/7/28.
 */
@Component
public class DisposableBeanCase implements DisposableBean {
    private static Logger logger = LoggerFactory.getLogger(DisposableBeanCase.class);

    @Override
    public void destroy() throws Exception {
        logger.info("==========destroy=======");
    }
}
