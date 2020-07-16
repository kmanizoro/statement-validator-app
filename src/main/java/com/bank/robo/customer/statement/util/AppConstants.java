package com.bank.robo.customer.statement.util;

/**
 * Common Application Constants
 * 
 * @author mani.kasi
 *
 */
public final class AppConstants {

	private AppConstants() {
		// default private constructor
	}

	public static final String APP_BASE_PACKAGE = "com.bank.robo.customer.statement.controller";

	public static final String CORS_ORIGIN = "*";

	public static final String CORS_METHODS = "POST, OPTIONS";

	public static final String CORS_MAX_AGE = "3600";

	public static final String CORS_ALLOW_HEADERS = "Content-Type, ResponseType, Access-Control-Allow-Headers";

	public static final String CORS_METHOD_OPTIONS = "OPTIONS";
}
