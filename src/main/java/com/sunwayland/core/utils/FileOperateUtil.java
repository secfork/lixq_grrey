package com.sunwayland.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.lmax.disruptor.util.Util;
import com.sunwayland.web.vo.PicPath;

public class FileOperateUtil {

	// private static　DiskFileItemFactory f = new DiskFileItemFactory();
	// static ServletFileUpload sfu = new ServletFileUpload(factory);
	// List<FileItem> list = sfu.parseRequest(request);

	// DiskFileItemFactory factory = new DiskFileItemFactory(1024*200, new
	// File("F:/temp")); 

	// 2M 的缓存区;
	// private static DiskFileItemFactory factory = new DiskFileItemFactory();
	// public static ServletFileUpload fileupload = new
	// ServletFileUpload(factory);
  
	 

 
	// 上传; 
	/**
	 * 反悔  account_Id/newnamexxxx.xxx ;  
	 */
	public static String  upload(HttpServletRequest request  , String accountid  , PicPath picPath ) 
			throws Exception { 
		
	 
		 String nginxPath ;
		 if(  Utils.isWinOs() ){
			  nginxPath = picPath.getWin_path();
		 }else{
			   nginxPath  = picPath.getLinux_path();
		 }
		  
		
		 String randomStr = Utils.randomStr(8); 
		 
		String system_id = request.getParameter("system_id");
		 
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		
		// web-inf/svg
		String uploadDir =    nginxPath +File.separator +  accountid  ;
				             // 还要 在再加 公司 名称;   还要 有 打散逻辑 ; 
				          //   file.getProjname();

		File dirfile = new File(uploadDir);

		if (!dirfile.exists()) {
			dirfile.mkdirs ();
		}

		String OriginalFilename = null;
		int i = 0;
		 
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		 
		for (Entry<String, MultipartFile> entry : entrySet) {
		 
			MultipartFile mFile = entry.getValue();
			 
			// 文件名; == xxxx.svg
			//OriginalFilename = mFile.getOriginalFilename();  
			//String  sufix = OriginalFilename.substring( OriginalFilename.lastIndexOf(".") );
			//long size = mFile.getSize(); 
			 
		     String contentType = mFile.getContentType();
		     
		     boolean matches = contentType.matches( ".*(png|jpeg|jpg)$");
		     if(!matches) return null ; 
		     
		     String  sufix = contentType.substring( contentType.lastIndexOf("/")+1 );
				
		        
			 
			String newfilename = system_id +"="+ Utils.randomStr(12)+"."+ sufix;
  
			File f = new File( dirfile, newfilename);
 
			BufferedOutputStream 	buffer_out = new BufferedOutputStream(new FileOutputStream(f));
			FileCopyUtils.copy(mFile.getInputStream(), buffer_out);
		 
			// 反悔 新 民命的路径; 
			//  file.setFilepath( dirfile.getPath() +  File.separator + newfilename   );
			return  accountid+"/"+ newfilename;
			
		}
		return null;
	}

	
	
	
	/**
	 * 下载
	 */
	public static void download(HttpServletRequest request,
			HttpServletResponse response, String storeName, String contentType,
			String realName) throws Exception {
		
		/*
		 * application/pdf                  //pdf;
		 * application/vnd.ms-powerpoint   //ppt ;
		 * application/msword              //word  ;
		 * application/zip                 //zip
		 * text/plain                      // text ; 
		 * 
		 */
		
		request.setCharacterEncoding("UTF-8");

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/");
//				+ FileOperateUtil.UPLOADDIR;
		
		String downLoadPath = ctxPath + storeName;

		long fileLength = new File(downLoadPath).length();

		response.setContentType(contentType);
		response.addHeader("Content-disposition", "attachment; filename="
				+ new String(realName.getBytes("utf-8"), "ISO8859-1"));

		response.addHeader("Content-Length", String.valueOf(fileLength));
//		response.setContentType("application/x-msdownload");
		response.setContentType("application/octet-stream"); 
		 
		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}




	 
}
