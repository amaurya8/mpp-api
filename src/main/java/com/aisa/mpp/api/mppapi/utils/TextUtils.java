package com.aisa.mpp.api.mppapi.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class TextUtils {

    // Simple tokenization and normalization
    public static List<String> tokenizeAndStem(String text) {
        String lowerCaseText = text.toLowerCase();
        String[] tokens = lowerCaseText.split("\\W+");
        // Apply stemming (basic example, can be enhanced)
        return Arrays.stream(tokens)
                .map(TextUtils::stem)
                .collect(Collectors.toList());
    }

    // Basic stemming function (can be replaced with a proper library like Snowball stemmer)
    private static String stem(String word) {
        // Simple stemmer that removes common suffixes and punctuations
        return word.replaceAll("(ing|:|;|.|,|ed|s)$", "");
    }
}