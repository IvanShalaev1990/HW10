package org.phonerider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PhoneRider class that reads data from a text file,
 * checks whether the data matches the format (xxx) xxx-xxxx or xxx-xxx-xxxx
 * and displays it on the screen.
 *
 * @author Shalaiev Ivan.
 * @version 1.0.0 16.09.2023
 */
public class PhoneRider {
    private final String REG_EX = "[(]?(\\p{Digit}{3})[)]?[-\s](\\p{Digit}{3})[-](\\p{Digit}{3})";
    private Pattern pattern = Pattern.compile(REG_EX);
    private Matcher matcher;
    private StringJoiner stringJoiner = new StringJoiner("\n");
    private String filePath;
    private String fileValue;

    /**
     * Constructor with one parameter.
     *
     * @param filePath should represent a path to the text file.
     */
    public PhoneRider(String filePath) {
        this.filePath = filePath;

    }

    /**
     * Method that reads data from, write this data to a string,
     * and prints a valid data on the screen.
     */
    public void phoneNumberPrinter() {
        readerFromFile();
        printPhoneNumber();
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
        fileValue = String.valueOf(stringJoiner);
    }

    /**
     * Utility method that prints a valid data on the screen.
     */
    private void printPhoneNumber() {
        matcher = pattern.matcher(fileValue);
        while (matcher.find()) {
            System.out.println(fileValue.substring(matcher.start(), matcher.end()));
        }
    }
}
