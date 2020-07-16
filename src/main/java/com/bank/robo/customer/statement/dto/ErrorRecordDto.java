package com.bank.robo.customer.statement.dto;

/**
 * Error Record Response Dto
 * @author mani.kasi
 *
 */
public class ErrorRecordDto {

	Long reference;

	String accountNumber;

	public ErrorRecordDto(Long reference, String accountNumber) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
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

	@Override
	public String toString() {
		return "ErrorRecordDto [reference=" + reference + ", accountNumber=" + accountNumber + "]";
	}

}
