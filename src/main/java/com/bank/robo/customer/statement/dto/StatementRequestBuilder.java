package com.bank.robo.customer.statement.dto;

import java.math.BigDecimal;

/**
 * Used to build the StatementRequestDto object
 * 
 * @author mani.kasi
 *
 */
public class StatementRequestBuilder {

	Long reference;

	String accountNumber;

	String description;

	BigDecimal startBalance;

	BigDecimal mutation;

	BigDecimal endBalance;

	public StatementRequestBuilder(Long reference) {
		this.reference = reference;
	}

	public StatementRequestBuilder accountNumber(String accNo) {
		this.accountNumber = accNo;
		return this;
	}

	public StatementRequestBuilder description(String description) {
		this.description = description;
		return this;
	}

	public StatementRequestBuilder startBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
		return this;
	}

	public StatementRequestBuilder mutation(BigDecimal mutation) {
		this.mutation = mutation;
		return this;
	}

	public StatementRequestBuilder endBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
		return this;
	}

	public StatementRequestDto build() {
		return new StatementRequestDto(this);
	}

}
