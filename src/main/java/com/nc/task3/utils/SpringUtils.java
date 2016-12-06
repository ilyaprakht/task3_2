package com.nc.task3.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringUtils {

    private static final String APPLICATION_CONTEXT_XML = "com/nc/task3/application_context.xml";

    private static ApplicationContext context;

    private SpringUtils() {}

    public static Object getBean(String beanName) {
        if (context == null) {
            context = new ClassPathXmlApplicationContext(new String[] {APPLICATION_CONTEXT_XML});
        }
        return context.getBean(beanName);
    }

}
