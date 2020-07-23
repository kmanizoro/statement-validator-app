package com.bank.robo.customer.statement.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bank.robo.customer.statement.controller.ValidatorContoller;
import com.bank.robo.customer.statement.service.impl.ValidatorServiceImpl;

@ContextConfiguration(classes = { ValidatorServiceImpl.class })
public class CommonExceptionHandlerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private ValidatorContoller validatorContoller;

	@Mock
	private CommonExceptionHandler commonExceptionHandler;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(validatorContoller).setControllerAdvice(commonExceptionHandler)
				.build();
	}

	@Test
	public void testCommonExceptionHandler_404() throws Exception {
		mockMvc.perform(get("/validate")).andExpect(status().is(404));
	}
}
