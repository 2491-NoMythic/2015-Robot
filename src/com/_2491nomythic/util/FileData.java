package com._2491nomythic.util;

import java.io.IOException;
import java.util.Hashtable;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.regex.Pattern;

public class FileData {
	private String path;
	private Hashtable<String, String> fileData = new Hashtable<String, String>();
	private static Pattern pattern = Pattern.compile(":");
	
	public FileData(String file_path) {
		path = file_path;
		try {
		openAndReadFile();
		}
		catch(IOException anything) {
			System.out.println(anything.getMessage());
		}
	}
	
	
	private void openAndReadFile() throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader textreader = new BufferedReader(fr);	
		String line;
		String[] parsedLine;
		while((line = textreader.readLine()) != null) {
			parsedLine = pattern.split(line);
			if(parsedLine.length == 2) {
				fileData.put(parsedLine[0], parsedLine[1]);
			}
			else {
				System.out.println("Couldn't read line " + line + " in " + path);
			}
		}
	
		textreader.close();
	
	}
	
	
}

