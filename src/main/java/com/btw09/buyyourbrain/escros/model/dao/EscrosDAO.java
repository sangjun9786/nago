package com.btw09.buyyourbrain.escros.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.btw09.buyyourbrain.escros.model.vo.Escrow;

@Repository
public class EscrosDAO {

	
	
	public int createEscrowByContractId(SqlSessionTemplate sqlSession, int contractId, int totalValue) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<>();
		
		param.put("contractId", contractId);
		param.put("totalValue", totalValue);
		
		return sqlSession.insert("escrosMapper.createEscrowByContractId", param);
	}

	public List<Escrow> findAll(SqlSessionTemplate sqlSession) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("escrosMapper.findAll");
	}

	public Escrow getEscrowById(SqlSessionTemplate sqlSession, int escrowId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("escrosMapper.getEscrowById", escrowId);
	}

	public int updateStatusDP(SqlSessionTemplate sqlSession, int escrowId, int balance) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<>();
		
		param.put("escrowId", escrowId);
		
//		int balance 
		param.put("balance", balance);
		
		return sqlSession.update("escrosMapper.updateStatusDP", param);
	}

	public int escrosRefund(SqlSessionTemplate sqlSession, int escrowId) {
		// TODO Auto-generated method stub
		return sqlSession.update("escrosMapper.escrosRefund", escrowId);
	}
	
	
	

}
