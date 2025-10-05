package com.example.lab2_wordcounternew;

public class WordsCounter {
    public static int countWords(String text) {
        if (text.trim().isEmpty()) return 0;
        String[] words = text.split("[\\s,\\.]+");
        return words.length;
    }
}
