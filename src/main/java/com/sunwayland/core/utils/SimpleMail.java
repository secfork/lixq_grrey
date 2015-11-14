package com.sunwayland.core.utils;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.sunwayland.core.vo.MailAuthen;
import com.sunwayland.core.vo.MailInfo;

public class SimpleMail {  
  
 public static  Logger log =  Logger.getLogger(SimpleMail.class);	
	
 public  static  MailInfo getMailInfo(){
	 MailInfo  info = new MailInfo();
	   MailInfo mailInfo = new MailInfo();  
       mailInfo.setMailServerHost("smtp.qiye.163.com");  
       mailInfo.setMailServerPort("25");  
       mailInfo.setValidate(true);   
       mailInfo.setUsername("thinglinx@sunwayland.com.cn");  
       mailInfo.setPassword("thinglinx123456");// 您的邮箱密码   
       
       mailInfo.setFromAddress("thinglinx@sunwayland.com.cn");  
        
       return mailInfo; 
 }
	
 
 public  static void sendAccountRegistEmail ( String to ,  String  registurl) throws Exception{
 	
	   MailInfo mailInfo = getMailInfo();
	   mailInfo.setToAddress(to);
	   mailInfo.setSubject("继续完成您的ThingLinx帐号注册");

	   mailInfo.setContent( crateMailBody("请确认您的邮箱，只差一步，您的注册就成功了！", "请在30分钟内完成", "完成注册", registurl ));  
	      
 	
 	 sendHtmlMail( mailInfo ); 
 	
 }
 
 
 
	   
  public static  void  sendAccountForgetPassEmail ( String to , String   changePassUrl ) throws Exception{
    	
    	MailInfo mail =  getMailInfo();
    	
    	mail.setToAddress(to);
    	mail.setSubject("Thinglinx 找回密码 "); 
    	   		 
    	mail.setContent( crateMailBody("点此按钮重设管理员帐号密码!", "请在30分钟内完成", "重设管理员帐号密码", changePassUrl)); 
    	
    	 sendHtmlMail(mail);
    	
    } 
	   
  
  
  
  	public static void sendSimpleMail(String to , String subject , String title , String note , String message ) throws Exception{
  	  
  		StringBuffer buffer = new StringBuffer() ;
  	   buffer.append( "<!DOCTYPE html><html><head> <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head>")
  		 .append("<body> <div class='email' style='width:700px;padding-bottom: 50px;'> <div class='hd' style='height:40px;padding-left: 30px;'><h2> <mg src='http://thinglinx-net.oss-cn-beijing.aliyuncs.com/thinglinx/logo_2.png'  style='width:150px; height:30px; '></h2> ")
  		 .append("</div><div class='bd' style='padding:10px 55px 0 100px;'><div style='margin-top: 25px;font:bold 16px/40px arial;'>")
  		 
  		 .append( title)
  		 
  		 .append(" <span style='color: #cccccc'>(")
  		 
  		 .append(note )
  		 
  		  .append(")：</span></div><div style='font:bold 18px/36px arial; width: 170px;   height:36px;background-color:#ff3300;text-align: center;margin:25px 0 0 140px; '><span style='color: #fff;  text-decoration: none;'>")
            //  .append( url )  
          .append( message ) 
          .append("</span> </div>")
  		   .append("</div></div></body></html>");
  		  ;
  		  
  		MailInfo mailInfo = getMailInfo();
  		mailInfo.setSubject(subject);
  		mailInfo.setToAddress(to);
  		
  		mailInfo.setContent( buffer.toString());
  		 
  		 sendHtmlMail(mailInfo);
  	}
  
   public static void  sendMail (String to , String subject ,String  title , String note , String buttom ,  String  url ) throws Exception{
	   
	    MailInfo mailInfo = getMailInfo();
 		mailInfo.setSubject(subject);
 		mailInfo.setToAddress(to);
 		
 		mailInfo.setContent(  crateMailBody(title, note, buttom, url)   );
 		
 		sendHtmlMail(mailInfo);
	   
   }
  	
  	
  	
  	
  	
