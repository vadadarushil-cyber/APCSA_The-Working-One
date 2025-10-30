package piglatin;

import java.util.Scanner;

public class PigLatinTranslator {

    public static Book translate(Book book) {
        Book newBook = new Book();

        // Go through every line in the book
        for (int i = 0; i < book.getLineCount(); i++) {
            String line = book.getLine(i);
            String translated = translate(line);
            newBook.appendLine(translated);
        }

        return newBook;
    }

    public static String translate(String line) {
        System.out.println("  -> translate('" + line + "')");

        // If it's empty, just return empty
        if (line == null || line.trim().equals("")) {
            return "";
        }

        Scanner scan = new Scanner(line);
        String result = "";

        // Split by spaces and translate each word
        while (scan.hasNext()) {
            String word = scan.next();
            String pigWord = translateWord(word);

            if (!result.equals("")) {
                result += " ";
            }

            result += pigWord;
        }

        scan.close();
        return result;
    }

    private static String translateWord(String word) {
        System.out.println("  -> translateWord('" + word + "')");

        if (word.equals("")) return "";

        // If word ends in punctuation like . , ! ?
        String end = "";
        char last = word.charAt(word.length() - 1);
        if (!Character.isLetter(last)) {
            end = String.valueOf(last);
            word = word.substring(0, word.length() - 1);
        }

        if (word.equals("")) return end;

        boolean firstCap = Character.isUpperCase(word.charAt(0));
        String small = word.toLowerCase();

        // Handle hyphenated words like clean-cut
        if (small.contains("-")) {
            // treat hyphen like part of the word but only move letters before first vowel
            int vowelPos = findFirstVowelPos(small);
            if (vowelPos == -1) {
                small = small + "ay";
            } else {
                String start = small.substring(0, vowelPos);
                String rest = small.substring(vowelPos);
                small = rest + start + "ay";
            }
        } else {
            // normal single word
            int vowelPos = findFirstVowelPos(small);
            if (vowelPos == 0) {
                small = small + "ay";
            } else if (vowelPos == -1) {
                small = small + "ay";
            } else {
                String start = small.substring(0, vowelPos);
                String rest = small.substring(vowelPos);
                small = rest + start + "ay";
            }
        }

        // Fix capitalization pattern (so TrAsH -> AsHtray)
        small = copyCaps(word, small, firstCap);

        return small + end;
    }

    // Finds the first vowel position, ignoring hyphens
    private static int findFirstVowelPos(String word) {
        String vowels = "aeiou";
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '-') continue;
            if (vowels.indexOf(c) != -1) return i;
        }
        return -1;
    }

    // Keeps uppercase letters in same positions as original word
    private static String copyCaps(String oldWord, String newWord, boolean firstWasCap) {
        String fixed = "";

        // first letter rule
        if (firstWasCap) {
            fixed += Character.toUpperCase(newWord.charAt(0));
        } else {
            fixed += newWord.charAt(0);
        }

        int limit = oldWord.length() - 2;

        for (int i = 1; i < newWord.length(); i++) {
            char ch = newWord.charAt(i);
            if (i <= limit && i < oldWord.length() && Character.isUpperCase(oldWord.charAt(i))) {
                fixed += Character.toUpperCase(ch);
            } else {
                fixed += ch;
            }
        }

        return fixed;
    }
}


