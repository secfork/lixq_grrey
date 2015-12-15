package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.sunwayland.core.utils.SimpleMail;
import com.sunwayland.core.vo.MailAuthen;
import com.sunwayland.core.vo.MailInfo;

public class email {
	
	@Test
	public void readEmal () throws Exception{
		InputStream in = ClassLoader.getSystemResourceAsStream( "email/account_regist_email.html"  );
    	
		List readLines = IOUtils.readLines(in);
		
//		for( Object o : readLines){
//				System.out.println( o );
//		}
		System.out.println(  String.format("gaefae %s", "123"));
		
	}
	
	@Test
	public void test() throws MessagingException{
		   MailInfo mailInfo = new MailInfo();  
	        mailInfo.setMailServerHost("smtp.qq.com");  
	        mailInfo.setMailServerPort("25");  
	        mailInfo.setValidate(true);   
	        mailInfo.setUsername("1987116566");  
	        mailInfo.setPassword("li123456");// 您的邮箱密码   
	        mailInfo.setFromAddress("1987116566@qq.com");   
	        
	        
	        mailInfo.setToAddress("grreygrrey@163.com");   
	        mailInfo.setSubject("继续完成您的ThingLinx帐号注册");  
	        
	        // 这个类主要来发送邮件  
	        //mailInfo.setContent("设置邮箱内容");  
	        //SimpleMail.sendTextMail(mailInfo);// 发送文体格式  
	        StringBuffer demo = new StringBuffer();  
	        demo.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")  
	        .append("<html>")  
	        .append("<head>")  
	        .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")  
	        .append("<title>测试邮件</title>")  
	        .append("<style type=\"text/css\">")  
	        .append(".test{font-family:\"Microsoft Yahei\";font-size: 18px;color: red;}")  
	        .append("</style>")  
	        .append("</head>")  
	        .append("<body>")  
	        .append("<span class=\"test\">大家好，这里是测试Demo</span>")  
	        .append("</body>")  
	        .append("</html>");  
	        mailInfo.setContent(demo.toString());  
	        SimpleMail.sendHtmlMail(mailInfo);// 发送html格式
		
		
	}
	
	
	
	@Test
	public void test1 () throws Exception{
		
		JavaMailSenderImpl  sender = new  JavaMailSenderImpl();
//		<property name="host" value="smtp.qq.com" />
//		<property name="port" value="25" />
//		<property name="username" value="1987116566" />
//		<property name="password" value="li123456" />
//		<property name="javaMailProperties">
//		<props>
//			<prop key="mail.transport.protocol">smtp</prop>
//			<prop key="mail.smtp.auth">true</prop>
//			<prop key="mail.smtp.starttls.enable">true</prop>
//			<prop key="mail.debug">true</prop>
//		</props>
		sender.setHost("smtp.qq.com");
		sender.setPort(25);
		sender.setUsername("1987116566");
		sender.setPassword("li123456");
		
//		sender.setDefaultFileTypeMap(FileTypeM);
		
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.smtp.auth", true);
		javaMailProperties.put("mail.smtp.starttls.enable", true);
		javaMailProperties.put("mail.debug", true);
		
		
		sender.setJavaMailProperties(javaMailProperties);
		
		// text 邮件; 
		SimpleMailMessage  message = new SimpleMailMessage();
		message.setTo("grrey@163.com");
		message.setFrom("1987116566@qq.com");
		message.setText("12222222222");
	
		
		// sender.send(message);
		
		// html 邮件;  
		 Properties p = new Properties();  
	        p.put("mail.smtp.host", "smtp.qq.com");  
	        p.put("mail.smtp.port", "25");  
	        p.put("mail.smtp.auth", "false"); 
	         
	        		
		Session  session = Session.getDefaultInstance( p , new MailAuthen("1987116566","li123456")  );
		
		MimeMessage  htmlmessage = new MimeMessage( session );
		
		
		
		 //MimeMultipart类是一个容器类，包含MimeBodyPart类型的对象 
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();//创建一个包含HTML内容的
		messageBodyPart.setContent("<div style='color:red;'>123</div> ","text/html; charset=utf-8"); 
		
		Multipart  mainpart = new MimeMultipart();
		mainpart.addBodyPart(messageBodyPart);
		
		htmlmessage.setContent(mainpart);
		  
		Address to = new InternetAddress( "grrey@163.com");//创建邮件的接收者地址  
		htmlmessage.setRecipient(Message.RecipientType.TO, to);//设置邮件消息的接收者  
		
		htmlmessage.setSubject("123");
//		htmlmessage.setse
		
		Transport.send(htmlmessage);
		sender.send(htmlmessage);
	
	
		
	 
		
		
	}
	
	
}	
