package com.btw09.buyyourbrain.escros.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EscrosDAO {

	
	
	public int createEscrowByContractId(SqlSessionTemplate sqlSession, int contractId, int totalValue) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<>();
		
		param.put("contractId", contractId);
		param.put("totalValue", totalValue);
		
		return sqlSession.insert("escrosMapper.createEscrowByContractId", param);
	}

}
