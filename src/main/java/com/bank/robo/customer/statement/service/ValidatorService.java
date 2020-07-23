package com.bank.robo.customer.statement.service;

import java.util.List;

import com.bank.robo.customer.statement.dto.StatementRequestDto;
import com.bank.robo.customer.statement.dto.StatementResponseDto;

/**
 * Validator Service Interface
 * 
 * @author mani.kasi
 *
 */
public interface ValidatorService {

	/**
	 * validate service used to validate the customer statements
	 * 
	 * @param statementRequestDtos
	 * @return
	 */
	public StatementResponseDto validateCustomerStatements(List<StatementRequestDto> statementRequestDtos);
}
