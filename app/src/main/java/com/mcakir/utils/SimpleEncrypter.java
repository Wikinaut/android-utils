package com.mcakir.utils;

import android.annotation.SuppressLint;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class SimpleEncrypter {

	private static final String passKey = "PASSWORD_KEY";
	
	public SimpleEncrypter() {
	}

	@SuppressLint("TrulyRandom")
	public static String encrypt(String value){
		try {
	        DESKeySpec keySpec = new DESKeySpec(passKey.getBytes("UTF8"));
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey key = keyFactory.generateSecret(keySpec);

	        byte[] clearText = value.getBytes("UTF8");
	        // Cipher is not thread safe
	        Cipher cipher = Cipher.getInstance("DES");
	        cipher.init(Cipher.ENCRYPT_MODE, key);

	        return Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);
	    } catch (InvalidKeyException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (InvalidKeySpecException e) {
	        e.printStackTrace();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } catch (BadPaddingException e) {
	        e.printStackTrace();
	    } catch (NoSuchPaddingException e) {
	        e.printStackTrace();
	    } catch (IllegalBlockSizeException e) {
	        e.printStackTrace();
	    }
	    return value;
	}
	
	public static String decrypt(String value){
		try {
	        DESKeySpec keySpec = new DESKeySpec(passKey.getBytes("UTF8"));
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey key = keyFactory.generateSecret(keySpec);

	        byte[] encrypedPwdBytes = Base64.decode(value, Base64.DEFAULT);
	        // cipher is not thread safe
	        Cipher cipher = Cipher.getInstance("DES");
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));

	        return new String(decrypedValueBytes);
	    } catch (InvalidKeyException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (InvalidKeySpecException e) {
	        e.printStackTrace();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } catch (BadPaddingException e) {
	        e.printStackTrace();
	    } catch (NoSuchPaddingException e) {
	        e.printStackTrace();
	    } catch (IllegalBlockSizeException e) {
	        e.printStackTrace();
	    }
	    return value;
	}
}
