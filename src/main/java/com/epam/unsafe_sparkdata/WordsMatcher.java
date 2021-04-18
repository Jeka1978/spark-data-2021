package com.epam.unsafe_sparkdata;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * @author Evgeny Borisov
 */
public class WordsMatcher {
    public static String findAndRemoveMatchingPiecesIfExists(Set<String> options, List<String> pieces) {
        StringBuilder match = new StringBuilder(pieces.remove(0));
        List<String> remainingOptions = options.stream().filter(option -> option.toLowerCase().startsWith(match.toString().toLowerCase())).collect(Collectors.toList());
        if (remainingOptions.isEmpty()) {
            return "";
        }
        while (remainingOptions.size() > 1) {
            match.append(pieces.remove(0));
            remainingOptions.removeIf(option -> !option.toLowerCase().startsWith(match.toString().toLowerCase()));
        }
        while (!remainingOptions.get(0).equalsIgnoreCase(match.toString())) {
            match.append(pieces.remove(0));
        }

        return Introspector.decapitalize(match.toString());

    }

    public static List<String> toWordsByJavaConvention(String naming) {
        return new ArrayList<>(asList(naming.split("(?=\\p{Upper})")));
    }
}
