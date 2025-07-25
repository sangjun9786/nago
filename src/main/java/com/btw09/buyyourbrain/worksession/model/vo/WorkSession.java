package com.btw09.buyyourbrain.worksession.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkSession {
	
//	SESSION_ID	NUMBER
	private int sessionId;
//	WORKER_ID	NUMBER
	private int workerId;
//	WORKSITE_ID	NUMBER
	private int worksiteId;
//	STARTED_AT	DATE
	private Date startedAt;
//	ENDED_AT	DATE
	private Date endedAt;
//	STATUS	VARCHAR2(20 BYTE)
	private String status;
//	SESSION_TYPE	VARCHAR2(255 BYTE)
//	PLAN_DAY	DATE
	private Date planDay;
	

}
