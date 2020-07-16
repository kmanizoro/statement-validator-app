package com.bank.robo.customer.statement.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bank.robo.customer.statement.dto.ErrorRecordDto;
import com.bank.robo.customer.statement.dto.StatementResponseDto;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String ERROR = "Oops!";

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ERROR, ex);
		List<ErrorRecordDto> errorRecords = new ArrayList<>();
		StatementResponseDto errorMessage = new StatementResponseDto(ResponseCode.BAD_REQUEST, errorRecords);
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class, RuntimeException.class })
	public ResponseEntity<StatementResponseDto> handleRuntimeException(Exception ex) {
		log.error(ERROR, ex);
		List<ErrorRecordDto> errorRecords = new ArrayList<>();
		StatementResponseDto errorMessage = new StatementResponseDto(ResponseCode.INTERNAL_SERVER_ERROR, errorRecords);
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