    private  static  String  crateMailBody ( String  title , String note , String buttom ,  String  url ){
    	StringBuffer buffer = new StringBuffer() ;
 	    
// 	   buffer.append( "<!DOCTYPE html><html><head> <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head>")
// 	   		 .append("<body> <div class='email' style='width:700px;padding-bottom: 50px;'> <div class='hd' style='height:40px;padding-left: 30px;'><h2> <mg src='http://thinglinx-net.oss-cn-beijing.aliyuncs.com/thinglinx/logo_2.png'  style='width:150px; height:30px; '></h2> ")
// 	   		 .append("</div><div class='bd' style='padding:10px 55px 0 100px;'><div style='margin-top: 25px;font:bold 16px/40px arial;'>")
// 	   		 .append("请确认您的邮箱，只差一步，您的注册就成功了！ <span style='color: #cccccc'>(请在30分钟内完成)：</span></div>")
// 	   		 .append("<div style='font:bold 18px/36px arial; width: 170px;   height:36px;background-color:#ff3300;text-align: center;margin:25px 0 0 140px; '><a style='color: #fff;  text-decoration: none;' href='")
//              .append( registUrl )
// 	   		 .append("'>完成注册</a> </div> <div style='color: #ccc;margin-top: 40px;font:bold 16px/26px arial;'> 如果亲看不到上方的按钮<br> 可点击下面的链接以完成注册或复制下面的链接到浏览器地址栏中完成注册：<br><a style='color:#3399ff;font-weight: normal;  ' ")
// 	   		 .append("href='")
// 	   		 .append(registUrl + "'>")
// 	   		 .append(registUrl) 
// 	   		 .append("</a></div></div></div></body></html>");
 	   		 
 	   buffer.append( "<!DOCTYPE html><html><head> <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head>")
 	   		 .append("<body> <div class='email' style='width:700px;padding-bottom: 50px;'> <div class='hd' style='height:40px;padding-left: 30px;'><h2> <mg src='http://thinglinx-net.oss-cn-beijing.aliyuncs.com/thinglinx/logo_2.png'  style='width:150px; height:30px; '></h2> ")
 	   		 .append("</div><div class='bd' style='padding:10px 55px 0 100px;'><div style='margin-top: 25px;font:bold 16px/40px arial;'>")
 	   		 
 	   		 .append( title)
 	   		 
 	   		 .append(" <span style='color: #cccccc'>(")
 	   		 
 	   		 .append(note )
 	   		 
 	   		 .append(")：</span></div><div style='font:bold 18px/36px arial; width: 170px;   height:36px;background-color:#ff3300;text-align: center;margin:25px 0 0 140px; '><a style='color: #fff;  text-decoration: none;' href='")
              .append( url ) 
              
              .append("'>") 
              .append( buttom ) 
              .append("</a> </div> <div style='color: #ccc;margin-top: 40px;font:bold 16px/26px arial;'> 如果亲看不到上方的按钮<br> 可点击下面的链接以完成注册或复制下面的链接到浏览器地址栏中完成操作：<br><a style='color:#3399ff;font-weight: normal;  ' href='")
 	   		 .append(url )
 	   		 .append( "'>")
 	   		 .append(url) 
 	   		 .append("</a></div></div></div></body></html>");
    	return  buffer.toString() ; 
    }  
   
   
	   
