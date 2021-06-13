package com.classes;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Criptografia {

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

    public byte[] getCriptografar(byte[] dados, byte[] chave) throws NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, NoSuchPaddingException, InvalidAlgorithmParameterException {
    	//KeySpec s = new PBEKeySpec(p)
        SecretKeySpec skeySpec = new SecretKeySpec(chave, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] criptografado = cipher.doFinal(dados);
        return criptografado;
    }

    public byte[] getDescriptografar(byte[] dados, byte[] chave) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec skeySpec = new SecretKeySpec(chave, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] original = cipher.doFinal(dados);
        return original;
    }

    public String fromHex(byte[] hex) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < hex.length; i++) {
            sb.append(Integer.toString((hex[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public byte[] toHex(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
