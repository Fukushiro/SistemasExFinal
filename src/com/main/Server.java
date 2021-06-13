package com.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.Cipher;

import com.classes.Criptografia;
import com.classes.Criptografia2;
import com.classes.CriptografiaClass;

public class Server {
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		Criptografia c = new Criptografia();
		Criptografia2 c2 = new Criptografia2();
		int porta = 1099;
		
		
		try {
			System.out.println(Cipher.getMaxAllowedKeyLength("AES"));
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			LocateRegistry.createRegistry(porta);
			String nome = "Criptografia";
			System.out.println("Informe a mensagem a ser criptografada");
			String mensagem = s.nextLine();
			System.out.println("Informe a chave a ser usada(minimo de 16 valores)");
			String str = s.nextLine();
			
			String ret = c2.getCriptografar(mensagem, str);
			
			System.out.println(ret);
			
			byte[] b = str.getBytes();
			
			Remote remote =  new CriptografiaClass(mensagem, b);
			Naming.rebind(nome, remote);
			
			
			System.out.println("Servidor rodando");
		} catch (RemoteException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//22222222222222222222222222222222
		}
		
	}
}
