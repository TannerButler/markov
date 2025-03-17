/**
 * Title: Markov Word Generator
 * Author: Tanner Butler
 * Date: 3/10/2025
 */

package com.daclink;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Markov class processes a text file and generates random sentences
 * based on a Markov model.
 */
public class Markov {
    private static final String BEGINS_SENTENCE = "__$";
    private static final String PUNCTUATION_MARKS = ".!?$";
    private String prevWord;
    private HashMap<String, ArrayList<String>> words;

    /**
     * Constructor: Initializes HashMap and sets starting word
     */
    public Markov() {
        words = new HashMap<>();
        words.put(BEGINS_SENTENCE, new ArrayList<>());
        prevWord = BEGINS_SENTENCE;
    }

    /**
     * Returns HashMap containing words and their following words
     */
    public HashMap<String, ArrayList<String>> getWords() {
        return words;
    }

    /**
     * Reads file and processes content
     */
    public void addFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                addLine(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + fileName);
        }
    }

    /**
     * Processes line by splitting it into words
     */
    public void addLine(String line) {
        if (line.isEmpty()) return;
        String[] tokens = line.split("\\s+");

        for (String word : tokens) {
            addWord(word);
        }
    }

    /**
     * Adds word to HashMap and links it with previous word
     */
    public void addWord(String word) {
        if (endsWithPunctuation(prevWord)) {
            words.get(BEGINS_SENTENCE).add(word);
        }

        words.putIfAbsent(prevWord, new ArrayList<>());
        words.get(prevWord).add(word);

        prevWord = word;
    }

    /**
     * Generates sentence using the Markov model
     */
    public String getSentence() {
        Random random = new Random();
        if (!words.containsKey(BEGINS_SENTENCE) || words.get(BEGINS_SENTENCE).isEmpty()) {
            return "No sentences available.";
        }

        StringBuilder sentence = new StringBuilder();
        String currentWord = randomWord(BEGINS_SENTENCE);

        while (currentWord != null && !endsWithPunctuation(currentWord)) {
            sentence.append(currentWord).append(" ");
            currentWord = randomWord(currentWord);
        }

        if (currentWord != null) {
            sentence.append(currentWord);
        }

        return sentence.toString();
    }

    /**
     * Selects random word from list of following words
     */
    public String randomWord(String key) {
        if (!words.containsKey(key) || words.get(key).isEmpty()) {
            return null;
        }

        List<String> wordList = words.get(key);
        Random random = new Random();
        return wordList.get(random.nextInt(wordList.size()));
    }

    /**
     * Checks if word ends with a punctuation mark
     */
    private static boolean endsWithPunctuation(String word) {
        if (word == null || word.isEmpty()) return false;
        return PUNCTUATION_MARKS.contains(String.valueOf(word.charAt(word.length() - 1)));
    }

    /**
     * Returns string representation of the Markov model
     */
    @Override
    public String toString() {
        return words.toString();
    }
}
