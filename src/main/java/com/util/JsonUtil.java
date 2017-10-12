package com.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class JsonUtil {
	public static String 
	convertJavaToJson(Object obj){
		String jsonStr="";
	ObjectMapper mapper=new ObjectMapper();
	//convert java obj into json
	   try {
		jsonStr=mapper.writeValueAsString(obj);
	} catch (JsonGenerationException e) {
		e.printStackTrace();
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		return jsonStr;
	}
}
