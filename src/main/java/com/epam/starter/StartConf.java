package com.epam.starter;

import com.epam.unsafe_sparkdata.FirstLevelCacheService;
import com.epam.unsafe_sparkdata.LazyCollectionAspectHandler;
import com.epam.unsafe_sparkdata.LazySparkList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Evgeny Borisov
 */
@Configuration
public class StartConf {

    @Bean
    @Scope("prototype")
    public LazySparkList lazySparkList(){
        return new LazySparkList();
    }

    @Bean
    public FirstLevelCacheService firstLevelCacheService(){
        return new FirstLevelCacheService();
    }

    @Bean
    public LazyCollectionAspectHandler lazyCollectionAspectHandler(){
        return new LazyCollectionAspectHandler();
    }

}
