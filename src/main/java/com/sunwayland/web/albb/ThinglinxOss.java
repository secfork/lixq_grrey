package com.sunwayland.web.albb;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.sunwayland.core.utils.Utils;

public class ThinglinxOss {

	
	private OSSClient  client ; 
	
	private String  bocketName ; 
	 
	
	public String upLoadFile(   HttpServletRequest request , String accountid ,  String system_id ) throws IOException{
		
		 
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		 
		for (Entry<String, MultipartFile> entry : entrySet) {
		 
			MultipartFile mFile = entry.getValue(); 
		     String contentType = mFile.getContentType();
		     
		     boolean matches = contentType.matches( ".*(png|jpeg|jpg)$");
		     if(!matches) return null ; 
		     
		     String  sufix = contentType.substring( contentType.lastIndexOf("/")+1 );
				
		        
			 
			String newfilename =  accountid +"/"+ system_id +"="+ Utils.randomStr(8)+"."+ sufix;
   
			 
			ObjectMetadata  meta  = new ObjectMetadata();
			meta.setContentType(contentType);
			
			PutObjectResult result = client.putObject(bocketName, newfilename,  
					mFile.getInputStream(), new ObjectMetadata());
			
		   String eTag = result.getETag();
		   
			
			return  newfilename ; 
			
		}
		return null ;
		
		
		 
		
		
//		
//		//要创建的文件夹名称,在满足Object命名规则的情况下以"/"结尾
//		String objectName = "folder_name/"; 
//		
//		 
//		ObjectMetadata objectMeta = new ObjectMetadata();
//		/*这里的size为0,注意OSS本身没有文件夹的概念,这里创建的文件夹本质上是一个size为0的Object,dataStream仍然可以有数据
//		 */
//		byte[] buffer = new byte[0];
//		ByteArrayInputStream in = new ByteArrayInputStream(buffer);  
//		objectMeta.setContentLength(0);
//		try {
//		    client.putObject(bucketName, objectName);
//		} finally {
//		    in.close();
//		}
//		
//		
//		
//		return null ; 
	}
	
	
	
	
	public void  delFile(  String  pic_orgin_url  ){
			
//		去掉  oss rooturl ;   删除 目录    "25.0/faef.png"  ; 
		// http://thinglinx-net.oss-cn-beijing.aliyuncs.com/25.0/V1rVJ6-ASQ=3htmega8.png
		
		
		
		client.deleteObject( bocketName, pic_orgin_url ); 
		 
	}




	public OSSClient getClient() {
		return client;
	}




	public void setClient(OSSClient client) {
		this.client = client;
	}




	public String getBocketName() {
		return bocketName;
	}




	public void setBocketName(String bocketName) {
		this.bocketName = bocketName;
	}




 
	
	
	
}
