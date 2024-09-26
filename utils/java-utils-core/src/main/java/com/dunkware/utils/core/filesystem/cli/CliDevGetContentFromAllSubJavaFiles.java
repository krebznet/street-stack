package com.dunkware.utils.core.filesystem.cli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CliDevGetContentFromAllSubJavaFiles {

    private List<String> javaFileContents;

    public CliDevGetContentFromAllSubJavaFiles() {
        this.javaFileContents = new ArrayList<>();
    }

    // Recursively find and process Java files
    public void processDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processDirectory(file); // Recursively process subdirectories
                } else if (file.getName().endsWith(".java")) {
                    appendJavaFile(file.toPath());
                }
            }
        }
    }

    // Append the contents of a .java file to the in-memory list
    public void appendJavaFile(Path filePath) {
        try {
            // Read all lines from the file and join them into a single string
            String content = Files.readString(filePath, StandardCharsets.UTF_8);
            javaFileContents.add(content); // Add the content to the list
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
    }

    // Write all collected content to a single file
    public void writeToFile(String outputFilePath) {
        try (FileWriter writer = new FileWriter(outputFilePath)) {
            for (String content : javaFileContents) {
                writer.write(content);
                writer.write(System.lineSeparator()); // Separate different file contents
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + outputFilePath);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       // if (args.length < 2) {
         //   System.out.println("Usage: JavaFileAggregator <directory> <output-file>");
         //   return;
       // }

        // The directory containing .java files
        String directoryPath = "/Users/duncankrebs/dunkware-bsv/repo/bac/street-cluster/projects/dunkware-xstream-core-bean/src/main/java/com/dunkware/xstream/bean/bean/query";// args[0];
        // The output file to write the collected content
        String outputFilePath = "/tmp/bean.java";//

        CliDevGetContentFromAllSubJavaFiles aggregator = new CliDevGetContentFromAllSubJavaFiles();
        aggregator.processDirectory(new File(directoryPath)); // Process the directory
        aggregator.writeToFile(outputFilePath); // Write the content to the output file

        System.out.println("Java files aggregated into: " + outputFilePath);
    }
}

