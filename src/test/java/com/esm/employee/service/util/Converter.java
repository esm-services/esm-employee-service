package com.esm.employee.service.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {

	// Convert object to JSON string and save into a file directly
	public static void convertToJsonAndWriteToFile(Object obj) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writeValue(new File("D:\\data.json"), obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Convert object to JSON string
	public static String convertToJsonString(Object obj) {

		ObjectMapper mapper = new ObjectMapper();

		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

	// Convert object to JSON string and pretty print
	public static String convertToPrettyJsonString(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

	// Convert JSON string from file to Object
	public static <T> T convertJsonStrinFromFileToObject(Class<T> targetClass) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.readValue(new File("D:\\data.json"), targetClass);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Convert JSON string to Object
	public static <S, T> T convertJsonStringToObject(String jsonInString, Class<T> targetClass) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.readValue(jsonInString, targetClass);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
