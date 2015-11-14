package com.sunwayland.web.vo;

import java.lang.annotation.Documented;


public enum ErrCode {
		
	 // 手机号码格式不正确;
	 phone_pattern_err, 
	 
	 // 手机号码错误
	 phone_err,
	 
	 unknown ,  
     // 图片 验证码 无效; 
     identify_err, 
     
     //  短信验证码无效
     sms_verify_err,   
     // 重复发动
     send_repetition, 
     // 短信发送无效
     sms_error,
     pass_err,
     // 接口调用错误
     rest_err, 
     // account 不存在;
     no_account,
     // email 发送失败;
     send_maill_err, 
     // account_name_
     account_name_exist, 
     //account  admin 用户 无 预留email;
     no_account_email,
     // 页面失效
     page_expire ,
   
     
    
     
    
    
    

}
