package com.epam.unsafe_sparkdata;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import scala.Tuple2;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Evgeny Borisov
 */
@Component
@RequiredArgsConstructor
public class SparkInvocationHandlerFactory {

    private final DataExtractorResolver resolver;
    private final Map<String, TransformationSpider> spiderMap;
    private final Map<String, Finalizer> finalizerMap;


    @Setter
    private ConfigurableApplicationContext realContext;


    public SparkInvocationHandler create(Class<? extends SparkRepository> sparkRepoInterface) {
        Class<?> modelClass = getModelClass(sparkRepoInterface);
        String pathToData = modelClass.getAnnotation(Source.class).value();
        Set<String> fieldNames = getFieldNames(modelClass);
        DataExtractor dataExtractor = resolver.resolve(pathToData);
        Map<Method, List<Tuple2<SparkTransformation,List<String>>>> transformationChain = new HashMap<>();
        Map<Method, Finalizer> method2Finalizer = new HashMap<>();

        Method[] methods = sparkRepoInterface.getMethods();
        for (Method method : methods) {
            TransformationSpider currentSpider = null;
            List<String> methodWords = WordsMatcher.toWordsByJavaConvention(method.getName());
            List<Tuple2<SparkTransformation,List<String>>> transformations = new ArrayList<>();
            while (methodWords.size() > 1) {
                String spiderName = WordsMatcher.findAndRemoveMatchingPiecesIfExists(spiderMap.keySet(), methodWords);
                if (!spiderName.isEmpty()) {
                    currentSpider = spiderMap.get(spiderName);
                }
                transformations.add(currentSpider.getTransformation(methodWords,fieldNames));
            }
            transformationChain.put(method, transformations);
            String finalizerName = "collect";
            if (methodWords.size() == 1) {
                finalizerName = Introspector.decapitalize(methodWords.get(0));
            }
            method2Finalizer.put(method, finalizerMap.get(finalizerName));
        }


        return SparkInvocationHandler.builder()
                .modelClass(modelClass)
                .pathToData(pathToData)
                .dataExtractor(dataExtractor)
                .transformationChain(transformationChain)
                .finalizerMap(method2Finalizer)
                .postFinalizer(new LazyCollectionInjectorPostFinalizer(realContext))
                .context(realContext)
                .build();

    }


    private Class<?> getModelClass(Class<? extends SparkRepository> repoInterface) {
        ParameterizedType genericInterface = (ParameterizedType) repoInterface.getGenericInterfaces()[0];
        Class<?> modelClass = (Class<?>) genericInterface.getActualTypeArguments()[0];
        return modelClass;
    }

    private Set<String> getFieldNames(Class<?> modelClass) {
        return Arrays.stream(modelClass.getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(Transient.class))
                .filter(field -> !Collection.class.isAssignableFrom(field.getType()))
                .map(Field::getName)
                .collect(Collectors.toSet());

    }


}
