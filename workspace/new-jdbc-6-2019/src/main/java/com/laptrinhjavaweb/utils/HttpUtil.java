package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	private String value;
	
	public HttpUtil(String value) {
		this.setValue(value);
	}
	
	// mapping string to Model
	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// return result is string obj from json obj <BufferedReader>
	public static HttpUtil of (BufferedReader reader) {
		StringBuilder sBuilder = new StringBuilder();
		try {
			String line;
			while((line = reader.readLine()) != null) {
				sBuilder.append(line);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new HttpUtil(sBuilder.toString());
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
