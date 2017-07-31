package com.df.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xuchengdong@qbao.com on 2017/7/28.
 */
@Component
public class ApplicationArgumentsCase {

    private static Logger logger = LoggerFactory.getLogger(ApplicationArgumentsCase.class);

    @Autowired
    public ApplicationArgumentsCase(ApplicationArguments args) {
        boolean debug = args.containsOption("debug");
        List<String> files = args.getNonOptionArgs();
        // if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]

        logger.info(String.valueOf(debug));
        logger.info(files.toString());
    }

}
