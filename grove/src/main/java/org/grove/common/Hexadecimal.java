package org.grove.common;

import java.net.InetAddress;
import java.util.Date;

import org.junit.Test;

public class Hexadecimal {

	@Test
	public void test()throws Exception{
		
		/*Date date = new Date();
		
		long time = date.getTime();

		String hex = Long.toHexString(time);
		String sse = Long.toString(time, 32);
		System.out.println("time :" +time);
		System.out.println("hex  :" +hex);
		System.out.println("sse  :" +sse);
		
		
		int i = 19851029;
		System.out.println(Integer.toHexString(i));
		System.out.println(Integer.toString(i,32));
		System.out.println(Integer.valueOf("itpol", 32));*/
		
		String ip = "192.168.100.1";
		
		InetAddress address = InetAddress.getByName(ip);   
        byte[] bytes = address.getAddress(); 
        
        System.out.println(address.getHostAddress());
        
        
        
        //2
        
        String[] ipArray = ip.split("\\.");
        
        //int ip2int = ((Integer.parseInt(ipArray[3]) & 0xFF)  << 24) | ((Integer.parseInt(ipArray[2]) & 0xFF)  << 16)| ((Integer.parseInt(ipArray[1]) & 0xFF)  << 8)| (Integer.parseInt(ipArray[0]) & 0xFF);
        int ip2int = (Integer.parseInt(ipArray[0]) << 24) + (Integer.parseInt(ipArray[1]) << 16) + (Integer.parseInt(ipArray[2])  << 8) + Integer.parseInt(ipArray[3]);
		
		System.out.println(ip2int);
		
		System.out.println(ip2int>>>24);
		System.out.println((ip2int & 0xFF)>>>16);
		System.out.println((ip2int & 0xFF)>>>8);
		System.out.println(ip2int & 0xFF);
		
		
		String s = "http://china.huanqiu.com/roll/2011-11/2221050.html";
		/*s =s.replaceFirst("http://www.", "");
		String[] sss = s.split("/");
		for(String t : sss){
			System.out.println(t);
		}*/
		System.out.println(getSite(s));
		
	}
	private static String getSite(String url){
		url = url.replaceFirst("http://www.|http://", "");
		String[] ua = url.split("/");
		String result = "";
		String tmp = "";
		for(String level : ua){
			if(!tmp.equals("")){
				tmp += "/" + level;
			}else{
				tmp += level;
			}
			result += tmp + " ";
		}
		return result;
	}
}
