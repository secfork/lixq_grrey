package com.sunwayland.rest.params;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MultiHashMap;



public class QueryParams extends GenericParams {
    
	public MultiHashMap map = new MultiHashMap(1); 
   	
	@Override
	public Map getMap() { 
	    return this.map ;
	}
	
	
	
	private QueryParams (){
		super();
	}
	
	public static  QueryParams get(){
		return new QueryParams();
	}
	//===========================
   
	 
	
	
}
