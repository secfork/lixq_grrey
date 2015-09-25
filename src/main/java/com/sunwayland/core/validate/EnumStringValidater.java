package com.sunwayland.core.validate;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumStringValidater  implements  ConstraintValidator< EnumString  , String >{

	private List<String> valueList;
	
	@Override
	public void initialize(EnumString constraintAnnotation) { 
		String[] acceptedValues = constraintAnnotation.acceptedValues();	
		 
		for( String v: acceptedValues){
				valueList.add(v);
		}
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		  if(!valueList.contains(value )) {
	            return false;
	        }
	        return true;
	}

}
