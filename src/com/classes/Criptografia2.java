package com.classes;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Criptografia2 {
	
	private static final String SALT = "sal";
	public String nullPadString(String original) {
        StringBuffer output = new StringBuffer(original);
        int remain = output.length() % 16;
        if (remain != 0) {
            remain = 16 - remain;
            for (int i = 0; i < remain; i++) {
                output.append((char) 0);
            }
        }
        return output.toString();
    }
	
	public String getCriptografar(String strToEncrypt, String SECRET_KEY) {
		 try {
		      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		      IvParameterSpec ivspec = new IvParameterSpec(iv);
		 
		      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
		      SecretKey tmp = factory.generateSecret(spec);
		      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		 
		      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
		      return Base64.getEncoder()
		          .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		    } catch (Exception e) {
		      System.out.println("Error while encrypting: " + e.toString());
		    }
		    return null;
		
	}
	
	public byte[] getDescriptografar(byte[] dados, byte[] chave) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec skeySpec = new SecretKeySpec(chave, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] original = cipher.doFinal(dados);
		return original;
	}
}
