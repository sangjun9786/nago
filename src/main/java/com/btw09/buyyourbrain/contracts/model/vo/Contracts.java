package com.btw09.buyyourbrain.contracts.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contracts {
	
//	CONTRACT_ID	NUMBER
	private int contractId;
//	COMPANY_ID	NUMBER
	private int companyId;
//	WORKER_ID	NUMBER
	private int workerId;
//	PROJECT_NAME	VARCHAR2(255 BYTE)
	private String projectName;
//	STATUS	VARCHAR2(255 BYTE)
	private String status;
//	SIGNED_AT	DATE
	private Date signedAt;
//	DUE_DATE	DATE
	private Date dueDate;
//	CONTRACT_PRICE	NUMBER
	private int contractPrice;
//	PAYMENT_TERMS	VARCHAR2(255 BYTE)
	private String paymentTerms;

	
	public Contracts(int contractId, int companyId, int workerId, String status, Date dueDate, int contractPrice) {
		this.contractId = contractId;
		this.companyId = companyId;
		this.workerId = workerId;
		this.status = status;
		this.dueDate = dueDate;
		this.contractPrice = contractPrice;
	}
	
	
	
}


