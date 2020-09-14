package com.learn.freeim.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class SendMailUtil {

	private static final Logger LOG = LoggerFactory.getLogger(SendMailUtil.class);

	@Value("${SMTPHOST}")
	private String smtphost;
	@Value("${SMTPUSERNAME}")
	private String smtpuser;
	@Value("${SMTPPASSWORD}")
	private String smtppassword;
	@Value("${SMTPPORT}")
	private String smptport;

	// 两个邮箱之间以分号隔开
	public String sendMail(String title, String mailTo, MimeMultipart part) {
		if ("".equals(mailTo) || mailTo == null) {
			LOG.info("邮件接收人为空");
			return "fail";
		}
		String result = "fail";
		try {
			String isproxy = ""; // CommonUtil.getEmailPropertiesValue("ISPROXY");
			String proxyhost = ""; // CommonUtil.getEmailPropertiesValue("PROXYADDRESS");
			String proxyport = ""; // CommonUtil.getEmailPropertiesValue("PROXYPORT");

			// 设置代理服务器
			Properties props = System.getProperties();
			if (isproxy != null && isproxy.equals("1")) {
				props.setProperty("proxySet", "true");
				props.setProperty("socksProxyHost", proxyhost);
				props.setProperty("socksProxyPort", proxyport);
			}
			props.setProperty("mail.smtp.host", smtphost);
			props.setProperty("mail.smtp.port", smptport);
			props.put("mail.smtp.auth", "true");

			final String username = smtpuser;
			final String password = smtppassword;

			// 使用验证
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(username, password);
				}
			});

			MimeMessage message = new MimeMessage(session);
			Address address = new InternetAddress(username);

			String[] mailTos = mailTo.split(";");// 对输入的多个邮件进行逗号分割
			Address[] toAaddress = new InternetAddress[mailTos.length];
			for (int i = 0; i < mailTos.length; i++) {
				toAaddress[i] = new InternetAddress(mailTos[i]);
			}
			message.setFrom(address);
			message.setRecipients(MimeMessage.RecipientType.TO, toAaddress);
			message.setSubject(title);
			message.setContent(part);
			message.setSentDate(new Date());
			Transport.send(message);
			result = "success";
		} catch (Exception e) {
			LOG.info("邮件发送异常：" + e.getMessage());
			result = "fail";

		}
		return result;
	}

}
