package com.sunwayland.web.albb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

public class osstest {

	@Test
	public void test() throws FileNotFoundException {

		String ACCESS_ID = "kp74z938xM97OCnV";
		String ACCESS_KEY = "WmMdAKOWdjm0dr5UHtssXFmNyjeqqC";

		String UPLOAD_FILE_PATH = "G:\\Q13.png";

		String OSS_ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
		
		String bucketName = "thinglinx-net";
		
		OSSClient  client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
		
		ObjectMetadata  m = new ObjectMetadata();
		m.setContentType("image/jpeg");
		 
	    FileInputStream inputStream = new FileInputStream( UPLOAD_FILE_PATH);
		
		PutObjectResult result = client.putObject(bucketName, "gaefae/gaefpng"+new Date().getSeconds(), inputStream, m );
		
		 
		 

	}
}
