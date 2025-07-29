package com.btw09.buyyourbrain.escros.model.service;

import java.util.List;

import com.btw09.buyyourbrain.escros.model.dto.EscrowViewDTO;
import com.btw09.buyyourbrain.escros.model.vo.Escrow;
import com.btw09.buyyourbrain.escros.model.vo.EscrowLog;
import com.btw09.buyyourbrain.worksession.model.vo.MileStone;
import com.btw09.buyyourbrain.worksession.model.vo.WorkSession;

public interface EscrosService {
	
	// 에스크로 데이터 생성 로직 
	int createEscrowByContractId(EscrowViewDTO dto);
	
	//에스크로 데이터를 전부 가져옴
	List<Escrow> findAll();
	
//	에스크로 아이디를 통해 에스크로 뷰 dto를 구하는 로직 
	EscrowViewDTO getEscrowViewById(int escrowId);
	
	//에스크로 상태변경 held -> PARTIAL_RELEASE 
	int updateStatusDP(int escrowId, int balance);
	
	//에스크로 환불 로직 (?) -> REFUNDED
	int escrosRefund(int escrowId);
	
	//에스크로 로그 생성 (예치)	
	int createEscrowLogHold(EscrowViewDTO dto);
	
	// 상태별 에스크로 로그 구하기
	List<EscrowLog> findEscrowLogStat(String string);

	List<EscrowLog> findEscrowLogStatEx(String string);
	
	// 에스크로 로그 생성 (선금)
	int createEscrowLogDownpay(int escrowId, int price);
	// 에스크로 로그 생성 (환불)
	int createEscrowLogRefund(int escrowId, int refundPrice);
	// 에스크로 로그 생성 (마일스톤 비용)
	int createEscrowLogMilePay(int escrowId, int price);
	
	//계약 id를 통해 작업세션 구하기 
	WorkSession getWsByContractId(int contractId);

	//계약 id를 통해 마일스톤 구하기
	List<MileStone> getMileListByConId(int contractId);
//	에스크로 로그 생성 (마지막 릴리즈)
	int createEscrowLogFinalPay(int escrowId, int price);

	int updateStatusFinal(int escrowId);
	// 마일스톤 지불여부 업데이트
	int updateMilePay(int milestoneId);

}
