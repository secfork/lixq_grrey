package com.sunwayland.core.validate;

import javax.validation.Payload;

public  @interface EnumString {

	   String[] acceptedValues();

	   
	    String message() default "{uk.dds.ideskos.validator.ValidateString.message}";

	    Class<?>[] groups() default { };

	    Class<? extends Payload>[] payload() default { }; 
}


 