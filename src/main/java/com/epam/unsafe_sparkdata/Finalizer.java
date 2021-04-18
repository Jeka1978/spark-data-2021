package com.epam.unsafe_sparkdata;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

/**
 * @author Evgeny Borisov
 */
public interface Finalizer {
    Object doAction(Dataset<Row> dataset, Class<?> model);
}
