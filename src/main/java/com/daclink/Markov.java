package com.daclink;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Markov {

    private static final String BEGINS_SENTENCE = "BEGIN";
    private String prevWord;
    private HashMap<String, ArrayList<String>> words = new HashMap<>();
    private static final String PUNCTUATION_MARKS = "";

    public Markov() {
        words = new HashMap<>();
        prevWord = BEGINS_SENTENCE;
    }

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

    public void addWord(String word) {
        if (endsWithPunctuation(prevWord)) {
            words.get(BEGINS_SENTENCE).add(word);
        }

        words.putIfAbsent(prevWord, new ArrayList<>());
        words.get(prevWord).add(word);

        prevWord = word;
    }

    private static boolean endsWithPunctuation(String word) {
        if (word == null || word.isEmpty()) return false;
        return PUNCTUATION_MARKS.contains(String.valueOf(word.charAt(word.length() - 1)));
    }

    public String randomWord(String key) {
        if (!words.containsKey(key) || words.get(key).isEmpty()) {
            return null;
        }

        List<String> wordList = words.get(key);
        Random random = new Random();
        return wordList.get(random.nextInt(wordList.size()));
    }

    public String toString() {
        return "";
    }

    public HashMap<String, ArrayList<String>> getWords() {
        return words;
    }

    public void addLine(String line) {
        if (line.isEmpty()) return;
        String[] tokens = line.split("\\s+");

        for (String word : tokens) {
            addWord(word);
        }
    }


    private boolean endsWithPPunctuation(String word) {}





}




