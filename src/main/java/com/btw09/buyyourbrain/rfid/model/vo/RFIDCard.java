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
//    WORKER_ID
    private int workerId; // 할당 워커 이름(미할당시 null)
//    ISSUED_AT
    private Date issuedAt;

}
