package com.sunwayland.core.testsupport;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.sunwayland.rest.eneityV2.User;

import controller.Url;


@RunWith(  SpringJUnit4ClassRunner.class )
@WebAppConfiguration( value="src/main/webapp")  
@ContextHierarchy(
		{   @ContextConfiguration( name="parent" , locations="classpath:spring/application.xml")
		  , @ContextConfiguration( name="child" ,  locations="classpath:spring/spring-mvc.xml")
		}
 )
public class MvcTestSupport {
	
	public Logger  log = Logger.getLogger(MvcTestSupport.class);
	 
    public   Gson gson  = new Gson() ; 
    
    @Autowired  
    private WebApplicationContext applicationContext; 
    
    
	/**
	 * 登录
	 */ 
	public void login (){ 
		User ue = new User() ; 
		ue.setCompany_name("123123");
		ue.setUsername("123123");
		ue.setPassword("96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e");
		
		this.postJsonRequest( Url.longUrl, ue);
		
	}
    


	public WebApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(WebApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}
 
	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}


	protected MockMvc mockMvc; 
 
    @Before  
    public void setUp() {  
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();  
    } 
     
 
    
    /**
     * 以 content-type: "application/json" 方式传递 json数据; 
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public void  postJsonRequest ( String url , Object params ) {
    	System.out.println( "--------------sss------------------------------------------------");
		
	    	  try {
	    		  System.out.println("requirest url    =  "+  url);
	    		  String  json =  this.gson.toJson(params);
	    		  System.out.println("                 parmas =  "+  json);
		    	  MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post(url ).characterEncoding("utf-8")
		         		    .contentType(MediaType.APPLICATION_JSON)
		         		    .content(    json  ) ;
		    	  MvcResult mvcResult = mockMvc.perform(content).andReturn();
		    	  
			System.out.println( "response = " + mvcResult.getResponse().getContentAsString());  
			System.out.println( "--------------eeee------------------------------------------------");
		} catch  ( Exception e) {
			e.printStackTrace();
		}
	    	  
    } 
    
    
    /**
     * 适合 post   普通请求
     *  以 content-type: "application/x-www-form-urlencoded" 方式传递 json数据; 
     * @return
     * @throws Exception 
     */
    public  void  postRequest ( String url  , HashMap<String, String> params  )  {
    	System.out.println( "---------sss-----------------------------------------------------");
		
    	try {
    		   System.out.println("requirest url    =  "+  url);
	    	   System.out.println("          parmas =  "+  params);
	    	   
	    	 MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post(url).
	    			                          characterEncoding("utf-8")
	    	                                 .contentType(MediaType.APPLICATION_FORM_URLENCODED) ; 
	    	 if( null != params ){
	    		 Set<Entry<String, String>> entrySet = params.entrySet();
	    		 for( Entry<String, String>  p : entrySet ){
	    			 content.param(p.getKey(), p.getValue());
	    		 }
	    	 }
	    	 MvcResult result = mockMvc.perform(content).andReturn();
	    	System.out.println( "response = "+ result.getResponse().getContentAsString()    );
	    	System.out.println( "---------eee-----------------------------------------------------");
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
    	 
    }
   
 	 /**
 	  * 适合 get 普通请求
 	  * @param url
 	  * @return
 	 * @throws Exception 
 	  */
    public void getRequest ( String url  , Object...params ) {
    	System.out.println( "--------sss------------------------------------------------------");
    	  MockHttpServletRequestBuilder content = MockMvcRequestBuilders.get(url, params) 
    			                 .characterEncoding("utf-8")
    	                         .contentType(MediaType.APPLICATION_FORM_URLENCODED);
		 	try {
		 		MvcResult mvcResult = mockMvc.perform(content).andReturn();
				System.out.println( "response = "+ mvcResult.getResponse().getContentAsString()    );
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println( "--------eeee------------------------------------------------------");
	
    }
    
    public void getRequest ( String url    )  {
    	System.out.println( "--------sss------------------------------------------------------");
    	  MockHttpServletRequestBuilder content = MockMvcRequestBuilders.get(url)  ;
		 	try {
		 		MvcResult mvcResult = mockMvc.perform(content).andReturn();
				System.out.println( "response = "+ mvcResult.getResponse().getContentAsString()    );
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println( "--------eeee------------------------------------------------------");

    }
    
    
    public void postRequest ( String url ){
    	this.postRequest(url, null);
    	
    }
    
    
}
