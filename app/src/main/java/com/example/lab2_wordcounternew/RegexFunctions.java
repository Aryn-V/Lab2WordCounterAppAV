package com.example.lab2_wordcounternew;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/// [...](https://www.w3schools.com/java/java_regex.asp)
public class RegexFunctions {
    public static int countSentences(String text) {
        Pattern pattern = Pattern.compile("[.!?]");
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    // Regex for numbers
    public static int countNumbers(String text) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
