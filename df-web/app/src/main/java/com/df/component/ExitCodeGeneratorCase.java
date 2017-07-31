package com.df.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeGenerator;

/**
 * @author xuchengdong@qbao.com on 2017/7/28.
 */
public class ExitCodeGeneratorCase implements ExitCodeGenerator {

    private static Logger logger = LoggerFactory.getLogger(ExitCodeGeneratorCase.class);

    @Override
    public int getExitCode() {
        logger.info("exit 0");
        return 0;
    }
}
