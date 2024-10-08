package com.aisa.mpp.api.mppapi.utils;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class SimilarityUtils {

    public static double cosineSimilarity(Map<String, Integer> vec1, Map<String, Integer> vec2) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (String key : vec1.keySet()) {
            dotProduct += vec1.get(key) * vec2.getOrDefault(key, 0);
            normA += Math.pow(vec1.get(key), 2); // normA or magnitude of vec1 is sum of square of values
        }
        for (int value : vec2.values()) {
            normB += Math.pow(value, 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB)); // cosine similarity formula, values or between -1 to +1, +1 more similar
    }

    // converting each token(word) into pairs key and no of occurrence
    public static Map<String, Integer> vectorize(List<String> tokens) {
        Map<String, Integer> vector = new HashMap<>();
        for (String token : tokens) {
            vector.put(token, vector.getOrDefault(token, 0) + 1);
        }
        return vector;
    }
}
