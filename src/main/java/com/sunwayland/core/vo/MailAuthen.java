package com.sunwayland.core.vo;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthen  extends Authenticator {  
    private String username = null;  
    private String password = null;  
  
    public MailAuthen() {  
    };  
  
    public MailAuthen(String username, String password) {  
        this.username = username;  
        this.password = password;  
    }  
  
    protected PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(username, password);  
    }  
  
}  
