package com.btw09.buyyourbrain.rfid.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RFIDrepDTO {
	
	private int readerId;
	private int workerId;
//	작업지
	private int  workSiteID;
	
	

}
