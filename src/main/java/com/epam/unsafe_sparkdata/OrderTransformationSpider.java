package com.epam.unsafe_sparkdata;

import org.springframework.stereotype.Component;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Evgeny Borisov
 */
@Component("orderBy")
public class OrderTransformationSpider implements TransformationSpider {
    @Override
    public Tuple2<SparkTransformation,List<String>> getTransformation(List<String> methodWords, Set<String> fieldNames) {
        String sortColumn = WordsMatcher.findAndRemoveMatchingPiecesIfExists(fieldNames, methodWords);
        List<String> additional = new ArrayList<>();
        while (!methodWords.isEmpty() && methodWords.get(0).equalsIgnoreCase("and")) {
            methodWords.remove(0);
            additional.add(WordsMatcher.findAndRemoveMatchingPiecesIfExists(fieldNames, methodWords));
        }
        additional.add(0, sortColumn);

        return new Tuple2<>(new SortTransformation(), additional);

    }
}
