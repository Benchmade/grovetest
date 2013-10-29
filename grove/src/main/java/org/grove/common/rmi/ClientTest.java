package org.grove.common.rmi;

import java.rmi.Naming;

public class ClientTest {
	public static void main(String[] args)throws Exception{
		HashSetWrapper set = (HashSetWrapper)Naming.lookup("rmi://127.0.0.1:9000/set");
		
		System.out.println(set.size());
		
		set.add("xiaolin");
		
		System.out.println(set.size());
	}
}
