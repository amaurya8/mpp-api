package com.aisa.mpp.api.mppapi.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class TextUtils {

    // Simple tokenization and normalization
    public static List<String> tokenizeAndStem(String text) {
        String lowerCaseText = text.toLowerCase();
        String[] tokens = lowerCaseText.split("\\W+");
        // Apply stemming (basic example, can be enhanced, in future will use open source library)
        return Arrays.stream(tokens)
                .map(TextUtils::stem)
                .collect(Collectors.toList());
    }

    // Basic stemming function (can be replaced with a proper library like Snowball stemmer - will explore later) , stem meaning origin
    // e.g. running, runs, ran, stem word is run
    private static String stem(String word) {
        // Simple stemmer that removes common suffixes and punctuations, values having less semantics(meaning) values
        return word.replaceAll("(ing|:|;|.|,|ed|s)$", "");
    }
}