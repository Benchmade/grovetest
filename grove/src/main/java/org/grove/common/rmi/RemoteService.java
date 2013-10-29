package org.grove.common.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class RemoteService {

	public static void main(String[] args) throws Exception {
		HashSetImplements set = new HashSetImplements();
		LocateRegistry.createRegistry(9000); 
		Naming.rebind("rmi://127.0.0.1:9000/set", set);
		System.out.println("server started...");
	}

}
