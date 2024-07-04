public class NLP3 {
    // Sample corpus
    private static final String[] corpus = {
        "The quick brown fox jumps over the lazy dog",
        "A fast blue hedgehog runs quickly",
        "The cat is running after the mouse"
    };

    // Rule-based lexicon arrays
    private static final String[] words = {
        "the", "a", "quick", "brown", "fast", "blue", "lazy",
        "fox", "dog", "hedgehog", "cat", "mouse",
        "jumps", "runs", "running", "after", "is", "over", "quickly"
    };
    private static final String[] tags = {
        "DT", "DT", "JJ", "JJ", "JJ", "JJ", "JJ",
        "NN", "NN", "NN", "NN", "NN",
        "VBZ", "VBZ", "VBG", "IN", "VBZ", "IN", "RB"
    };

    // Stochastic tagger probabilities arrays
    private static final String[] stochasticWords = {
        "the", "quick", "brown", "fox", "jumps"
    };
    private static final String[][] stochasticTags = {
        {"DT"}, {"JJ"}, {"JJ"}, {"NN"}, {"VBZ"}
    };
    private static final double[][] stochasticProbabilities = {
        {1.0}, {1.0}, {1.0}, {1.0}, {1.0}
    };

    // Method to tag a word using the rule-based tagger
    public static String ruleBasedTagWord(String word) {
        for (int i = 0; i < words.length; i++) {
            if (word.equalsIgnoreCase(words[i])) {
                return tags[i];
            }
        }
        return "NN"; // Default to NN (noun) if no rule matches
    }

    // Method to tag a word using the stochastic tagger
    public static String stochasticTagWord(String word) {
        for (int i = 0; i < stochasticWords.length; i++) {
            if (word.equalsIgnoreCase(stochasticWords[i])) {
                double random = Math.random();
                double cumulativeProbability = 0.0;
                for (int j = 0; j < stochasticTags[i].length; j++) {
                    cumulativeProbability += stochasticProbabilities[i][j];
                    if (random <= cumulativeProbability) {
                        return stochasticTags[i][j];
                    }
                }
            }
        }
        return "NN"; // Default to NN if no probabilities are known
    }

    // Method to tag a sentence using the rule-based tagger
    public static void ruleBasedTagSentence(String sentence) {
        String[] words = sentence.split(" ");
        for (String word : words) {
            System.out.print(word + "/" + ruleBasedTagWord(word) + " ");
        }
        System.out.println();
    }

    // Method to tag a sentence using the stochastic tagger
    public static void stochasticTagSentence(String sentence) {
        String[] words = sentence.split(" ");
        for (String word : words) {
            System.out.print(word + "/" + stochasticTagWord(word) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Rule-Based Tagger:");
        for (String sentence : corpus) {
            ruleBasedTagSentence(sentence);
        }

        System.out.println("\nStochastic Tagger:");
        for (String sentence : corpus) {
            stochasticTagSentence(sentence);
        }
    }
}
