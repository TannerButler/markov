package com.daclink;

import java.util.ArrayList;
import java.util.HashMap;

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

    public String addFromFile(String fileName) {

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

    }

    private boolean endsWithPPunctuation(String word) {}





}




