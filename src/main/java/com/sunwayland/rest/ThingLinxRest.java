package com.sunwayland.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javassist.bytecode.annotation.StringMemberValue;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.aspectj.ajde.ui.swing.GoToLineThread;
import org.eclipse.core.internal.refresh.PollingMonitor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.sunwayland.core.exception.ExceptionMessage;
import com.sunwayland.rest.basic.HttpRest;
import com.sunwayland.rest.basic.HttpsRest;
import com.sunwayland.web.vo.Global;

/**
 * 
 * RestEntity : com.sunwayland.rest.entityV2 中的 实现类 ; 或者 restentity 就为object :
 * string 可以传 , java就不做验证了!
 * 
 * @author Administrator
 *
 *
 *
 */

@SuppressWarnings("unused")
public class ThingLinxRest {

	Log logger = LogFactory.getLog(ThingLinxRest.class);

	private Gson gson = new Gson();

	public HttpRest http;
	public HttpsRest https;

	public RestSystem System;
	public RestSystemModel SystemModel;
	public RestSystemModelProfile profile;
	public RestDeviceModel deviceModel;

	/**
	 * ip = base_url ;
	 * 
	 * @param IP
	 * @param keyStorePassword
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public ThingLinxRest(String httpsUrl, String httpUrl, String keyStoreFilePath,
			String keyStorePassword) throws Exception {

	 
		// https:// ip:443 ;
		// http : // ip: 3000 ;

		//===============================================================
		
		HttpComponentsClientHttpRequestFactory httpRequestFactory =
				getClieentRequestFactory(keyStoreFilePath, keyStorePassword);
 
		
		ArrayList<ClientHttpRequestInterceptor> list = new ArrayList<ClientHttpRequestInterceptor>();
		List<HttpMessageConverter<?>> list1 = new ArrayList<HttpMessageConverter<?>>();

		list.add(requestInterCeptor);

 		list1.add(responseMapStringConverter);
		
		list1.add(new StringHttpMessageConverter());
		
 
		RestTemplate httpsRest = new RestTemplate(httpRequestFactory);
		RestTemplate httpRest = new RestTemplate();

		// reInitMessageConverter( httpRest);
		// reInitMessageConverter( httpsRest);

		httpsRest.setInterceptors(list);
		httpsRest.setMessageConverters(list1);

		httpRest.setInterceptors(list);
		httpRest.setMessageConverters(list1);

		// StringHttpMessageConverter c = new StringHttpMessageConverter();

		this.http = new HttpRest(httpRest, httpUrl);

		this.https = new HttpsRest(httpsRest, httpsUrl);

		this.System = new RestSystem(httpsRest, httpsUrl);
		this.SystemModel = new RestSystemModel(httpsRest, httpsUrl);
		this.profile = new RestSystemModelProfile(httpsRest, httpsUrl);
		this.deviceModel = new RestDeviceModel(httpsRest, httpsUrl);

	}

	@SuppressWarnings("deprecation")
	private HttpComponentsClientHttpRequestFactory getClieentRequestFactory(
			String keyStoreFilePath,
			String keyStorePassword) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException,
			KeyManagementException {

		// /cert/client.jks ;
		// String path = // File.separator + "cert" + File.separator +
		// "client.jks";

		// InputStream in =
		// Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		// String path = File.separator +"cert" + File.separator +
		// keyStoreFilePath ;
		// InputStream in = ClassLoader.getSystemResourceAsStream( path );

		InputStream in = ClassLoader.getSystemResourceAsStream(keyStoreFilePath);

		char[] ar = keyStorePassword.toCharArray();

		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(in, ar);

		SSLContextBuilder builder = SSLContexts.custom().loadTrustMaterial(keyStore, new TrustSelfSignedStrategy());

		SSLContext sslcontext = builder.build();

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext,
				new StrictHostnameVerifier()
				);
		 
		//===========
		
		PoolingHttpClientConnectionManager  pool_connectMangeer  = 
				new PoolingHttpClientConnectionManager();
		  
		pool_connectMangeer.setMaxTotal(100);
	 	pool_connectMangeer.setDefaultMaxPerRoute(50);
	 	pool_connectMangeer.setMaxPerRoute( new HttpRoute(
	 			new HttpHost("172.18.16.254",443 ,"https")
	 			
	 			), 50);
		 
		
		// rest 超时 ;
		RequestConfig config = RequestConfig.custom().setConnectTimeout(30*1000).build();
		
		

 	   CloseableHttpClient httpclient1 = HttpClientBuilder.create()
 			   		 
 	   					.setConnectionManager( pool_connectMangeer )
 	   					.setSSLSocketFactory(sslsf) 
 	   					.setDefaultRequestConfig(config)
						.build();
 	   
		//===========
						  		 
 	   
 	   
		CloseableHttpClient httpclient2 = HttpClientBuilder.create()
				//  .setConnectionManager(pool_connectMangeer)			
				// .setSslcontext(sslcontext)
				 .setSSLSocketFactory(sslsf)
				 
				 .build();
		 
		
		
		//====================================
		
		PoolingNHttpClientConnectionManager asyncManager = new PoolingNHttpClientConnectionManager(
				  new DefaultConnectingIOReactor(IOReactorConfig.DEFAULT)
				);
		 
		
		CloseableHttpAsyncClient  httpAsyncClient = HttpAsyncClientBuilder.create()
								.setConnectionManager(asyncManager) 
								.setSSLContext(sslcontext)
								.build();
		
		
		
		//====================================
		 
		HttpComponentsClientHttpRequestFactory httpRequestFactory = 
				new HttpComponentsClientHttpRequestFactory( httpclient2 );
		
		HttpComponentsAsyncClientHttpRequestFactory  asyncRequestFactory = 
				new HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient);
	 
		return httpRequestFactory;
		
//		return  asyncRequestFactory;
	}

	private ClientHttpRequestInterceptor requestInterCeptor = new ClientHttpRequestInterceptor() {
		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
 
			logger.info("rest : " + request.getMethod() + " : url : " + request.getURI().toString());
		//	logger.info("rest : "+ request.getMethod() +" : body : " +  new String(body) );
			  
			return execution.execute(request, body);
		}
	};

	
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter responseMapStringConverter = new HttpMessageConverter<Map>() {

		@Override
		public boolean canRead(Class<?> clazz, MediaType mediaType) {
			return true;
		}

		@Override
		public boolean canWrite(Class<?> clazz, MediaType mediaType) {
			return false;
		}

		@Override
		public List<MediaType> getSupportedMediaTypes() {
			ArrayList<MediaType> list = new ArrayList<MediaType>();
			list.add(MediaType.ALL);
			return list;
		}

		@Override
		public Map read(Class<? extends Map> clazz, HttpInputMessage inputMessage) throws IOException,
				HttpMessageNotReadableException {
			
			InputStream in = inputMessage.getBody();
 
		 
			// ISO-8859-1
			// Reader
			 Reader reader = new InputStreamReader(in, "utf-8");
			
			
			//String object = (String) IOUtils.readLines(reader).get(0);
//			Map map = gson.fromJson(object, Global.mapType);
			
			 
			 long star = java.lang.System.currentTimeMillis();
			
			Map map = gson.fromJson(reader, Global.mapType);
			long end  = java.lang.System.currentTimeMillis();

			logger.info("     conver_time_cast :"+ ( end - star ));

			
			
			RestUtils.handlerResponse(map); 

			// logger.info("111111 rest : response : " + map);

			Object err = map.get(Global.err_key);
			if (null != err) {
				throw new ExceptionMessage(err.toString());
			}
			in.close();
			return map;
		}

		@Override
		public void write(Map t, MediaType contentType,
				HttpOutputMessage outputMessage) throws IOException,
				HttpMessageNotWritableException {
		}
	};

	private void reInitMessageConverter(RestTemplate restTemplate) {
		List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
		HttpMessageConverter<?> converterTarget = null;
		for (HttpMessageConverter<?> item : converterList) {
			if (item.getClass() == StringHttpMessageConverter.class) {
				converterTarget = item;
				break;
			}
		}

		if (converterTarget != null) {
			converterList.remove(converterTarget);
		}
		StringHttpMessageConverter a = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		converterList.add(a);
	}

}
