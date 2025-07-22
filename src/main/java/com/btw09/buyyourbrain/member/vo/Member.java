package com.btw09.buyyourbrain.member.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	private int userNo; // USER_NO
	private String userId; // USER_ID
	private String userPwd; // PASSWORD
	private String userName; // USER_NAME
	private String email; // EMAIL
	private String phone; // PHONE_NUMBER
	private String address; // ADDRESS_DETAIL 
	private Date enrollDate;  // JOINED_AT
	private int points; // POINTS
	private String isDelete; // IS_DELETED
	private Date createAt; // CREATED_AT
	private Date updateAt; // UPDATED_AT
	

}
