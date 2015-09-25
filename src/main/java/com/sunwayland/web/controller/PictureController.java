package com.sunwayland.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.oss.OSSClient;
import com.mysql.fabric.xmlrpc.Client;
import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.utils.FileOperateUtil;
import com.sunwayland.core.utils.Utils;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.SystemUrl;
import com.sunwayland.web.albb.ThinglinxOss;
import com.sunwayland.web.vo.Global;
import com.sunwayland.web.vo.PicPath;


@Controller
@ResponseBody
@RequestMapping("picture")
public class PictureController extends GenericAction {
	
	@Autowired
	private ThinglinxOss  ossClient ;
	
	@Autowired
	private  PicPath picPath ;
	 

   // ==================== 上传 system 图片 ==============================
	@RequestMapping( value="/system" , method=  RequestMethod.POST   , params={"system_id"})
	public  Object  uplaodSystemPicture(@ModelAttribute( Global.session_key_user) User user,
			  							HttpServletRequest request ) throws Exception{
		
		 // 检查是否有上传的权限; 
		 
		
		String system_id = request.getParameter("system_id");
		
		if(null == system_id) return null ; 
		
		// 如果有 pic_url 删除源文件; 
	    Map  system = (Map) rest.https.get(user, SystemUrl.select , UrlParams.get().system_id(system_id),  null).get("ret");
		
	    String  pic_orgin_url  = (String) system.get("pic_url");
	    
	    if( null != pic_orgin_url){  
	    	ossClient.delFile(pic_orgin_url);
	    }
		 
	    
	    
	    String fileUrl =  ossClient.upLoadFile(request, user.getAccount_id(), system_id);
	    
	    
	    
		 HashMap  sys = new HashMap();
		 sys.put("system_id", system_id);
		 sys.put("pic_url", fileUrl );
		 
		 rest.https.put(user, SystemUrl.update, sys , UrlParams.get().system_id(system_id) , null);
		 
		return  RESP( fileUrl ) ;
	}
	
	
}
