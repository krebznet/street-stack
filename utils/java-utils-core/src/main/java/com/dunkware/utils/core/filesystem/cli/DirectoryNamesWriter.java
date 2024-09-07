package com.dunkware.utils.core.filesystem.cli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DirectoryNamesWriter {
    public static void writeDirectoryNames(String directoryPath, String outputFilePath) {
        File directory = new File(directoryPath);
        if (directory.isDirectory()) {
            try (FileWriter writer = new FileWriter(outputFilePath)) {
                for (File file : directory.listFiles()) {
                    if (file.isDirectory()) {
                        writer.write(file.getName() + "\n");
                    }
                }
                System.out.println("Directory names written to " + outputFilePath);
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.err.println("The specified path is not a directory.");
        }
    }

    public static void main(String[] args) {
        String directoryPath = "/Users/duncankrebs/dunkhub/street-stack/trade/";  // Specify the directory path
        String outputFilePath = "output.txt";        // Specify the output file path
        writeDirectoryNames(directoryPath, outputFilePath);
    }
}