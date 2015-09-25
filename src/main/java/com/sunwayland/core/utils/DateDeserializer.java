package com.sunwayland.core.utils;

import java.lang.reflect.Type;
import java.util.Date;
 
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
 
 
public class DateDeserializer implements JsonDeserializer<Long> {
 
    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//        return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
	System.out.println("11111111111111111");
	json.getAsLong();
	 
        return   json.getAsJsonPrimitive().getAsLong() ;
        
    }
}
