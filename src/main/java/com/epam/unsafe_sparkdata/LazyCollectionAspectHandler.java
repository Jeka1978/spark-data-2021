package com.epam.unsafe_sparkdata;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

/**
 * @author Evgeny Borisov
 */
@Aspect
public class LazyCollectionAspectHandler {
    @Autowired
    private FirstLevelCacheService cacheService;
    @Autowired
    private ConfigurableApplicationContext context;


    @Before("execution(* com.epam.unsafe_sparkdata.LazySparkList.*(..)) && execution(* java.util.List.*(..)) ")
    public void setLazyCollections(JoinPoint jp) {
        LazySparkList lazyList = (LazySparkList) jp.getTarget();
        if (!lazyList.initialized()) {
            List<Object> content = cacheService.readDataFor(lazyList.getOwnerId(), lazyList.getModelClass(), lazyList.getPathToSource(),lazyList.getForeignKeyName(), context);
            lazyList.setContent(content);
        }
    }
}





