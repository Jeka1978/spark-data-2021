package com.epam.unsafe_sparkdata;

import lombok.Data;
import lombok.experimental.Delegate;

import java.util.List;

/**
 * @author Evgeny Borisov
 */
@Data
public class LazySparkList implements List {


    @Delegate
    private List content;

    private long ownerId;

    private Class<?> modelClass;

    private String foreignKeyName;

    private String pathToSource;

    public boolean initialized(){
        return content != null /*&& !content.isEmpty()*/;
    }

}








