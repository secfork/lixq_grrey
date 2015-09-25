package com.sunwayland.rest.basic;

import org.junit.Test;

public class ff {

	@Test
	public void ff(){
		
		FieldEnum[] values = FieldEnum.values();
		
		for( FieldEnum f : values ){
			String  str = f.toString() ; 
			System.out.println( "public static  Field "+str +"( String value ){" );
			System.out.println("     Field fideld = new Field() ;");
			System.out.println( "   fideld.setKey( \""+str+"\");");
			System.out.println("  fideld.setValue(value);");
			System.out.println("   return fideld ;");
			System.out.println("}");
			System.out.println("    ");
			
			 
			
		} 
	}
	
	@Test
	public  void ff1(){
		FieldEnum[] values = FieldEnum.values();
		
		FieldEnum.action.toString(); 
		
		for( FieldEnum f : values ){
			String  str = f.toString() ; 
			System.out.println( "public  Params "+str +"( String value ){" );
			System.out.println("     this.add( FieldEnum."+str+".toString(), value);  ");
			System.out.println("   return this ;");
			System.out.println("}");
			System.out.println("    ");
			
		} 
		
	}
	
	
/*	 public  Params  id (String  value ){
		 this.getParams().put(key, value);
		 return this ;  
	 } */
	 
	
	
	
}
