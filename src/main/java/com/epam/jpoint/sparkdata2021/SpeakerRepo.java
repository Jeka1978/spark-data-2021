package com.epam.jpoint.sparkdata2021;

import com.epam.unsafe_sparkdata.SparkRepository;

import java.util.List;

/**
 * @author Evgeny Borisov
 */
public interface SpeakerRepo extends SparkRepository<Speaker> {

    List<Speaker> findByAgeBetween(int min, int max);
    long findByAgeGreaterThanCount(int min);
}
