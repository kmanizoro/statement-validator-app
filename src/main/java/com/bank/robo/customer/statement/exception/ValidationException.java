package com.bank.robo.customer.statement.exception;

/**
 * It's custom exception to handle statement validation
 * 
 * @author mani.kasi
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 2816438424415940868L;

	private final ResponseCode errorCode;

	public ValidationException(ResponseCode code, Exception e) {
		super(e);
		this.errorCode = code;
	}

	public ValidationException(ResponseCode codes) {
		super(getMessage(codes));
		this.errorCode = codes;
	}

	public ValidationException(final String message) {
		super(message);
		this.errorCode = null;
	}

	public ResponseCode getErrorCode() {
		return errorCode;
	}

	private static String getMessage(ResponseCode errorCode) {
		if (errorCode != null) {
			return errorCode.name();
		} else {
			return null;
		}
	}
}
