package com.sunwayland.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
