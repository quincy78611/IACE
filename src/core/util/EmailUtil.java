package core.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {

	public static void send(String subject, String content, List<File> attaches, String from, String senderName, String... to) throws MessagingException, IOException {
		Properties props = new Properties();
		props.load(EmailUtil.class.getClassLoader().getResourceAsStream("configs/mail.smtp.properties"));
		javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, null); // 取得與SMTP

		MimeMessage msg = new MimeMessage(session); // 取得一Mime的Message
		msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
		
		// set FROM
		msg.setFrom(new InternetAddress(from, senderName, "UTF-8"));
		
		// set TO
		List<InternetAddress> addressList = new ArrayList<InternetAddress>();
		for (String email : to) {
			addressList.add(new InternetAddress(email));
		}
		InternetAddress[] address = addressList.toArray(new InternetAddress[addressList.size()]);	
		msg.setRecipients(Message.RecipientType.TO, address);
		
		// set Subject
		msg.setSubject(subject, "utf-8");
		
		// set content
		{
			Multipart multipart = new MimeMultipart();

			// set actual message
			{
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(content, "text/html; charset=utf-8");
				multipart.addBodyPart(messageBodyPart);
			}
			
			// add attaches
			if (attaches != null) {
				for (int i = 0; i< attaches.size(); i++) {
					MimeBodyPart messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(attaches.get(i));
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(attaches.get(i).getName());
					multipart.addBodyPart(messageBodyPart);
				}
			}
			
			msg.setContent(multipart);
		}
		
		Transport.send(msg);
	}

	public static List<String> batchSend(String subject, String content, List<File> attaches, String from, String senderName, List<String> to) {
		List<String> failEmails = new ArrayList<String>();
		for (int i=0; i<to.size(); i++) {
			String email = to.get(i);
			try {
				send(subject, content, attaches, from, senderName, email);
			} catch (MessagingException | IOException e1) {
				failEmails.add(email);
			}
			
			if (i%100 == 0) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
			}
		}
		return failEmails;
	}
	
	public static List<String> batchSend(String subject, String content, List<File> attaches, String from, String senderName, Set<String> to) {
		List<String> emailList = new ArrayList<String>();
		emailList.addAll(to);
		return batchSend(subject, content, attaches, from, senderName, emailList);
	}
}
