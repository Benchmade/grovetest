package org.grove.common;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TTT implements Serializable {

	public static void main(String[] args) throws Exception {
		/*System.out.println(Integer.toBinaryString(2));
		System.out.println(Integer.toBinaryString(4));
		System.out.println(Integer.toBinaryString(8));
		System.out.println(Integer.toBinaryString(16));
		System.out.println((int)'a');
		System.out.println((int)'A');
		System.out.println((int)'z');
		System.out.println((int)'Z');*/
		
	}
	
	public static void stringBuffer(){
		long a = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer(1000000);
		for(int i=0;i<1000;i++){
			for(int j=0;j<10000;j++){
				sb.append(j);
			}
		}
		System.out.println(System.currentTimeMillis()-a);
	}
	
	public static void stringBuilder(){
		long a = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<1000;i++){
			for(int j=0;j<10000;j++){
				sb.append(j);
			}
		}
		System.out.println(System.currentTimeMillis()-a);
	}
	
	public static void xxx(){
		
		System.out.println(136&1);
		System.out.println(136&2);
		System.out.println(136&4);
		System.out.println(136&8);
		System.out.println(136&16);
		System.out.println(136&32);
		System.out.println(136&64);
		System.out.println(136&128);
		System.out.println(136&256);
	}
	
	
}