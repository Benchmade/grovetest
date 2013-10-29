package org.grove.common.stream;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * 返回结果解析器
 */
public class BufferParser {

	private static final byte STOP = (byte) '\1';

	private int prevoff;
	private int offset;
	private byte[] buffer;
	private String resultEncode;

	public int version = 1;
	public int status;
	public int searchTime;

	public BufferParser(byte[] b, String resultEncode) {
		this.resultEncode = resultEncode;
		if (resultEncode != null) {
			try {
				b = new String(b, resultEncode).getBytes();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		buffer = b;

		if (buffer[0] != 'V') {
			offset = 12;

			if ((buffer[offset] == 'V') && "V2.0".equals(getString())) { // 2.0
				version = 2;
			}
		} else {
			// �汾
			if ("V3.0".equals(getString())) {
				version = 3;

				// ״̬
				String str = getString();

				if (str.startsWith("ERROR")) {
					status = NumberUtil.getInt(str.substring(5).trim());
					if (status == 0) {
						status = 1;
					}
				}

				// ����ʱ��
				searchTime = getInt();
			}
		}
	}

	public boolean isEnd() {
		if (offset >= buffer.length) {
			return true;
		}

		return ((buffer[offset] == '\2') ? true : false);
	}

	/**
     * 向下移n个字段
     */
	public void next(int n) {
		if (n > 3000000) {
			throw new IndexOutOfBoundsException(
					"final public void next(int n), n value more than 3000000, formal error");
		}
		while (n-- > 0) {
			prevoff = offset;

			while ((offset < buffer.length) && (buffer[offset] != STOP)) {
				offset++;
			}

			offset++;
		}
	}

/*	public String getString() {
		next(1);

		if ((offset >= buffer.length) || (offset <= (prevoff + 1))) {
			return "";
		}
		if (resultEncode == null) {
			return GBKHelper.decode(buffer, prevoff, offset - prevoff - 1);
		}
		return new String(buffer, prevoff, offset - prevoff - 1);
	}*/

	
public String getString() {
		
		ByteBuffer bbf = ByteBuffer.allocate(1024);
		prevoff = offset;

		while ((offset < buffer.length) && (buffer[offset] != STOP)) {
			bbf.put(buffer[offset]);
			offset++;
		}

		offset++;
		
		bbf.flip();
		byte[] data1 = new byte[bbf.limit()];
		bbf.get(data1);
		try {
			return new String(data1,"GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new String(data1);
		}
	}
	public int getInt() {
		return NumberUtil.getInt(getString().trim());
	}
}
