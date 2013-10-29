package org.grove.common;

import java.math.BigDecimal;
import java.util.Random;

public class DivisionVSStirng {

	public static void main(String[] args) {
		BigDecimal bd = new BigDecimal(5.99);
		double a = bd.doubleValue();
		int b = bd.intValue();
		System.out.println(a);
		System.out.println(b);
		System.out.println(a==b);
		
	}
	
	
	public static void division(){
		Random r = new Random();
		long[] la = new long[1000000];
		for(int i=0;i<1000000;i++){
			la[i] = r.nextLong();
		}
		
		long s = System.currentTimeMillis();
		boolean b ;
		for(long l : la){
			b = l%100==0;
		}
		System.out.println(System.currentTimeMillis()-s);
	}
	
}
