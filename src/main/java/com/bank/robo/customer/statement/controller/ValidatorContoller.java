package com.bank.robo.customer.statement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.robo.customer.statement.dto.StatementRequestDto;
import com.bank.robo.customer.statement.dto.StatementResponseDto;
import com.bank.robo.customer.statement.exception.ValidationException;
import com.bank.robo.customer.statement.service.ValidatorService;

/**
 * 
 * Validator Controller
 * 
 * @author mani.kasi
 *
 */
@RestController
@RequestMapping(path = "/statements")
public class ValidatorContoller {

	private static final Logger logger = LoggerFactory.getLogger(ValidatorContoller.class);

	@Autowired
	ValidatorService validatorService;

	/**
	 * validate end point used to validate the customer statements
	 * 
	 * @param statementRequestDtos
	 * @return
	 * @throws ValidationException
	 */
	@PostMapping(value = "/validate")
	public ResponseEntity<StatementResponseDto> validateCustomerStatements(
			@RequestBody List<StatementRequestDto> statementRequestDtos) throws ValidationException {
		if (logger.isDebugEnabled()) {
			logger.info("validateCustomerStatements started");
		}
		return new ResponseEntity<>(validatorService.validateCustomerStatements(statementRequestDtos), HttpStatus.OK);
	}
}
