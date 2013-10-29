package org.xiaolin.bloomfilter;

import java.io.BufferedReader;
import java.io.FileReader;

import org.grove.common.bloomfilter.BloomFilter;
import org.grove.common.bloomfilter.Hash;


public class TestBloomFilter {

	public static void main(String[] args) throws Exception{
		BloomFilter bf = new BloomFilter(5000000, 3, Hash.JENKINS_HASH);
		FileReader fr = new FileReader("C:/Users/xiaolin/Desktop/pp.txt");
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		while ((line = br.readLine()) != null) {
			String[] lineArray = line.split("\t");
			byte[] ba = lineArray[0].getBytes("utf-8");
			if(bf.contains(ba)){
				System.out.println("fuck");
			}
			bf.add(ba);
		}
		
		fr = new FileReader("C:/Users/xiaolin/Desktop/pp.txt");
		br = new BufferedReader(fr);
		while ((line = br.readLine()) != null) {
			String[] lineArray = line.split("\t");
			byte[] ba = lineArray[0].getBytes("utf-8");
			if(!bf.contains(ba)){
				System.out.println("fuck!!!");
			}
		}
		
		br.close();
		fr.close();
		
	}
	
}
