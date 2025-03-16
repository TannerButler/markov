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
        return "";
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

    }

    public String randomWord(String key) {
        return "";
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




