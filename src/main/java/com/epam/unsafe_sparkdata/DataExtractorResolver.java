package com.epam.unsafe_sparkdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Evgeny Borisov
 */
@Component
public class DataExtractorResolver {


    @Autowired
    private Map<String,DataExtractor> extractorMap;


    public DataExtractor resolve(String pathToData) {
        String fileExtension = pathToData.split("\\.")[1];
        return extractorMap.get(fileExtension);
    }
}
