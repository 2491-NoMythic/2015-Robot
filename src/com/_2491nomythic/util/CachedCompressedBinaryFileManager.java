package com._2491nomythic.util;

/**
 * A manager for binary files that are compressed that uses advanced caching technology for no discernible practical reason.
 */
public class CachedCompressedBinaryFileManager extends CompressedBinaryFileManager {
	
	protected byte[] cache;
	
	public CachedCompressedBinaryFileManager(String fileName) {
		super(fileName);
		cache = super.read();
	}
	
	/**
	 * Get the data currently stored in the cache (which is read from file on the instance's construction)
	 * @return the data currently in the cache
	 */
	public byte[] read() {
		return cache;
	}
	
	/**
	 * Append data to the cache.  This does not change the file in the filesystem
	 * @param bytes data to append to the cache
	 */
	public void append(byte[] bytes) {
		byte[] oldCache = cache;
		cache = new byte[oldCache.length + bytes.length];
		System.arraycopy(oldCache, 0, cache, 0, oldCache.length);
		System.arraycopy(bytes, 0, cache, oldCache.length, bytes.length);
	}
	
	/**
	 * Overwrite the data in the cache with new data.  This does not change the file in the filesystem
	 * @param bytes new data to put in cache
	 */
	public void write(byte[] bytes) {
		cache = bytes;
	}
	
	/**
	 * Write the current cache to the file in the filesystem
	 */
	public void writeCacheToFile() {
		super.write(cache);
	}
	
	/**
	 * Reread the file into the cache.  Use this if something else edits the file while the program is running
	 */
	public void reloadFile() {
		cache = super.read();
	}
}
