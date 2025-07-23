package com.btw09.buyyourbrain.escros.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btw09.buyyourbrain.escros.model.dao.EscrosDAO;

@Service
public class EscrosServiceImpl implements EscrosService {

	private final EscrosDAO dao;
	
	public EscrosServiceImpl(EscrosDAO dao) {
		this.dao = dao;
	}
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//에스크로 데이터 생성 로직
	@Override
	public int createEscrowByContractId(int contractId, int totalValue) {
		// TODO Auto-generated method stub
		return dao.createEscrowByContractId(sqlSession, contractId, totalValue  );
	}
	
	

}
