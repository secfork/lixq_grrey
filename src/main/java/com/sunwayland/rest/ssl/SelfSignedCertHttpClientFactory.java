package com.sunwayland.rest.ssl;

 
/**
 * 
 * The problem is not in your code but in the certificate.
 *  You need to have your domain name (the one the client is connecting to) 
 *  specified either in SubjectName.CommonName field of the certificate or in 
 *  Subject Alternative Name extension of the certificate. You need to re-create the certificate and
 *   when doing this, check manuals regarding how to specify CommonName 
 *   (you'd need to set it to something like "www.mydomain.com").
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStore.LoadStoreParameter;
import java.security.Provider;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class SelfSignedCertHttpClientFactory   {

	 
	  public CloseableHttpClient getClient( String  keyStorePassword) throws Exception {
		  
		   
   	  InputStream inputStream  = this.getClass().getClassLoader()
	 			  							    .getResourceAsStream("/cert/t.txt");
//											   	 .getResourceAsStream("/cert/kclient.keystore");
			 
//		String keyPath = "C:\\cert\\kclient.keystore"; 
   	  
   	   FileInputStream inputStream1 = new FileInputStream("C:\\kclient.keystore");
	   
//		KeyStore keyStore = KeyStore.getInstance("JKS" ); 
		KeyStore keyStore = KeyStore.getInstance("JKS");  
		    
		keyStore.load(  inputStream1 , keyStorePassword.toCharArray());
		
		
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(keyStore).build();
		 
		   
//	    SSLContext sslContext =   new SSLContextBuilder()
//	    								.loadKeyMaterial(keyStore, "123456".getBytes());
//	    	
	      //  .loadTrustMaterial( keyStore , new TrustSelfSignedStrategy())
	       // .build();

	    SSLConnectionSocketFactory sslConnectionSocketFactory  =  
	    		new SSLConnectionSocketFactory(sslContext);

	    
	  //  CloseableHttpClient build = HttpClientBuilder.create().useSystemProperties().build();
	     
	    // based on HttpClients.createSystem() 
	    CloseableHttpClient client = HttpClientBuilder.create().useSystemProperties()
	    							.setSSLSocketFactory(sslConnectionSocketFactory).build();
	     
	    return   client ; 
	    
	   
	  }

	}