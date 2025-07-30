package com.btw09.buyyourbrain.rfid.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkSite {
	
//	WORKSITE_ID	NUMBER
	private int worksiteId;
//	COMPANY_ID	NUMBER
	private int companyId;
//	LOCATION_DETAIL	VARCHAR2(255 BYTE)
	private String locationDetail;

}
