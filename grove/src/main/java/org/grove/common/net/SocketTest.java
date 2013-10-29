package org.grove.common.net;

import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

//作用和telnet作用一样
public class SocketTest {

	public static void main(String[] args)throws Exception {
		
		Socket s = new Socket("127.0.0.1", 9000);
		
		InputStream is = s.getInputStream();
		
		Scanner sc = new Scanner(is);
		
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			System.out.println(line);
		}
		
		s.close();
	}
	
}
