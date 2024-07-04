/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.one;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class NLP1 {

    // Define maximum number of words that we expect in the corpus
    private static final int MAX_WORDS = 100;

    // Function to tokenize a sentence into words
    public static int tokenize(String sentence, String[] tokens) {
        StringTokenizer tokenizer = new StringTokenizer(sentence);
        int index = 0;
        while (tokenizer.hasMoreTokens()) {
            tokens[index++] = tokenizer.nextToken();
        }
        return index;
    }

    // Function to calculate bigram probabilities
    public static void calculateBigramProbabilities(String[] corpus, int numSentences, Map<String, Integer> unigramCounts, Map<String, Integer> bigramCounts) {
        String[] tokens = new String[MAX_WORDS];

        for (int i = 0; i < numSentences; ++i) {
            int numTokens = tokenize(corpus[i], tokens);
            for (int j = 0; j < numTokens; ++j) {
                unigramCounts.put(tokens[j], unigramCounts.getOrDefault(tokens[j], 0) + 1);
                if (j > 0) {
                    String bigram = tokens[j - 1] + " " + tokens[j];
                    bigramCounts.put(bigram, bigramCounts.getOrDefault(bigram, 0) + 1);
                }
            }
        }
    }

    // Function to calculate the probability of a given sentence
    public static double calculateSentenceProbability(String sentence, Map<String, Integer> unigramCounts, Map<String, Integer> bigramCounts) {
        String[] tokens = new String[MAX_WORDS];
        int numTokens = tokenize(sentence, tokens);
        double probability = 1.0;

        for (int i = 1; i < numTokens; ++i) {
            String bigram = tokens[i - 1] + " " + tokens[i];
            if (bigramCounts.containsKey(bigram)) {
                probability *= (double) bigramCounts.get(bigram) / unigramCounts.get(tokens[i - 1]);
            } else {
                // If a bigram is not found, we can consider it as having a very small probability
                probability *= 1e-6; // Smoothing
            }
        }

        return probability;
    }

    public static void main(String[] args) {
        String[] corpus = {
            "there is a big garden",
            "children play in a garden",
            "they play inside beautiful garden"
        };

        int numSentences = 3;
        String targetSentence = "they play in a big garden";

        Map<String, Integer> unigramCounts = new HashMap<>();
        Map<String, Integer> bigramCounts = new HashMap<>();

        calculateBigramProbabilities(corpus, numSentences, unigramCounts, bigramCounts);
        double sentenceProbability = calculateSentenceProbability(targetSentence, unigramCounts, bigramCounts);

        System.out.println("The probability of the sentence \"" + targetSentence + "\" is: " + sentenceProbability);
    }
}
