package com.mks.spring.boot;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.base.Preconditions;
import com.mks.utils.JVMUtils;


public final class SpringBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(JVMUtils.class);
    
    private SpringBootstrap() {
    }
    
    public static ClassPathXmlApplicationContext bootUsingSpring(String[] contextFilePathes, String[] systemParameters) {
        return bootUsingSpring(JVMUtils.getFirstInvokeClassSimpleName(), contextFilePathes, systemParameters);
    }
    
    public static ClassPathXmlApplicationContext bootUsingSpring(String systemFlag, String[] contextFilePathes,
            String[] systemParameters) {
        Preconditions.checkArgument(StringUtils.isNotBlank(systemFlag), "systemFlag is blank.");
        Preconditions.checkArgument((!ArrayUtils.isEmpty(contextFilePathes))
                && (contextFilePathes.length > 0), "contextFilePathes is empty.");

        long beginMTime = System.currentTimeMillis();
        JVMUtils.setProperties(systemParameters);
        try {
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(contextFilePathes);
//            ctx.registerShutdownHook();
            LOGGER.info(systemFlag + " boot in " + (System.currentTimeMillis() - beginMTime)
                    + " ms");
            return ctx;

        } catch (Exception e) {
            LOGGER.error(systemFlag + " boot occur error:", e);
            System.exit(-1);
            return null;
        }
    }
}
