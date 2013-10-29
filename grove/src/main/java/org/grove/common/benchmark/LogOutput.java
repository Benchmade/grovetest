package org.grove.common.benchmark;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.lucene.store.DataOutput;

public class LogOutput extends DataOutput{

	ByteBuffer buf = ByteBuffer.allocate(2048);
	
	@Override
	public void writeByte(byte b) throws IOException {
		buf.put(b);
	}
	
	@Override
	public void writeBytes(byte[] b, int offset, int length) throws IOException {
		
	}
	
	public byte[] getData(){
		buf.flip();
		byte[] data = new byte[buf.limit()];
		buf.get(data);
		return data;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
