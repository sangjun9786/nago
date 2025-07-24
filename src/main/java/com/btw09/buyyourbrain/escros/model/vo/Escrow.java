package com.btw09.buyyourbrain.escros.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Escrow {
	
//	ESCROW_ID	NUMBER
	private int escrowId;
//	CONTRACT_ID	NUMBER
	private int contractId;
//	TOTAL_AMOUNT	NUMBER
	private int totalAmount;
//	RELEASED_AMOUNT	NUMBER
	private int releasedAmount;
//	REFUNDED_AMOUNT	NUMBER
	private int refundedAmount;
//	STATUS	VARCHAR2(255 BYTE)
	private String status;
//	HOLD_AT	DATE
	private Date holdAt;
//	FIRST_RELEASE_AT	DATE
	private Date firstReleaseAt;
//	FINAL_RELEASE_AT	DATE
	private Date finalReleaseAt;
//	REFUNDED_AT	DATE
	private Date refundedAt;

}