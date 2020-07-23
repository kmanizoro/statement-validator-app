package com.bank.robo.customer.statement.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.bank.robo.customer.statement.dto.StatementRequestBuilder;
import com.bank.robo.customer.statement.dto.StatementRequestDto;
import com.bank.robo.customer.statement.dto.StatementResponseDto;
import com.bank.robo.customer.statement.exception.ResponseCode;

public class ValidatorServiceImplTest {

	@InjectMocks
	private ValidatorServiceImpl validatorServiceImpl;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void validateCustomerStatements_success() {
		StatementResponseDto resDto = validatorServiceImpl.validateCustomerStatements(getMockStatementRequestDto());
		assertEquals(ResponseCode.SUCCESSFUL, resDto.getResult());
	}

	@Test
	public void validateCustomerStatements_incorrectBalance() {
		StatementResponseDto resDto = validatorServiceImpl
				.validateCustomerStatements(getMockStatementRequestDto_incorrectBalance());
		assertEquals(ResponseCode.INCORRECT_END_BALANCE, resDto.getResult());
	}

	@Test
	public void validateCustomerStatements_duplicateReference() {
		StatementResponseDto resDto = validatorServiceImpl
				.validateCustomerStatements(getMockStatementRequestDto_duplicateReference());
		assertEquals(ResponseCode.DUPLICATE_REFERENCE, resDto.getResult());
	}

	@Test
	public void validateCustomerStatements_duplicateReference_incorrectBalance() {
		List<StatementRequestDto> list = getMockStatementRequestDto_duplicateReference();
		list.addAll(getMockStatementRequestDto_incorrectBalance());
		StatementResponseDto resDto = validatorServiceImpl.validateCustomerStatements(list);
		assertEquals(ResponseCode.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE, resDto.getResult());
	}

	private List<StatementRequestDto> getMockStatementRequestDto() {
		List<StatementRequestDto> statementRequestDtos = new ArrayList<>();
		StatementRequestDto requestDto = new StatementRequestBuilder(12722l).accountNumber("ABGD023823")
				.description("Valid Balace Account").endBalance(BigDecimal.valueOf(100.0))
				.startBalance(BigDecimal.valueOf(50.0)).mutation(BigDecimal.valueOf(50.0)).build();
		statementRequestDtos.add(requestDto);
		return statementRequestDtos;
	}

	private List<StatementRequestDto> getMockStatementRequestDto_incorrectBalance() {
		List<StatementRequestDto> statementRequestDtos = new ArrayList<>();
		StatementRequestDto requestDto = new StatementRequestBuilder(12722l).accountNumber("ABGD023823")
				.description("Valid Balance Account").endBalance(BigDecimal.valueOf(100.0))
				.startBalance(BigDecimal.valueOf(50.0)).mutation(BigDecimal.valueOf(20.0)).build();
		statementRequestDtos.add(requestDto);
		return statementRequestDtos;
	}

	private List<StatementRequestDto> getMockStatementRequestDto_duplicateReference() {
		List<StatementRequestDto> statementRequestDtos = new ArrayList<>();
		StatementRequestDto requestDto = new StatementRequestBuilder(12722l).accountNumber("ABGD023823")
				.description("Valid Account").endBalance(BigDecimal.valueOf(100.0))
				.startBalance(BigDecimal.valueOf(50.0)).mutation(BigDecimal.valueOf(50.0)).build();
		StatementRequestDto requestDto2 = new StatementRequestBuilder(12722l).accountNumber("BCDA23820")
				.description("Duplicate Account").endBalance(BigDecimal.valueOf(120.0))
				.startBalance(BigDecimal.valueOf(50.0)).mutation(BigDecimal.valueOf(70.0)).build();
		statementRequestDtos.add(requestDto);
		statementRequestDtos.add(requestDto2);
		return statementRequestDtos;
	}
}
