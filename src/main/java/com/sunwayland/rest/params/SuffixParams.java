package com.sunwayland.rest.params;

import java.util.Map;

import org.apache.commons.collections.MultiHashMap;

import com.sunwayland.rest.basic.VarFieldEnum;


public class SuffixParams  extends Params  {
  
    
   	public MultiHashMap map = new MultiHashMap(1); 
   	
	@Override
	public MultiHashMap getMap() {   
	    return this.map ;
	  
	}
	 
   	
	private SuffixParams(){
		super();  
	}
	
	public  static SuffixParams get(){
		return new SuffixParams() ; 
	}

	//=========================================
	  
	public  SuffixParams  extend_profile (boolean value ){
		this.put(VarFieldEnum.extend_profile.toString()	, value  );
		return this ; 
	}
	
	public  SuffixParams  extend_device (boolean value ){
		this.put(VarFieldEnum.extend_device.toString()	, value  );
		return this ; 
	}

	public GenericParams isaccount(boolean b) {
		this.put( "isaccount"	, b );
		return this ; 
	}


	
	
 
 
}
