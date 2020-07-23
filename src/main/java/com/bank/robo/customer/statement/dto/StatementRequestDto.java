package com.bank.robo.customer.statement.dto;

import java.math.BigDecimal;

/**
 * Statement Request Dto
 * 
 * @author mani.kasi
 *
 */
public class StatementRequestDto {

	Long reference;

	String accountNumber;

	String description;

	BigDecimal startBalance;

	BigDecimal mutation;

	BigDecimal endBalance;

	public StatementRequestDto() {
		this(null, null, null, null, null, null);
	}

	public StatementRequestDto(Long reference, String accountNumber, String description, BigDecimal startBalance,
			BigDecimal mutation, BigDecimal endBalance) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
		this.description = description;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.endBalance = endBalance;
	}

	StatementRequestDto(StatementRequestBuilder builder) {
		this.reference = builder.reference;
		this.accountNumber = builder.accountNumber;
		this.description = builder.description;
		this.startBalance = builder.startBalance;
		this.mutation = builder.mutation;
		this.endBalance = builder.endBalance;
	}

	public Long getReference() {
		return reference;
	}

	public void setReference(Long reference) {
		this.reference = reference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}

	public BigDecimal getMutation() {
		return mutation;
	}

	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}

	public BigDecimal getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}
	
}
