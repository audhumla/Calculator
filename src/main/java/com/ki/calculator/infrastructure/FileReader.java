package com.ki.calculator.infrastructure;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<String> readFile(File file) {
        List<String> list = new ArrayList<>();
        try {
            list.addAll(Files.readAllLines(file.toPath()));
        } catch (IOException e) {
            // nothing to do
        }
        return list;
    }
}
