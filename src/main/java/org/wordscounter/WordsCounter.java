package org.wordscounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * WordsCounter class that counts the frequency of use of each word
 * and shows them on the screen in descending order.
 *
 * @author Shalaiev Ivan.
 * @version 1.0.0 16.09.2023
 */

public class WordsCounter {
    private StringJoiner stringJoiner = new StringJoiner("\s");
    private String filePath;
    private String textValue;
    private String[] textValues;
    private Map<String, Integer> wordsCounter = new HashMap<>();

    /**
     * Constructor with one parameter.
     *
     * @param filePath should represent a path to the text file.
     */
    public WordsCounter(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Method reads data from a text file,
     * sorts and displays words according to their frequency of use.
     */
    public void printWords() {
        readerFromFile();
        textValues = wordsSplitter();
        addToHashMap();
        wordsCounter = sortByValues();
        for (String name : wordsCounter.keySet()) {
            String key = name;
            Integer value = wordsCounter.get(name);
            System.out.println(key + " " + value);
        }
    }

    /**
     * Utility method that reads data from a file
     * and write this data to a string.
     */
    private void readerFromFile() {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                stringJoiner.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        textValue = String.valueOf(stringJoiner);
    }

    /**
     * Utility method that split a split and put a data to an array.
     *
     * @return String[].
     */
    private String[] wordsSplitter() {
        return textValue.split("\\p{Blank}+|\\p{Punct}+\\p{Blank}+");
    }

    /**
     * Utility method that adds words to a collection according to the key-value principle,
     * where the key is the word and the value is the number of times this word was used.
     */
    private void addToHashMap() {
        for (String value : textValues) {
            if (wordsCounter.containsKey(value)) {
                wordsCounter.replace(value, wordsCounter.get(value) + 1);
            } else {
                wordsCounter.put(value, 1);
            }
        }
    }

    /**
     * Utility method that adds data to a TreeMap and sorts it by value and not by key.
     * If values is equal method sorts by keys.
     *
     * @return Map<>.
     */

    private Map<String, Integer> sortByValues() {

        Map<String, Integer> sortedMap = new TreeMap<>(new Comparator<String>() {
            Map<String, Integer> base = wordsCounter;

            public int compare(String a, String b) {
                Integer value1 = base.get(a);
                Integer value2 = base.get(b);

                // Compare values first
                int valueComparison = value2.compareTo(value1);

                // If values are equal, compare keys
                if (valueComparison == 0) {
                    return a.compareTo(b);
                }

                return valueComparison;
            }
        });
        sortedMap.putAll(wordsCounter);
        return sortedMap;
    }
}
