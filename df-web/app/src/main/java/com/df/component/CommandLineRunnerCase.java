package com.df.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author xuchengdong@qbao.com on 2017/7/28.
 */
@Component
public class CommandLineRunnerCase implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(CommandLineRunnerCase.class);

    public void run(String... args) {
        // Do something...
        for (String arg: args){
            logger.info(arg);
        }
    }
}
