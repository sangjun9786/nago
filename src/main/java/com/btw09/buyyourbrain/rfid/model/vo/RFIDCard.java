package com.btw09.buyyourbrain.rfid.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RFIDCard {
//	RFID_ID
    private int rfidId;
//    UID
    private String uid;
//    IS_ACTIVE
    private String isActive; 
//    ISSUED_AT
    private Date issuedAt;

}
