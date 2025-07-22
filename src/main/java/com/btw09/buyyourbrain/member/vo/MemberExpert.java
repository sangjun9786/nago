package com.btw09.buyyourbrain.member.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberExpert {
	
	private int userNo;//	USER_NO	NUMBER	No	
	private String userId;//	USER_ID	VARCHAR2(255 BYTE)	Yes	
	private String userPwd;//	PASSWORD	VARCHAR2(255 BYTE)	No	
	private String userName;//	USER_NAME	VARCHAR2(255 BYTE)	Yes	
	private String email;//	EMAIL	VARCHAR2(255 BYTE)	Yes	
	private String phone;//	PHONE_NUMBER	VARCHAR2(20 BYTE)	Yes	
	private String address;//	ADDRESS_DETAIL	VARCHAR2(255 BYTE)	Yes	
	private Date enrollDate;//	JOINED_AT	DATE	No	"SYSDATE	"
	private int points;//	POINTS	NUMBER(10,0)	No	"0	"
	private String isDelete;//	IS_DELETED	VARCHAR2(1 BYTE)	No	"'N'	"
	private Date createAt;//	CREATED_AT	DATE	No	"SYSDATE	"
	private Date updateAt;//	UPDATED_AT	DATE	No	"SYSDATE	"
	private String category;//	EXPERTISE	VARCHAR2(255 BYTE)	Yes	
	private String portUrl;//	PORTFOLIO_URL	VARCHAR2(500 BYTE)	Yes	

}
