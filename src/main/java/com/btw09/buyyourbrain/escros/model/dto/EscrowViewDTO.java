package com.btw09.buyyourbrain.escros.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EscrowViewDTO {
    private int escrowId;
    private int contractId;
    private String projectName; // 계약에서 가져옴
    private String workerName;  // 계약/유저에서 가져옴
    private String clientName;  // 계약/유저에서 가져옴
    private int totalAmount;
    private int releasedAmount;
    private int refundedAmount;
    private String status;
    private Date holdAt;
    private Date firstReleaseAt;
    private Date finalReleaseAt;
    private Date refundedAt;
}

