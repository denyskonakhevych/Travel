package com.epam.traveling.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SaltedHasher {
	
	private static final String ALGORITHM = "SHA-256";
	
	public String getSecurePassword(String passwordToHash, String salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	
    public String getSalt()
    {
		try {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[256];
	        sr.nextBytes(salt);
	        return salt.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}        
    	return "salt";
    }
}
