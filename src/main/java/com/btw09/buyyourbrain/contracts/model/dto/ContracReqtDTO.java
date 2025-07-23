package com.btw09.buyyourbrain.contracts.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * test.html -> createAndSend Controller 메소드
 * 데이터 전달 객체 
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContracReqtDTO {
	
	private int contractId;
	private int companyId;
	private int selectedWorkerId;
	private int amountValue;
	
	

}
