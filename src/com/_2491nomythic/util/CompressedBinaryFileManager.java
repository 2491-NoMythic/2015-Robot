package com._2491nomythic.util;

import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


public class CompressedBinaryFileManager extends BinaryFileManager {
	
	CompressedBinaryFileManager(String fileName) {
		super(fileName);
	}
	
	/**
	 * Read data from the file, decompress it, and return it as a byte array
	 */
	byte[] read() {
		byte[] readBytes = super.read();
		Inflater decompress = new Inflater();
		if (readBytes.length < 4) {
			System.out.println("File " + path.toString() + " is too small");
			return new byte[0];
		}
		byte[] compressedBytes = new byte[readBytes.length - 4];
		System.arraycopy(readBytes, 4, compressedBytes, 0, compressedBytes.length);
		int length = ByteBuffer.wrap(new byte[] {readBytes[0], readBytes[1], readBytes[2], readBytes[3]}).getInt();
		byte[] uncompressedBytes = new byte[length];
		decompress.setInput(compressedBytes);
		try {
			decompress.inflate(uncompressedBytes);
		}
		catch (DataFormatException e) {
			System.out.println("Couldn't decompress file " + path.toString() + ", " + e.getLocalizedMessage());
			return new byte[0];
		}
		return uncompressedBytes;
	}
	
	/**
	 * Append data to the file.  This requires reading the whole file, appending it, recompressing it, and writing it again, so don't spam it, especially with large files.
	 */
	void append(byte[] bytes) {
		byte[] readBytes = read();
		byte[] bytesToWrite = new byte[readBytes.length + bytes.length];
		System.arraycopy(readBytes, 0, bytesToWrite, 0, readBytes.length);
		System.arraycopy(bytes, 0, bytesToWrite, readBytes.length, bytes.length);
		write(bytesToWrite);
	}
	
	/**
	 * Overwrite the file with new data.
	 */
	void write(byte[] bytes) {
		Deflater compress = new Deflater(9);
		byte[] compressedData = new byte[bytes.length];
		compress.setInput(bytes);
		compress.finish();
		int compressedLength = compress.deflate(compressedData, 0, bytes.length);
		byte[] dataToWrite = new byte[compressedLength + 4];
		System.arraycopy(compressedData, 0, dataToWrite, 4, compressedLength);
		System.arraycopy(intToByte(new int[] {bytes.length}), 0, dataToWrite, 0, 4);
		super.write(dataToWrite);
	}
}
