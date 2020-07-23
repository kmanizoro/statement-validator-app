package com.bank.robo.customer.statement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bank.robo.customer.statement.dto.ErrorRecordDto;
import com.bank.robo.customer.statement.dto.StatementRequestDto;
import com.bank.robo.customer.statement.dto.StatementResponseDto;
import com.bank.robo.customer.statement.exception.ResponseCode;
import com.bank.robo.customer.statement.service.ValidatorService;
import com.bank.robo.customer.statement.util.CommonUtil;

/**
 * Validator Service impmentation class
 * 
 * @author mani.kasi
 *
 */
@Service
public class ValidatorServiceImpl implements ValidatorService {

	private static final Logger logger = LoggerFactory.getLogger(ValidatorServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StatementResponseDto validateCustomerStatements(List<StatementRequestDto> statementRequestDtos) {
		if (logger.isDebugEnabled()) {
			logger.info("Service validateCustomerStatements Begin");
		}
		List<ErrorRecordDto> errorRecords = new ArrayList<>();
		StatementResponseDto responseDto = new StatementResponseDto(ResponseCode.SUCCESSFUL, errorRecords);

		// checking duplicates
		List<ErrorRecordDto> duplicateRecords = getDuplicateReferenceRecords(statementRequestDtos);
		boolean duplicateFound = CommonUtil.isNotNullOrEmpty(duplicateRecords);
		if (duplicateFound) {
			errorRecords.addAll(duplicateRecords);
			responseDto.setResult(ResponseCode.DUPLICATE_REFERENCE);
		}

		// checking incorrect balance
		List<ErrorRecordDto> inCorrectRecords = getIncorrectBalanceRecords(statementRequestDtos);
		if (CommonUtil.isNotNullOrEmpty(inCorrectRecords)) {
			errorRecords.addAll(inCorrectRecords);
			if (duplicateFound) {
				responseDto.setResult(ResponseCode.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE);
			} else {
				responseDto.setResult(ResponseCode.INCORRECT_END_BALANCE);
			}
		}

		if (logger.isDebugEnabled()) {
			logger.info("Service validateCustomerStatements End");
		}
		return responseDto;
	}

	/**
	 * Used to check duplicate reference is exits or not.
	 * 
	 * @param statementRequestDtos
	 * @return
	 */
	private List<ErrorRecordDto> getDuplicateReferenceRecords(List<StatementRequestDto> statementRequestDtos) {
		if (CommonUtil.isNotNullOrEmpty(statementRequestDtos)) {
			List<ErrorRecordDto> duplicateRecords = statementRequestDtos.stream()
					.collect(Collectors.groupingBy(StatementRequestDto::getReference)).values().stream()
					.filter(obj -> obj.size() > 1).map(obj -> obj.stream().findFirst().get())
					.map(statement -> new ErrorRecordDto(statement.getReference(), statement.getAccountNumber()))
					.collect(Collectors.toList());
			if (logger.isDebugEnabled()) {
				logger.info(String.format("getDuplicateReferenceRecords %s", duplicateRecords.toString()));
			}
			return duplicateRecords;
		}
		return new ArrayList<>();
	}

	/**
	 * Used to check incorrect balance
	 * 
	 * @param statementRequestDtos
	 * @return
	 */
	private List<ErrorRecordDto> getIncorrectBalanceRecords(List<StatementRequestDto> statementRequestDtos) {
		if (CommonUtil.isNotNullOrEmpty(statementRequestDtos)) {
			List<ErrorRecordDto> incorrectRecords = statementRequestDtos.stream()
					.filter(stmt -> stmt.getEndBalance().compareTo(stmt.getStartBalance().add(stmt.getMutation())) != 0)
					.map(statement -> new ErrorRecordDto(statement.getReference(), statement.getAccountNumber()))
					.collect(Collectors.toList());

			if (logger.isDebugEnabled()) {
				logger.info(String.format("getIncorrectBalanceRecords %s", incorrectRecords.toString()));
			}
			return incorrectRecords;
		}
		return new ArrayList<>();
	}

}
