package com.btw09.buyyourbrain.contracts.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.btw09.buyyourbrain.contracts.model.dto.ContracReqtDTO;
import com.btw09.buyyourbrain.contracts.model.vo.Contracts;
import com.btw09.buyyourbrain.member.vo.MemberSHK;

@Repository
public class ContractsDAO {

	public MemberSHK getObjectById(SqlSessionTemplate sqlSession, int workerId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.findSelect", workerId );
	}

	public int insertContracts(SqlSessionTemplate sqlSession, ContracReqtDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.insert("contractsMapper.insertContracts", dto);
	}

	public Contracts getContractById(SqlSessionTemplate sqlSession, int contractId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("contractsMapper.getContractById", contractId);
	}

//	계약상태 변경 1단계
	public int updateStatusPW(SqlSessionTemplate sqlSession, int contractId) {
		// TODO Auto-generated method stub
		return sqlSession.update("contractsMapper.updateStatusPW", contractId);
	}

	public int updateStatusPro(SqlSessionTemplate sqlSession, int contractId) {
		// TODO Auto-generated method stub
		return sqlSession.update("contractsMapper.updateStatusPro", contractId);
	}

}
