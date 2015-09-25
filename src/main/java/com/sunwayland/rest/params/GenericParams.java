package com.sunwayland.rest.params;

import java.util.Map;

import com.sunwayland.rest.basic.FieldEnum;
import com.sunwayland.rest.basic.VarFieldEnum;

public abstract class GenericParams {
	 
    	 
     public abstract Map getMap() ;  
      
 
	 
	 public GenericParams connect( GenericParams  params ){
		 this.getMap().putAll(params.getMap());
		 return this ; 
	 }
	 
	 
	 public  GenericParams put ( String  key , Object value) {
		 this.getMap().put(key, value.toString());
		 
		 return this; 
	 };
	 
	 public  GenericParams  clear(){
		 this.getMap().clear(); 
		 return this ; 
	 }
	 
	 
	 //=======================
//	  GenericParams   accessKey( String value){
//		    this.getParams().put( VarFieldEnum.accesskey.toString(), value);
//		    return this; 
//	 }
	   
	 public  GenericParams state ( String state){
		 this.put(FieldEnum.state.toString(),  state );
		 return this ; 
	 }
	 
	 
	 /**
	  *  + , -  好 会 urlDecode 转义 , 恶心;  
	  * @param field
	  * @param isDESC
	  * @return
	  */
	 public  GenericParams sorts ( FieldEnum field , Boolean  isDESC  ){
		 String sorts = VarFieldEnum.sorts.toString();
		 
//		  Object object = this.getParams().get( sorts);
//		 
//		 if(null == object ){
			 this.put(sorts, field.toString() +( isDESC==true?"+":"-") );
//		 }else{
//			string +="&sorts="+field.toString() +( isDESC==true?"+":"-");
//			this.put(sorts, string);
//		 }
		 return this ; 
	 }
	 
	 public GenericParams offset( int  value ){
		  this.put(VarFieldEnum.offset.toString(), value+"");
		  return this ; 
	 }
	 public GenericParams limit( int  value ){
		 this.put(VarFieldEnum.limit.toString(), value+"");
		 return this ; 
	 }
	 
	 public GenericParams calc_sum( boolean  value ){
		 this.put(VarFieldEnum.calc_sum.toString(), value+"");
		 return this ; 
	 }
	 
	 public GenericParams group_id( String  value ){
		 this.put(VarFieldEnum.group_id.toString(), value );
		 return this ; 
	 }
	 
	 public GenericParams user_id( String  value ){
		 this.put("user_id", value );
		 return this ; 
	 }



	public GenericParams isAccountPermission(boolean b) {
		 this.put("isaccount", b );
		return this;
	}



	public GenericParams region_id(String region_id) {
		 this.put("region_id", region_id );
		 return this;
	}
	 
	 
	
}
