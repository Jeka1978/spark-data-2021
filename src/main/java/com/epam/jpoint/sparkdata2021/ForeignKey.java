package com.epam.jpoint.sparkdata2021;/**
 * @author Evgeny Borisov
 */

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface ForeignKey {
    String value();
}
