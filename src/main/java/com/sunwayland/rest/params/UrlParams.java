package com.sunwayland.rest.params;

import java.util.HashMap;
import java.util.Map;


public class UrlParams   extends Params {
	 	
    	
    HashMap<String , String> map = new HashMap<String , String>();
    @Override
	public Map getMap() { 
	    return this.map;
	}

	 
	private UrlParams(){
		super();  
	}
	
	public  static UrlParams get(){
		return new UrlParams() ; 
	}


	
	
	
 
}
