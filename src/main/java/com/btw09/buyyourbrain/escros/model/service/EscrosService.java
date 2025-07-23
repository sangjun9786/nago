package com.btw09.buyyourbrain.escros.model.service;

public interface EscrosService {
	
	// 에스크로 데이터 생성 로직 
	int createEscrowByContractId(int contractId, int totalValue);

}
