package com.btw09.buyyourbrain.contracts.model.service;

import com.btw09.buyyourbrain.contracts.model.dto.ContracReqtDTO;
import com.btw09.buyyourbrain.contracts.model.vo.Contracts;
import com.btw09.buyyourbrain.member.vo.MemberSHK;

public interface ContractsService {
	
//	memerID로 객체가져오기 
	MemberSHK getObjectById(int workerId);
	
// Contracts data 생성
	int insertContracts(ContracReqtDTO dto);
// 계약객체를 불러오는 메소드
	Contracts getContractById(int contractId);
	
//	 contractId 접근 권한 체크 메소드
	boolean checkContractAccess(int contractId, int loginUserId);
//	계약 상태 변경 1단계
	int updateStatusPW(int contractId);
	
// 계약 상태 변경 2단계
	int updateStatusPro(int contractId);

}
