package org.jsonreader;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * JsonReader class reads data from a text file,
 * creates an object based on this data and writes this object to a JSON file.
 *
 * @author Shalaiev Ivan.
 * @version 1.0.0 16.09.2023
 */
public class JsonReader {
    private List<User> userList = new ArrayList<>();
    private final String REG_EX = "(\\p{Alpha}{3,})[-\s](\\p{Digit}{1,3})";
    private String filePath;
    private String fileValue;

    /**
     * Constructor with one parameter.
     *
     * @param filePath should represent a path to the text file.
     */
    public JsonReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method that reads data from a file creates an
     * object based on this data and writes data in JSON format.
     */
    public void converterAndWriter() {
        userRider();
        listToJson();
        jsonToFile();
    }

    /**
     * Utility method that reads data from a file and creates an
     * object based on this data.
     */
    private void userRider() {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                fileValue = scanner.nextLine();
                if (fileValue.matches(REG_EX)) {
                    userWriter(fileValue);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Utility method that create an object with data from a string.
     */
    private void userWriter(String user) {
        String[] userArray = user.split("\s");
        userList.add(new User(userArray[0], Integer.parseInt(userArray[1])));
    }

    /**
     * Utility method that converts an object to a JSON format string.
     *
     * @return String.
     */
    private String listToJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String result;
        try {
            result = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(userList);
        } catch (
                JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Utility method that writes an object to a file in JSON format.
     */
    private void jsonToFile() {
        try (FileWriter writer = new FileWriter("Fiels/user.json")) {
            writer.write(listToJson());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
