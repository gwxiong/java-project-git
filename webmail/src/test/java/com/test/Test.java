package com.test;

import java.security.NoSuchAlgorithmException;

import com.webmail.util.EncryptUtil;
import com.webmail.util.MailUtil;

public class Test {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(MailUtil.sendMail("qq@gwxiong.com", "1"));
	}

}
