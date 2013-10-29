package org.grove.common.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * 测试文件复制的速度,   最终得到使用channel,使用  ByteBuffer最快.
 * @Author : xiaolin
 * @Date : 2012-12-7 下午4:10:27
 */
public class TestFileCopyByChannel {

	/**
	 * 使用channel的transferFrom进行复制,速度为 35443
	 * Date : 2012-12-7
	 * @throws Exception
	 */
	@Test
	public void copyChannel() throws Exception {

		File file = new File("d:/soft/myeclipse-10.0-offline-installer-windows.exe");
		File taget = new File("d:/myeclipse.exe");
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(taget);

		FileChannel fc = fis.getChannel();
		FileChannel fw = fos.getChannel();

		long st = System.currentTimeMillis();

		fw.transferFrom(fc, 0, fc.size());

		long et = System.currentTimeMillis();

		System.out.println(et - st);

		fw.close();
		fc.close();
		fos.close();
		fis.close();

	}

	/**
	 * 使用stream复制,设置缓存区为20m,复制时间为34444
	 * Date : 2012-12-7
	 * @throws Exception
	 */
	@Test
	public void copyStream() throws Exception {
		File file = new File("d:/soft/myeclipse-10.0-offline-installer-windows.exe");
		File taget = new File("d:/myeclipse.exe");
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(taget);
		byte[] buff = new byte[1024 * 20];
		int len = 0;
		long st = System.currentTimeMillis();
		while ((len = fis.read(buff)) != -1) {
			fos.write(buff, 0, len);
		}
		long et = System.currentTimeMillis();

		System.out.println(et - st);
		fos.close();
		fis.close();

	}

	/**
	 * 自己使用bytebuffer复制,同样使用20m的buffer. 时间为28273
	 * Date : 2012-12-7
	 * @throws Exception
	 */
	@Test
	public void copyChannel2() throws Exception {

		ByteBuffer bb = ByteBuffer.allocate(1024 * 20);

		File file = new File("E:/AliDrive/tmall/tmallsearch_20130710.rar");
		File taget = new File("d:/tmallsearch_20130710.rar");
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(taget);

		FileChannel fc = fis.getChannel();
		FileChannel fw = fos.getChannel();

		long st = System.currentTimeMillis();

		while (fc.read(bb) != -1) {
			bb.flip();
			fw.write(bb);
			bb.clear();
		}

		long et = System.currentTimeMillis();

		System.out.println(et - st);

		fw.close();
		fc.close();
		fos.close();
		fis.close();

	}

}
