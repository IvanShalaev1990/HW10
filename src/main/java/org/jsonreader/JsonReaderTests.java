package org.jsonreader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderTests {
    public static void main(String[] args) {
        JsonReader js = new JsonReader("Fiels/fileJson.txt");
        js.converterAndWriter();
    }
}
