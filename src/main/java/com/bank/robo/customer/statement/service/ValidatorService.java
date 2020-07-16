package com.bank.robo.customer.statement.service;

import java.util.List;

import com.bank.robo.customer.statement.dto.StatementRequestDto;
import com.bank.robo.customer.statement.dto.StatementResponseDto;
import com.bank.robo.customer.statement.exception.ValidationException;

/**
 * Validator Service Interface
 * 
 * @author mani.kasi
 *
 */
public interface ValidatorService {

	/**
	 * validate service used to validate the customer statements
	 * @param statementRequestDtos
	 * @return
	 * @throws ValidationException
	 */
	public StatementResponseDto validateCustomerStatements(List<StatementRequestDto> statementRequestDtos)
			throws ValidationException;
}
