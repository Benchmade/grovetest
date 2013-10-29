package org.xiaolin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class TestXL {
	public static void main(String[] args) throws Exception{
		FileReader fr = new FileReader("C:/Documents and Settings/w/桌面/novel2.txt");
		BufferedReader br = new BufferedReader(fr);
		
		
		FileWriter fw = new FileWriter("C:/Documents and Settings/w/桌面/novel3.txt"); 
		BufferedWriter bw = new BufferedWriter(fw);
		
		String line ;
		int lineNum = 0;
		while((line=br.readLine())!=null){
			if(lineNum>4){
				bw.write(line);
				bw.newLine();
			}
			lineNum++;
			if(lineNum%100000==0){
				System.out.println(lineNum);
			}
			if(!line.startsWith("0000")){
				System.out.println(line);
			}
		}
		
		bw.close();
		fw.close();
		br.close();
		fr.close();
	}
}
