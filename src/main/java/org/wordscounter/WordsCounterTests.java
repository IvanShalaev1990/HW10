package org.wordscounter;

public class WordsCounterTests {
    public static void main(String[] args) {
        WordsCounter wordsCounter = new WordsCounter("Fiels/words.txt");
        wordsCounter.printWords();
    }
}
