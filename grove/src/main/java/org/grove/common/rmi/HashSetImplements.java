package org.grove.common.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class HashSetImplements extends UnicastRemoteObject implements HashSetWrapper,Serializable{
	protected HashSetImplements() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = -1295815829874428771L;

	
	public int size() throws RemoteException{
		return set.size();
	}

	public boolean contains(String value) throws RemoteException{
		return set.contains(value);
	}

	public void add(String value) throws RemoteException{
		set.add(value);
	}

	
	
}
