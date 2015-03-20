package com._2491nomythic.util;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.StandardOpenOption;
public class TextFileManager {
	protected Path path;
	protected Scanner fileScanner;
	private boolean fileScannerIsOpen = false;
	private boolean caught;
	
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
	
	
	public void clear() {
		try {
		Files.write(path, new byte[0]);
		}
		catch (IOException e) {
			System.out.println("Couldn't clear file " + path.toString() + ", " + e.getLocalizedMessage());
		}
	}
	
	public void append(ArrayList<String> apendee) {
		try {
		Files.write(path, apendee, StandardOpenOption.APPEND);
		}
		catch (IOException e) {
			System.out.println("Couldn't append to file " + path.toString() + ", " + e.getLocalizedMessage());
			caught = true;
		}
	}
	
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
