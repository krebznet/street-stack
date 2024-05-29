package com.dunkware.utils.core.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class DunkFile {
	
	public static String readFileContents(File file) throws IOException {
		StringBuffer contents = new StringBuffer();
		BufferedReader input = new BufferedReader(new FileReader(file));
		String line = null; // not declared within while loop
		while ((line = input.readLine()) != null) {
			contents.append(line);
			contents.append(System.getProperty("line.separator"));
		}
		input.close();
		return contents.toString();

	}

	public static List<String> readFileLines(File file) throws IOException {
		StringBuffer contents = new StringBuffer();
		BufferedReader input = new BufferedReader(new FileReader(file));
		String line = null; // not declared within while loop
		List<String> rows = new ArrayList<String>();
		while ((line = input.readLine()) != null) {

			rows.add(line);

		}
		input.close();
		return rows;

	}

	public static List<String> readFileLines(String content) throws IOException {
		BufferedReader input = new BufferedReader(new StringReader(content));
		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = input.readLine()) != null) {
			lines.add(line);
		}
		input.close();
		return lines;
	}
	

}
