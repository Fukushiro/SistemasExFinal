package com.classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.interfaces.ICriptografia;

public class CriptografiaClass extends UnicastRemoteObject implements ICriptografia {
	private String mensagem = "Inicial";
	Criptografia c = new Criptografia();
	
	
	public CriptografiaClass(String mensagem, byte[] chave) throws RemoteException {
		super();
		byte[] res = criptografar(mensagem, chave);
		String texto = c.fromHex(res); 
		this.mensagem = texto;
	}

	public CriptografiaClass() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String testar() {
		return "Teste";
		
	}

	@Override
	public byte[] criptografar(String msg, byte[] chave) throws RemoteException {
	
		byte[] bt = chave;//{0x12,0x15,0x54,0x12,0x01,0x18,0x34,0x78,0x12,0x15,0x54,0x12,0x01,0x18,0x34,0x78};
		
		byte[] ret = null;
		try {
			String msgN = c.nullPadString(msg);
			
			ret = c.getCriptografar(msgN.getBytes(), bt);
			
			String stringParaSerArmazenada = c.fromHex(ret);
			this.mensagem = stringParaSerArmazenada;
			return ret;
		} catch (InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException
				| NoSuchPaddingException | InvalidAlgorithmParameterException e) {
			System.out.println("Erro");
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public String descriptografar(byte[] chave) throws RemoteException {
		
		byte[] chaveAtual = chave;//{0x12,0x15,0x54,0x12,0x01,0x18,0x34,0x78,0x12,0x15,0x54,0x12,0x01,0x18,0x34,0x78};
		
		byte[] bytesDaMensagem = c.toHex(this.mensagem);
		
		try {
			byte[] mensagemDescriptografadaHex = c.getDescriptografar(bytesDaMensagem, chaveAtual);
			String retorno = new String(mensagemDescriptografadaHex).trim();
			
			return retorno;
		} catch (InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException
				| NoSuchPaddingException | InvalidAlgorithmParameterException e) {
			System.out.println("ERRo");
			e.printStackTrace();
		}
		
		return "teste";
		
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
	

}
