package com.btw09.buyyourbrain.escros.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EscrowLog {
	
//	LOG_ID	NUMBER
	private int logId;
//	ESCROW_ID	NUMBER
	private int escrowId;
//	MILESTONE_ID	NUMBER
	private int milestoneId;
//	EVENT_TYPE	VARCHAR2(255 BYTE)
	private String eventType;
//	RECIPIENT	VARCHAR2(255 BYTE)
	private String recipient;
//	PAYLOAD	NUMBER
	private int payload;
//	CREATED_AT	DATE
	private Date createdAt;
//	DESCRIPTION	VARCHAR2(255 BYTE)
	private String description;

}
