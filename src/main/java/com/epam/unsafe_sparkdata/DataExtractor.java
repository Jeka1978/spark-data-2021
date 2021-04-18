package com.epam.unsafe_sparkdata;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Evgeny Borisov
 */
public interface DataExtractor {
    Dataset<Row> load(String pathToData, ConfigurableApplicationContext context);
}
