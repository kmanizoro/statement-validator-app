package com.bank.robo.customer.statement.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.bank.robo.customer.statement.dto.StatementRequestBuilder;
import com.bank.robo.customer.statement.dto.StatementRequestDto;
import com.bank.robo.customer.statement.service.impl.ValidatorServiceImpl;
import com.bank.robo.customer.statement.util.TestConstants;
import com.bank.robo.customer.statement.util.TestUtil;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { ValidatorContoller.class, ValidatorServiceImpl.class })
@WebMvcTest
public class ValidatorContollerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void validateCustomerStatements_Ok() throws Exception {
		mockMvc.perform(post("/statements/validate").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objectToString(getMockStatementRequestDto()))).andExpect(status().isOk());
	}

	@Test
	public void validateCustomerStatements_Ok_successful() throws Exception {
		mockMvc.perform(post("/statements/validate").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objectToString(getMockStatementRequestDto()))).andExpect(status().isOk())
				.andExpect(jsonPath("result", is(TestConstants.SUCCESSFUL)));
	}

	@Test
	public void validateCustomerStatements_Ok_incorrectBalance() throws Exception {
		mockMvc.perform(post("/statements/validate").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objectToString(getMockStatementRequestDto_incorrectBalance())))
				.andExpect(status().isOk()).andExpect(jsonPath("result", is(TestConstants.INCORRECT_END_BALANCE)));
	}

	@Test
	public void validateCustomerStatements_Ok_duplicateReference() throws Exception {
		mockMvc.perform(post("/statements/validate").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objectToString(getMockStatementRequestDto_duplicateReference())))
				.andExpect(status().isOk()).andExpect(jsonPath("result", is(TestConstants.DUPLICATE_REFERENCE)));
	}

	@Test
	public void validateCustomerStatements_Ok_duplicateReference_incorrectBalance() throws Exception {
		mockMvc.perform(post("/statements/validate").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objectToString(getMockStatementRequestDto_duplicateReference_incorrectBalance())))
				.andExpect(status().isOk())
				.andExpect(jsonPath("result", is(TestConstants.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE)));
	}

	@Test
	public void validateCustomerStatements_badRequest() throws Exception {
		mockMvc.perform(post("/statements/validate").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void validateCustomerStatements_notFound() throws Exception {
		mockMvc.perform(post("/statements/valid").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

//	@Test
//	public void validateCustomerStatements_internalServer() throws Exception {
//		mockMvc.perform(post("/statements/validate").contentType(MediaType.APPLICATION_JSON)
//				.content(TestUtil.objectToString(getMockStatementRequestDto_WithoutReference())))
//				.andExpect(jsonPath("result", is(TestConstants.INTERNAL_SERVER_ERROR)));
//	}

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

	private List<StatementRequestDto> getMockStatementRequestDto_duplicateReference_incorrectBalance() {
		List<StatementRequestDto> statementRequestDtos = new ArrayList<>();
		StatementRequestDto requestDto = new StatementRequestBuilder(12722l).accountNumber("ABGD023823")
				.description("Valid Account").endBalance(BigDecimal.valueOf(100.0))
				.startBalance(BigDecimal.valueOf(50.0)).mutation(BigDecimal.valueOf(50.0)).build();
		StatementRequestDto requestDto2 = new StatementRequestBuilder(12722l).accountNumber("BCDA23820")
				.description("Duplicate Account").endBalance(BigDecimal.valueOf(120.0))
				.startBalance(BigDecimal.valueOf(50.0)).mutation(BigDecimal.valueOf(70.0)).build();
		StatementRequestDto requestDto3 = new StatementRequestBuilder(12722l).accountNumber("BCDA23820")
				.description("Account").endBalance(BigDecimal.valueOf(30.0)).startBalance(BigDecimal.valueOf(50.0))
				.mutation(BigDecimal.valueOf(-70.0)).build();
		statementRequestDtos.add(requestDto);
		statementRequestDtos.add(requestDto2);
		statementRequestDtos.add(requestDto3);
		return statementRequestDtos;
	}

//	private List<StatementRequestDto> getMockStatementRequestDto_WithoutReference() {
//		List<StatementRequestDto> statementRequestDtos = new ArrayList<>();
//		StatementRequestDto requestDto = new StatementRequestBuilder(12722l).accountNumber("ABGD023823")
//				.description("TEST Account").mutation(BigDecimal.valueOf(20.0)).build();
//		statementRequestDtos.add(requestDto);
//		return statementRequestDtos;
//	}
}
