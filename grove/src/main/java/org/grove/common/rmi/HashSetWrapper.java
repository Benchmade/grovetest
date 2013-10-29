package org.grove.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashSet;

public interface HashSetWrapper extends Remote{

	public HashSet<String> set = new HashSet<String>();
	
	public int size()throws RemoteException;
	
	public boolean contains(String value)throws RemoteException;
	
	public void add(String value)throws RemoteException;
	
}
