package com.btw09.buyyourbrain.worksession.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessionReqDTO {
	
//	SESSION_ID	NUMBER
	private int sessionId;
//	일자
	private String  day;
//	출근 시각
	private String  startedAt;
//	퇴근 시각
	private String  endedAt;
	private String status;

}
