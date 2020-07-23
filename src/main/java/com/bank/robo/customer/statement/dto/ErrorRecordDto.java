package com.bank.robo.customer.statement.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Error Record Response Dto
 * 
 * @author mani.kasi
 *
 */

public class ErrorRecordDto {

	Long reference;

	@Getter
	@Setter
	String accountNumber;

	public ErrorRecordDto(Long reference, String accountNumber) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "ErrorRecordDto [reference=" + reference + ", accountNumber=" + accountNumber + "]";
	}

	public Long getReference() {
		return reference;
	}

	public void setReference(Long reference) {
		this.reference = reference;
	}

}
