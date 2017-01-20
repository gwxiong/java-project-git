package com.webmail.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

    /**
     * SHA加密
     * @param password	明文
     * @return	返回密文
     */
    public static String encryptSHA(String password){
        MessageDigest md;
        ByteArrayOutputStream bos;
        String result = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(password.getBytes("utf-8"));
            bos = new ByteArrayOutputStream();
            OutputStream encodedStream = MimeUtility.encode(bos, "base64");
            encodedStream.write(digest);
            result = bos.toString("utf-8");
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (MessagingException e) {
        	e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
    }
}
