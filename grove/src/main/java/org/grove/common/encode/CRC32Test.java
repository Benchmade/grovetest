package org.grove.common.encode;

import java.util.zip.CRC32;

import org.junit.Test;

public class CRC32Test {

	
	public void simple(){
		
		CRC32 crc = new CRC32();
		String str = "xiaolin";
		crc.update(str.getBytes());
		long value = crc.getValue();
		System.out.println(value);
		System.out.println(Long.toHexString(value));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
