package com.epam.unsafe_sparkdata;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Component;

/**
 * @author Evgeny Borisov
 */
@Component("count")
public class CountFinalizer implements Finalizer {
    @Override
    public Object doAction(Dataset<Row> dataset, Class<?> model) {
        return dataset.count();
    }
}
