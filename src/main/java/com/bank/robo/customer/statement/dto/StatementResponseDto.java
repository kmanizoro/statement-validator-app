package com.bank.robo.customer.statement.dto;

import java.util.List;

import com.bank.robo.customer.statement.exception.ResponseCode;

import lombok.Getter;
import lombok.Setter;

/**
 * Statement Response Dto
 * 
 * @author mani.kasi
 *
 */
public class StatementResponseDto {

	ResponseCode result;
	@Getter
	@Setter
	List<ErrorRecordDto> errorRecords;

	public StatementResponseDto() {
		this(null, null);
	}

	public StatementResponseDto(ResponseCode code) {
		this(code, null);
	}

	public StatementResponseDto(ResponseCode code, List<ErrorRecordDto> errors) {
		this.result = code;
		this.errorRecords = errors;
	}

	public ResponseCode getResult() {
		return result;
	}

	public void setResult(ResponseCode result) {
		this.result = result;
	}
	
	
}
