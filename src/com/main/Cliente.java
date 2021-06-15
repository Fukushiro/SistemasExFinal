package com.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import com.classes.Criptografia;
import com.interfaces.ICriptografia;


public class Cliente {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		//variaveis normais
		boolean sair = false;
		//variaveis de criptografia
		//
		String con = "//127.0.0.1:1099/Criptografia";
		try {
			
			Criptografia c = new Criptografia();
			
			
			ICriptografia crip = (ICriptografia)Naming.lookup(con);
			System.out.println("Informe a chave a ser utilizada");
			byte[] chave = c.nullPadString(s.nextLine()).getBytes();
			
			String res = crip.descriptografar(chave);
			System.out.println(res);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
