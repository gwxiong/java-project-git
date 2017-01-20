package com.webmail.util;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.webmail.config.GlobalPropertie;

public class MailUtil {
	private final static String SmtpHost =  GlobalPropertie.get("smtp");
	
	public static String sendMail(String sender,String password){
		String result = "发送成功";
		Properties prop = new Properties();
		prop.setProperty("mail.host", SmtpHost);
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		String name = sender.substring(0,sender.indexOf("@"));
		//使用JavaMail发送邮件的5个步骤
		//1、创建session
		Session session = Session.getInstance(prop);
		//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(false);
		try {
			//2、通过session得到transport对象
			Transport ts = session.getTransport();
			//3、连上邮件服务器
			ts.connect(SmtpHost, name, password);
			//4、创建邮件
			Message message = createSimpleMail(result,session,sender);
			//5、发送邮件
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (Exception e) {
			result = "发送失败";
		}
		return result;
	}
	
	/**
	 * 创建邮件
	 * @param session
	 * @param sender	发送人
	 * @return
	 */
	public static MimeMessage createSimpleMail(String result,Session session,String sender){
		MimeMessage message = new MimeMessage(session);
		//设置邮件的基本信息
		try {
			//发件人
			message.setFrom(new InternetAddress(sender));
			//收件人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress("ww@gwxiong.com"));
			//邮件标题
			message.setSubject("只包含文本的简单邮件");
			//创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
			MimeBodyPart text = new MimeBodyPart();
			text.setContent("使用JavaMail创建的带附件的邮件", "text/html;charset=UTF-8");
			//创建邮件附件
			MimeBodyPart attach = new MimeBodyPart();
			DataHandler dh = new DataHandler(new FileDataSource("F:/test_mail/1.jpg"));
			attach.setDataHandler(dh);
			attach.setFileName(dh.getName());
			//创建容器描述数据关系
			MimeMultipart mp = new MimeMultipart();
			mp.addBodyPart(text);
			mp.addBodyPart(attach);
			mp.setSubType("mixed");

			message.setContent(mp);
			message.saveChanges();
			//将创建的Email写入到磁盘存储
			message.writeTo(new FileOutputStream("F:/test_mail/attachMail.eml"));
		} catch (Exception e) {
			result = "发送失败";
		}
		return message;
	}
}
