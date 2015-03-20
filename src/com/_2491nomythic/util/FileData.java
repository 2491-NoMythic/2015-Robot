package com._2491nomythic.util;

/**
 * Stores variables in a file.
 */
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.regex.Pattern;

public class FileData {
	private String path;
	private Hashtable<String, String> fileData = new Hashtable<String, String>();
	private static Pattern pattern = Pattern.compile(":");
	
	/**
	 * Stores variables in a file.
	 * @param file_path The file path of the file.
	 */
	public FileData(String file_path) {
		path = file_path;
		try {
			openAndReadFile();
		}
		catch (IOException anything) {
			System.out.println("Couldn't open and read " + file_path + ".  Error: " + anything.getMessage());
		}
	}
	
	/**
	 * Opens and reads the file.
	 * @throws IOException
	 */
	private void openAndReadFile() throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader textreader = new BufferedReader(fr);
		String line;
		String[] parsedLine;
		while ((line = textreader.readLine()) != null) {
			parsedLine = pattern.split(line);
			if (parsedLine.length == 2) {
				fileData.put(parsedLine[0], parsedLine[1]);
			}
			else {
				System.out.println("Couldn't read line " + line + " in " + path);
			}
		}
		textreader.close();
		fr.close();
	}
	
	/**
	 * Checks whether a certain string exists within the file's data.
	 * @param key The string to check for.
	 * @return A boolean describing whether or not the string is in the file data.
	 */
	public boolean exists(String key) {
		return fileData.containsKey(key);
	}
	
	/**
	 * Checks what value the specified key is mapped to.
	 * @param key The key to check for.
	 * @return The value the key is mapped to in the file data.
	 */
	public String get(String key) {
		if (fileData.containsKey(key)) {
			return fileData.get(key);
		}
		else {
			System.out.println("Couldn't find " + key);
			return "";
		}
	}
	
	/**
	 * Checks what value the specified key is mapped to, with a backup value to return if the key does not exist.
	 * @param key The key to find the value for.
	 * @param defaultValue Returned if the key does not exist in the file data.
	 * @return The value of the key if it exists and the defaultValue if it does not.
	 */
	public String getWithDefault(String key, String defaultValue) {
		if (exists(key)) {
			return fileData.get(key);
		}
		else {
			return defaultValue;
		}
	}
	
	/**
	 * Stores a new key and value in the file data.
	 * @param keyInput The key to store.
	 * @param valueInput The value to map the key to.
	 */
	public void set(String keyInput, String valueInput) {
		fileData.put(keyInput, valueInput);
		try {
			writeToFile();
		}
		catch (IOException anything) {
			System.out.println("Couldn't write to file " + path + ".  Error: " + anything.getMessage());
		}
	}
	
	/**
	 * Saves the new data to the file.
	 * @throws IOException
	 */
	private void writeToFile() throws IOException {
		FileWriter write = new FileWriter(path);
		PrintWriter print_line = new PrintWriter(write);
		
		Enumeration<String> fd_key_Enum = fileData.keys();
		Enumeration<String> fd_value_Enum = fileData.elements();
		while (fd_key_Enum.hasMoreElements() && fd_value_Enum.hasMoreElements()) {
			print_line.printf("%s" + "%n", fd_key_Enum.nextElement() + ":" + fd_value_Enum.nextElement());
		}
		
		print_line.close();
		write.close();
	}
	
	/**
	 * Stores a new key and value in the file data.
	 * @param key The key to store.
	 * @param valueInput The value to map the key to as a double.
	 */
	public void set(String key, double valueInput) {
		set(key, Double.toString(valueInput));
	}
	
	/**
	 * Checks what value the specified key is mapped to as a double.
	 * @param key The key to find the value for.
	 * @return The value of the key as a double.
	 */
	public double getDouble(String key) {
		return Double.parseDouble(get(key));
	}
	
	/**
	 * Checks what value the specified key is mapped to as a double with a backup value to return if the key does not exist.
	 * @param key The key to find the value for.
	 * @param defaultValue Returned if the key does not exist in the file data.
	 * @return The value of the key as a double if it exists and the defaultValue if it does not.
	 */
	public double getDoubleWithDefault(String key, double defaultValue) {
		return Double.parseDouble(getWithDefault(key, Double.toString(defaultValue)));
	}
	
}
