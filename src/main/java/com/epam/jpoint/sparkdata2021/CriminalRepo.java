package com.epam.jpoint.sparkdata2021;

import com.epam.unsafe_sparkdata.SparkRepository;

import java.util.List;

/**
 * @author Evgeny Borisov
 */
public interface CriminalRepo extends SparkRepository<Criminal> {
    List<Criminal> findByNumberGreaterThanOrderByNumber(int min);

    List<Criminal> findByNameContains(String s);
}
