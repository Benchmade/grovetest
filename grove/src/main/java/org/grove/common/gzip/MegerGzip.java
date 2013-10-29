package org.grove.common.gzip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

public class MegerGzip {

	@Test
	public void test() throws Exception {
		File log = new File("E:/zymo日志/wbv4_1_1");
		File out = new File("E:/zymo日志/wbv4_1_2");

		File[] logArray = log.listFiles();

		FileReader fr;
		BufferedReader br;

		BufferedWriter bw;
		GZIPOutputStream gzipos;
		FileOutputStream fos;
		OutputStreamWriter osw;

		int line = 0;

		for (int i = 0; i < logArray.length; i++) {
			fr = new FileReader(logArray[i]);
			br = new BufferedReader(fr);

			fos = new FileOutputStream(out, true);
			gzipos = new GZIPOutputStream(fos);
			osw = new OutputStreamWriter(gzipos);
			bw = new BufferedWriter(osw);
			int k = 0;
			String tmp;
			while ((tmp = br.readLine()) != null) {
				bw.write(tmp);
				bw.newLine();
				k++;
				if (k % 10000 == 0 && out.length() > 66060288) {
					bw.close();
					osw.close();
					gzipos.close();
					fos.close();
					fos = new FileOutputStream(new File("E:/zymo日志/wbv4_1_3"), true);
					gzipos = new GZIPOutputStream(fos);
					osw = new OutputStreamWriter(gzipos);
					bw = new BufferedWriter(osw);
					line++;
				}
			}
			System.out.println(out.length());

			System.out.println(line);

			br.close();
			fr.close();
		}
	}

	@Test
	public void ttt() throws Exception {
		FileInputStream fis = new FileInputStream(new File("e:/novelname.txt"));
		FileOutputStream fos = new FileOutputStream(new File("e:/novel2.zip"));
		ZipOutputStream gos = new ZipOutputStream(fos);
		ZipEntry ze = new ZipEntry("xxx");
		gos.putNextEntry(ze);
		byte[] buffer = new byte[2048];
		long size =0;
		while(fis.read(buffer)!=-1){
			gos.write(buffer);
			size +=2048;
			System.out.println(size);
		}
		gos.close();
		fos.close();
		fis.close();
	}

	
	
}
