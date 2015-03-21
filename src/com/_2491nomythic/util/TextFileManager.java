package com._2491nomythic.util;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.StandardOpenOption;

/**
 * A manager for text files.
 */
public class TextFileManager {
	protected Path path;
	protected Scanner fileScanner;
	private boolean fileScannerIsOpen = false;
	private boolean caught;
	
	/**
	 * A manager for text files.
	 * @param filename The name of the file.
	 */
	public TextFileManager(String filename) {
		path = Paths.get(filename);
	}
	
	public void openFileScanner() {
		try {
		fileScanner = new Scanner(path);
		}
		
		catch (IOException e) {
			System.out.println("Couldn't open file " + path.toString() + ", " + e.getLocalizedMessage());
			caught = true;
		}
		
		if(caught) {
			
		}
		else {
			 fileScannerIsOpen = true;
		}
		
	}
	
	/**
	 * Reads the next line of the file.
	 * @return
	 */
	public String readLine() {
		if(!fileScannerIsOpen) {
			openFileScanner();
		}
		if(fileScanner.hasNextLine() && fileScannerIsOpen) {
			return fileScanner.nextLine();
			
		}
		else {
			return null;
		}
	}
	
	/**
	 * Clears the contents of the file.
	 */
	public void clear() {
		try {
		Files.write(path, new byte[0]);
		}
		catch (IOException e) {
			System.out.println("Couldn't clear file " + path.toString() + ", " + e.getLocalizedMessage());
		}
	}
	
	/**
	 * Appends data to the file.
	 * @param apendee The data to be appended to the file.
	 */
	public void append(ArrayList<String> apendee) {
		try {
		Files.write(path, apendee, StandardOpenOption.APPEND);
		}
		catch (IOException e) {
			System.out.println("Couldn't append to file " + path.toString() + ", " + e.getLocalizedMessage());
			caught = true;
		}
	}
	
	/**
	 * Appends a line to the file
	 * @param apendingLine The line to append to the file.
	 */
	public void appendLine(String apendingLine){
		ArrayList<String> appendee = new ArrayList<String>();
		appendee.add(apendingLine);
		append(appendee);
	}
	
	public void write(ArrayList<String> lines) {
		try{ Files.write(path, lines);  }
		catch(IOException e) {
			System.out.println("Couldn't write to file " + path.toString() + ", " + e.getLocalizedMessage());
		}
	}
	
	
	public void closeFileScanner() {
		fileScanner.close();
	}

	
}
