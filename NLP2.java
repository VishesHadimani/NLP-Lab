/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.two;

import java.util.HashMap;
import java.util.Map;

public class NLP2 {
    
    public static void main(String[] args) {
        String corpus = "The quick brown fox jumps over the lazy dog. " +
                        "The quick brown fox likes to run in the forest. " +
                        "The lazy dog sleeps all day long.";

        // Step 1: Tokenize the corpus
        String[] tokens = tokenize(corpus);

        // Step 2: Count the bigrams
        Map<String, Integer> bigramCounts = countBigrams(tokens);

        // Step 3: Apply Laplace Smoothing and calculate probabilities
        Map<String, Double> bigramProbabilities = calculateBigramProbabilities(bigramCounts, tokens.length);

        // Print the probabilities
        for (Map.Entry<String, Double> entry : bigramProbabilities.entrySet()) {
            System.out.printf("P(%s) = %.4f%n", entry.getKey(), entry.getValue());
        }
    }

    private static String[] tokenize(String corpus) {
        // Tokenize the corpus
        corpus = corpus.toLowerCase();
        return corpus.split("\\s+");
    }

    private static Map<String, Integer> countBigrams(String[] tokens) {
        Map<String, Integer> bigramCounts = new HashMap<>();
        
        for (int i = 0; i < tokens.length - 1; i++) {
            String bigram = tokens[i] + " " + tokens[i + 1];
            bigramCounts.put(bigram, bigramCounts.getOrDefault(bigram, 0) + 1);
        }
        
        return bigramCounts;
    }

    private static Map<String, Double> calculateBigramProbabilities(Map<String, Integer> bigramCounts, int tokenCount) {
        Map<String, Double> bigramProbabilities = new HashMap<>();
        int vocabularySize = bigramCounts.size();
        
        for (Map.Entry<String, Integer> entry : bigramCounts.entrySet()) {
            String bigram = entry.getKey();
            int count = entry.getValue();
            double probability = (double) (count + 1) / (tokenCount + vocabularySize);
            bigramProbabilities.put(bigram, probability);
        }
        
        return bigramProbabilities;
    }
}
