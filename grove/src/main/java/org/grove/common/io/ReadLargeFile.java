package org.grove.common.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;

import org.junit.Test;


public class ReadLargeFile {

	/*@Test
	public void testWrite()throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		File f = new File("C:/Users/xiaolin/Desktop/pv.txt");
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String line ;
		List<String> list = new ArrayList<String>(400000);
		while((line=br.readLine())!=null){
			list.add(line);
		}
		
		br.close();
		isr.close();
		fis.close();
		
		File taget = new File("e:/lager.txt");
		FileOutputStream fos = new FileOutputStream(taget);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		
		for(int i=0;i<500;i++){
			System.out.println(i);
			for(String s : list){
				bw.write(sdf.format(new Date()) + " Debug : com.google.test \t" + s);
				bw.newLine();
			}
		}
		
		bw.close();
		osw.close();
		fos.close();
		
	}*/
	
	
	@Test
	public void testRead()throws Exception{
		File taget = new File("e:/lager.txt");
		FileInputStream fis = new FileInputStream(taget);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr,81920);
		String line ;
		long lineNum = 0;
		long s = System.currentTimeMillis();
		while((line=br.readLine())!=null){
			if(line.length()<20){
				System.out.println();
			}
			lineNum ++ ;
			if(lineNum%10000==0){
				System.out.println(lineNum);
			}
		}
		br.close();
		isr.close();
		fis.close();
		System.out.println(System.currentTimeMillis()-s);
	}
	
}
