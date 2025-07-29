package com.btw09.buyyourbrain.worksession.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MileStone {
	
//	MILESTONE_ID	NUMBER
	private int milestoneId;
//	CONTRACT_ID	NUMBER
	private int contractId;
//	seq_no	NUMBER
	private int seqNo;
//	title	VARCHAR2(255 BYTE)
	private String title;
//	status	VARCHAR2(255 BYTE)
	private String status;
//	END_DATE	DATE
	private Date endDate;
//	START_DATE	DATE
	private Date startDate;
// 	IS_PAID
	private String isPaid;

}
