package com.sunwayland.web.vo;

import java.lang.annotation.Documented;


public class ErrCode {
		
	
	public static  int  unknown = 0 ;
	 /**  验证码错误 */
    public static  int  indefifyErr= 1 ;
    
	 /**licence 错误 */
    public static  int  licenceErr = 2; 
    
	 /** 采集占sn 错误*/
    public static  int  station_sn = 3 ;
     
    /**
     * 为 user 添加  可管理proj时 , proj 重复, ( 即 之前已经 关联过该 proj )
     */ 
    public static  int  hasManageProj = 4 ; 
     
    
    
	public static int getUnknown() {
		return unknown;
	}
	public static void setUnknown(int unknown) {
		ErrCode.unknown = unknown;
	}
	public static int getIndefifyErr() {
		return indefifyErr;
	}
	public static void setIndefifyErr(int indefifyErr) {
		ErrCode.indefifyErr = indefifyErr;
	}
	public static int getLicenceErr() {
		return licenceErr;
	}
	public static void setLicenceErr(int licenceErr) {
		ErrCode.licenceErr = licenceErr;
	}
	public static int getStation_sn() {
		return station_sn;
	}
	public static void setStation_sn(int station_sn) {
		ErrCode.station_sn = station_sn;
	}
	 
	  
    
    
    

}
