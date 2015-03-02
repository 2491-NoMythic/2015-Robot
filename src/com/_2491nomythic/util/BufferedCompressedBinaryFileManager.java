package com._2491nomythic.util;

public class BufferedCompressedBinaryFileManager extends CompressedBinaryFileManager {
	
	protected byte[] buffer;
	
	public BufferedCompressedBinaryFileManager(String fileName) {
		super(fileName);
		buffer = super.read();
	}
	
	/**
	 * Get the data currently stored in the buffer (which is read from file on the instance's construction)
	 * @return the data currently in the buffer
	 */
	public byte[] read() {
		return buffer;
	}
	
	/**
	 * Append data to the buffer.  This does not change the file in the filesystem
	 * @param bytes data to append to the buffer
	 */
	public void append(byte[] bytes) {
		byte[] oldBuffer = buffer;
		buffer = new byte[oldBuffer.length + bytes.length];
		System.arraycopy(oldBuffer, 0, buffer, 0, oldBuffer.length);
		System.arraycopy(bytes, 0, buffer, oldBuffer.length, bytes.length);
	}
	
	/**
	 * Overwrite the data in the buffer with new data.  This does not change the file in the filesystem
	 * @param bytes new data to put in buffer
	 */
	public void write(byte[] bytes) {
		buffer = bytes;
	}
	
	/**
	 * Write the current buffer to the file in the filesystem
	 */
	public void writeBufferToFile() {
		super.write(buffer);
	}
	
	/**
	 * Reread the file into the buffer.  Use this if something else edits the file while the program is running
	 */
	public void reloadFile() {
		buffer = super.read();
	}
}
