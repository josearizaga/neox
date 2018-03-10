package com.neox.inventory.ws.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtil {
	
	public static ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}
	
}
