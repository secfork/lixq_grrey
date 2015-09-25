package com.sunwayland.core.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;

public class ValidateException  extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private List<BindingResult>  validateResults ;

    
    
	public List<BindingResult> getValidateResults() {
		return validateResults;
	}

	public void setValidateResults(List<BindingResult> validateResults) {
		this.validateResults = validateResults;
	}

	
	
	
	public ValidateException(List<BindingResult> validateResults) {
		super();
		this.validateResults = validateResults;
	}  
	  
	public ValidateException(BindingResult... validateResults) {
	  	 this.validateResults = new ArrayList<BindingResult>();
		 for( BindingResult br : validateResults){
			 this.validateResults.add(br);
		 } 
	}  
	
	 
	public ValidateException( ) {
		super(); 
	}  
	
	
    
 
    
	
	
}