    // 以文本格式发送邮件  
      private static boolean sendTextMail(MailInfo mailInfo) {       
        //判断是否需要身份认证  
        MailAuthen authenticator = null;  
        Properties properties = mailInfo.getProperties();  
        if (mailInfo.isValidate()) {  
            //如果需要身份认证，则创建一个密码验证器  
            authenticator = new MailAuthen(mailInfo.getUsername(), mailInfo.getPassword());  
        }  
          
        //根据邮件会话属性和密码验证器构造一个发送邮件的session  
        Session sendMailSession = Session.getDefaultInstance(properties, authenticator);  
        try {  
            Message mailMessage = new MimeMessage(sendMailSession);//根据session创建一个邮件消息   
            Address from = new InternetAddress(mailInfo.getFromAddress());//创建邮件发送者地址  
            mailMessage.setFrom(from);//设置邮件消息的发送者  
            Address to = new InternetAddress(mailInfo.getToAddress());//创建邮件的接收者地址  
            mailMessage.setRecipient(Message.RecipientType.TO, to);//设置邮件消息的接收者  
            mailMessage.setSubject(mailInfo.getSubject());//设置邮件消息的主题  
            mailMessage.setSentDate(new Date());//设置邮件消息发送的时间  
            //mailMessage.setText(mailInfo.getContent());//设置邮件消息的主要内容  
              
            //MimeMultipart类是一个容器类，包含MimeBodyPart类型的对象  
            Multipart mainPart = new MimeMultipart();  
            MimeBodyPart messageBodyPart = new MimeBodyPart();//创建一个包含附件内容的MimeBodyPart  
            //设置HTML内容  
            messageBodyPart.setContent(mailInfo.getContent(),"text/html; charset=utf-8");  
            mainPart.addBodyPart(messageBodyPart);  
  
            //存在附件  
            String[] filePaths = mailInfo.getAttachFileNames();  
            if (filePaths != null && filePaths.length > 0) {  
                for(String filePath:filePaths){  
                    messageBodyPart = new MimeBodyPart();  
                    File file = new File(filePath);   
                    if(file.exists()){//附件存在磁盘中  
                        FileDataSource fds = new FileDataSource(file);//得到数据源  
                        messageBodyPart.setDataHandler(new DataHandler(fds));//得到附件本身并至入BodyPart  
                        messageBodyPart.setFileName(file.getName());//得到文件名同样至入BodyPart  
                        mainPart.addBodyPart(messageBodyPart);  
                    }  
                }  
            }  
              
            //将MimeMultipart对象设置为邮件内容     
            mailMessage.setContent(mainPart);  
            Transport.send(mailMessage);//发送邮件  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();    
        }  
        return false;  
    }     
    
    

      
    // 以HTML格式发送邮件   
    public static boolean sendHtmlMail(MailInfo mailInfo) throws MessagingException   {       
        //判断是否需要身份认证  
    	MailAuthen authenticator = null;  
        Properties properties = mailInfo.getProperties();  
        if (mailInfo.isValidate()) {  
            // 如果需要身份认证，则创建一个密码验证器  
            authenticator = new MailAuthen(mailInfo.getUsername(), mailInfo.getPassword());  
        }  
  
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session  
        Session sendMailSession = Session.getDefaultInstance(properties, authenticator);  
        
            Message mailMessage = new MimeMessage(sendMailSession);//根据session创建一个邮件消息   
           
            
            Address from = new InternetAddress(mailInfo.getFromAddress());//创建邮件发送者地址  
            mailMessage.setFrom(from);//设置邮件消息的发送者  
              
            
            Address to = new InternetAddress(mailInfo.getToAddress());//创建邮件的接收者地址  
            mailMessage.setRecipient(Message.RecipientType.TO, to);//设置邮件消息的接收者  
            mailMessage.setSubject(mailInfo.getSubject());//设置邮件消息的主题  
            //mailMessage.setSentDate(new Date());//设置邮件消息发送的时间  
            mailMessage.setDisposition("disposition");
            
            //MimeMultipart类是一个容器类，包含MimeBodyPart类型的对象  
            Multipart mainPart = new MimeMultipart();  
            MimeBodyPart messageBodyPart = new MimeBodyPart();//创建一个包含HTML内容的MimeBodyPart  
            //设置HTML内容  
            messageBodyPart.setContent(mailInfo.getContent(),"text/html; charset=utf-8");  
             
            
            mainPart.addBodyPart(messageBodyPart);  
              
            //存在附件  
//            String[] filePaths = mailInfo.getAttachFileNames();  
//            if (filePaths != null && filePaths.length > 0) {  
//                for(String filePath:filePaths){  
//                    messageBodyPart = new MimeBodyPart();  
//                    File file = new File(filePath);   
//                    if(file.exists()){//附件存在磁盘中  
//                        FileDataSource fds = new FileDataSource(file);//得到数据源  
//                        messageBodyPart.setDataHandler(new DataHandler(fds));//得到附件本身并至入BodyPart  
//                        messageBodyPart.setFileName(file.getName());//得到文件名同样至入BodyPart  
//                        mainPart.addBodyPart(messageBodyPart);  
//                    }  
//                }  
//            }  
              
            //将MimeMultipart对象设置为邮件内容     
            mailMessage.setContent(mainPart);  
            Transport.send(mailMessage);//发送邮件  
            return true;  
          
    }  
  
}  
