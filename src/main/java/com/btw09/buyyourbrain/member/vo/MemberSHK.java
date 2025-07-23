package com.btw09.buyyourbrain.member.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberSHK {
	
//	USER_NO	NUMBER
	private int userNo;
//	USER_NAME	VARCHAR2(255 BYTE)
	private String userName;
//	JOINED_AT	DATE
	private Date joinedAt;
//	EXPERTISE	VARCHAR2(255 BYTE)
	private String expertise;

}
