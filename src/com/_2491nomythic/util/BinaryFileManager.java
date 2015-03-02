package com._2491nomythic.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BinaryFileManager {
	protected Path path;
	
	BinaryFileManager(String fileName) {
		path = Paths.get(fileName);
	}
	
	/**
	 * Read data from the file and return it as a byte array
	 * @return the data read from the file
	 */
	byte[] read() {
		try {
			return Files.readAllBytes(path);
		}
		catch (IOException e) {
			System.out.println("Couldn't read file " + path.toString() + ", " + e.getLocalizedMessage());
			return new byte[0];
		}
	}
	
	/**
	 * Append data to the file
	 * @param bytes data to append to the file
	 */
	void append(byte[] bytes) {
		try {
			Files.write(path, bytes, StandardOpenOption.APPEND);
		}
		catch (IOException e) {
			System.out.println("Couldn't append file " + path.toString() + ", " + e.getLocalizedMessage());
		}
	}
	
	void append(int[] ints) {
		append(intToByte(ints));
	}
	
	void append(float[] floats) {
		append(floatToByte(floats));
	}
	
	void append(double[] doubles) {
		append(doubleToByte(doubles));
	}
	
	void append(int anInt) {
		append(intToByte(new int[] {anInt}));
	}
	
	void append(float aFloat) {
		append(floatToByte(new float[] {aFloat}));
	}
	
	void append(double aDouble) {
		append(doubleToByte(new double[] {aDouble}));
	}
	
	
	/**
	 * Overwrite the file with new data
	 * @param bytes new data to write to the file
	 */
	void write(byte[] bytes) {
		try {
			Files.write(path, bytes);
		}
		catch (IOException e) {
			System.out.println("Couldn't write file " + path.toString() + ", " + e.getLocalizedMessage());
		}
	}
	
	void write(int[] ints) {
		write(intToByte(ints));
	}
	
	void write(float[] floats) {
		write(floatToByte(floats));
	}
	
	void write(double[] doubles) {
		write(doubleToByte(doubles));
	}
	
	void write(int anInt) {
		write(intToByte(new int[] {anInt}));
	}
	
	void write(float aFloat) {
		write(floatToByte(new float[] {aFloat}));
	}
	
	void write(double aDouble) {
		write(doubleToByte(new double[] {aDouble}));
	}
	
	protected byte[] intToByte(int[] ints) {
		IntBuffer intBuffer = IntBuffer.wrap(ints);
		ByteBuffer bytes = ByteBuffer.allocate(ints.length * 4);
		bytes.asIntBuffer().put(intBuffer);
		return bytes.array();
	}
	
	protected byte[] floatToByte(float[] floats) {
		FloatBuffer floatBuffer = FloatBuffer.wrap(floats);
		ByteBuffer bytes = ByteBuffer.allocate(floats.length * 4);
		bytes.asFloatBuffer().put(floatBuffer);
		return bytes.array();
	}
	
	protected byte[] doubleToByte(double[] doubles) {
		DoubleBuffer doubleBuffer = DoubleBuffer.wrap(doubles);
		ByteBuffer bytes = ByteBuffer.allocate(doubles.length * 8);
		bytes.asDoubleBuffer().put(doubleBuffer);
		return bytes.array();
	}
}
