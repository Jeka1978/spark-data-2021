package com.epam.unsafe_sparkdata;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Evgeny Borisov
 */
@Component("json")
public class JsonDataExtractor implements DataExtractor {
    @Override
    public Dataset<Row> load(String pathToData, ConfigurableApplicationContext context) {
       return context.getBean(SparkSession.class).read().json(pathToData);
    }
}
