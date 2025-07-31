package com.btw09.buyyourbrain.member.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Worker {
	
//	WORKER_ID	NUMBER
	private int workerId;
//	WORKER_NAME
	private String workerName;
//	IS_CERTIFICATED	VARCHAR2(10 BYTE)
	private String isCertificated;
//	CERTIFICATED_AT	DATE
	private Date certificatedAt;
//	RFID_TAG_ID	NUMBER
	private int rfidTagId;
//	AVAILABLE	VARCHAR2(10 BYTE)
	private String available;
//	CAREER	VARCHAR2(255 BYTE)
	private String career;
//	SKILLS	VARCHAR2(255 BYTE)
	private String skills;
//	PROFILE_IMAGE_URL	VARCHAR2(255 BYTE)
	private String profileImageURL;
//	PORTFOLIO_LINK	VARCHAR2(255 BYTE)
	private String portfolioLink;
//	COMPANY_ID	NUMBER
	private int companyId;

}
