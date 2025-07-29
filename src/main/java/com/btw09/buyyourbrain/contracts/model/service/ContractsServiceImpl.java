package com.btw09.buyyourbrain.contracts.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btw09.buyyourbrain.contracts.model.dao.ContractsDAO;
import com.btw09.buyyourbrain.contracts.model.dto.ContracReqtDTO;
import com.btw09.buyyourbrain.contracts.model.vo.Contracts;
import com.btw09.buyyourbrain.member.vo.MemberSHK;

@Service
public class ContractsServiceImpl implements ContractsService {

	private final ContractsDAO dao;
	
	public ContractsServiceImpl( ContractsDAO dao ) {
		this.dao = dao;
	}
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberSHK getObjectById(int workerId) {
		// TODO Auto-generated method stub
		return dao.getObjectById(sqlSession, workerId);
	}

	@Override
	public int insertContracts(ContracReqtDTO dto) {
		// TODO Auto-generated method stub
		return dao.insertContracts(sqlSession, dto);
	}

	@Override
	public Contracts getContractById(int contractId) {
		// TODO Auto-generated method stub
		return dao.getContractById(sqlSession, contractId);
	}

	/**
	 *contractId 변경시 발동하는 체크 메소드
	 */
	@Override
	public boolean checkContractAccess(int contractId, int loginUserId) {
        // 1. contractId에 해당하는 계약을 조회
        Contracts contract = dao.getContractById(sqlSession, contractId);

        if (contract == null) {
            return false; // 계약이 없으면 접근 불가
        }

        // 2. 계약의 소유자(혹은 권한자)와 로그인 유저가 일치하는지 체크
        //   (아래 예시는 클라이언트 또는 워커 중 한 쪽이 일치하면 통과하는 구조)
        return (contract.getCompanyId() == loginUserId
            || contract.getWorkerId() == loginUserId);
    }

	@Override
	public int updateStatusPW(int contractId) {
		// TODO Auto-generated method stub
		return dao.updateStatusPW(sqlSession, contractId);
	}

	@Override
	public int updateStatusPro(int contractId) {
		// TODO Auto-generated method stub
		return dao.updateStatusPro(sqlSession, contractId);
	}

	@Override
	public int updateProjectName(int contractId, String string) {
		// TODO Auto-generated method stub
		return dao.updateProjectName(sqlSession, contractId,  string);
	}

	@Override
	public int updateStatusExpire(int contractId) {
		// TODO Auto-generated method stub
		return dao.updateStatusExpire(sqlSession, contractId);
	}

}
