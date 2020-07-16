package com.bank.robo.customer.statement.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TestUtil Class
 * 
 * @author mani.kasi
 *
 */
public class TestUtil {

	private TestUtil() {
		// private constructor
	}

	private static ObjectMapper mapper = new ObjectMapper();

	public static <T> String objectToString(T obj) throws JsonProcessingException {
		return mapper.writeValueAsString(obj);
	}
}
