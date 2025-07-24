package com.btw09.buyyourbrain.escros.model.service;

import java.util.List;

import com.btw09.buyyourbrain.escros.model.dto.EscrowViewDTO;
import com.btw09.buyyourbrain.escros.model.vo.Escrow;

public interface EscrosService {
	
	// 에스크로 데이터 생성 로직 
	int createEscrowByContractId(int contractId, int totalValue);
	
	//에스크로 데이터를 전부 가져옴
	List<Escrow> findAll();
	
//	에스크로 아이디를 통해 에스크로 뷰 dto를 구하는 로직 
	EscrowViewDTO getEscrowViewById(int escrowId);
	
	//에스크로 상태변경 held -> PARTIAL_RELEASE 
	int updateStatusDP(int escrowId, int balance);
	
	//에스크로 환불 로직 (?) -> REFUNDED
	int escrosRefund(int escrowId);

}
