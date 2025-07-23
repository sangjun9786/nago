package com.btw09.buyyourbrain.contracts.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 오직 결제 폼으로 데이터를 보내기 위한 dto 객체
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContracResDTO {
	
	private String projectName; //프로젝트명
	private String workerName;
	private int contractId; // 계약 id (hidden)
	private int amountValue; // 작업비
	private int chargeValue; // 수수료
	private int totalValue; // 토탈
	
	
	

}
