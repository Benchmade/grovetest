package org.grove.common.benchmark;

import org.apache.lucene.store.DataInput;

public class LogInput extends DataInput {
	private int pos=0;
	byte[] bytes;

	public LogInput(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public byte readByte() {
		return bytes[pos++];
	}

	// NOTE: AIOOBE not EOF if you read too much
	@Override
	public void readBytes(byte[] b, int offset, int len) {
		System.arraycopy(bytes, pos, b, offset, len);
		pos += len;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
