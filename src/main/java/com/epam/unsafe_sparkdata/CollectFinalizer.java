package com.epam.unsafe_sparkdata;

import lombok.SneakyThrows;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evgeny Borisov
 */
@Component("collect")
public class CollectFinalizer implements Finalizer {
    @Override
    @SneakyThrows
    public Object doAction(Dataset<Row> dataset, Class<?> modelClass) {
        Encoder<?> encoder = Encoders.bean(modelClass);
        List<String> listFieldNames = Arrays.stream(encoder.schema().fields()).filter(structField -> structField.dataType() instanceof ArrayType)
                .map(StructField::name)
                .collect(Collectors.toList());
        for (String fieldName : listFieldNames) {

            ParameterizedType genericType = (ParameterizedType) modelClass.getDeclaredField(fieldName).getGenericType();
            Class c = (Class) genericType.getActualTypeArguments()[0];
            dataset = dataset.withColumn(fieldName, functions.lit(null).cast(DataTypes.createArrayType(DataTypes.createStructType(Encoders.bean(c).schema().fields()))));
        }


        return dataset.as(encoder).collectAsList();

    }
}
