package org.phonerider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhoneRiderTests {
    public static void main(String[] args) {
        PhoneRider ph = new PhoneRider("Fiels/file.txt");
        ph.phoneNumberPrinter();
    }
}
