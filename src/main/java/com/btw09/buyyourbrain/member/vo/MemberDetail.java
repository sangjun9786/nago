package com.btw09.buyyourbrain.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetail {
	
	private int userNo;          // FK: USER.USER_NO
    private String profileImg;   // PROFILE_IMG
    private String intro;        // INTRO
    private String skill;        // SKILL
    private String snsUrl;       // SNS_URL
    private String workTime;     // WORK_TIME
    private String location;     // LOCATION
    private String carrer;       // CARRER

}
