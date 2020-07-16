package com.bank.robo.customer.statement.util;

import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TestUtil Class
 * 
 * @author mani.kasi
 *
 */
public class CommonUtil {

	private CommonUtil() {
		// private constructor
	}

	private static ObjectMapper mapper = new ObjectMapper();

	public static <T> String objectToString(T obj) throws JsonProcessingException {
		return mapper.writeValueAsString(obj);
	}

	public static boolean isNullOrEmpty(Collection<?> obj) {
		return obj == null || obj.isEmpty();
	}

	public static boolean isNotNullOrEmpty(Collection<?> obj) {
		return !isNullOrEmpty(obj);
	}
}
