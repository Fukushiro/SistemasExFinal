package com.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICriptografia extends Remote{
	public String testar() throws RemoteException;
	
	public byte[] criptografar(String msg, byte[] chave) throws RemoteException;
	
	public String descriptografar(byte[] chave) throws RemoteException;
}
