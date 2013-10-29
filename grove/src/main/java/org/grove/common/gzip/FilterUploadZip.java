package org.grove.common.gzip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.zip.GZIPOutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;


public class FilterUploadZip {
	
	
	@Test
	public void test()throws Exception{
		Configuration conf = new Configuration();
		FileSystem hdfs = FileSystem.get(URI.create("hdfs://a639.zymo.weibo.com:9000"), conf);
		OutputStream  outs;
		GZIPOutputStream gzip;
		
		//fis = new FileInputStream("E:/zymo日志/wbv4_1_1/wbv4_1_1_20120321_00002");
		outs = hdfs.create(new Path("/zymo/test/test.gz"));
		FileReader fr = new FileReader("E:/zymo日志/wbv4_1_1/wbv4_1_1_20120321_00002");
		BufferedReader br = new BufferedReader(fr);
		
		
		gzip = new GZIPOutputStream(outs);
		OutputStreamWriter osw = new OutputStreamWriter(gzip);
		BufferedWriter bw = new BufferedWriter(osw);
		
		String tmp ;
		while((tmp=br.readLine())!=null){
			String[] fields = tmp.split("\t");
			//orgn7z112frri	1332750590	中国	广东	广州	2013995882	f	none	929789905	1
			if(!fields[2].trim().endsWith("中国")){
				fields[3] = "";
				fields[4] = "";
				StringBuilder sb = new StringBuilder();
				for(int i=0;i<fields.length-1;i++){
					sb.append(fields[i]).append("\t");
				}
				sb.append(fields[fields.length-1]);
				bw.write(sb.toString());
			}else{
				bw.write(tmp);
			}
			System.out.println(tmp);
			bw.newLine();
		}
		
		bw.close();
		osw.close();
		gzip.close();
		br.close();
		fr.close();
		outs.close();
		fr.close();
	}
	
	
	
	
	
	
	
	
	@Test
	public void gzip()throws Exception{
		
		FileReader fr = new FileReader("E:/zymo日志/wbv4_1_1/wbv4_1_1_20120404_00082");
		BufferedReader br = new BufferedReader(fr);
		
		FileOutputStream fos = new FileOutputStream("d:/gzip.gz");
		
		GZIPOutputStream gzip = new GZIPOutputStream(fos);
		
		OutputStreamWriter osw = new OutputStreamWriter(gzip);
		BufferedWriter bw = new BufferedWriter(osw);
		
		long bytes = 0;
		
		String tmp ;
		while((tmp=br.readLine())!=null){
			bw.write(tmp);
			bytes +=tmp.getBytes().length;
			bw.newLine();
		}
		
		bw.close();
		osw.close();
		gzip.close();
		br.close();
		fr.close();
		fos.close();
		fr.close();
		System.out.println(bytes);
	}

}
