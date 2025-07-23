package com.btw09.buyyourbrain.company.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btw09.buyyourbrain.company.dao.PartnerDAO;
import com.btw09.buyyourbrain.company.model.vo.PartnerCorp;

@Service
public class PartnerServiceImpl implements PartnerService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final PartnerDAO dao;
	
	public PartnerServiceImpl(PartnerDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public PartnerCorp findSelect(int i) {
		// TODO Auto-generated method stub
		return dao.findSelect(sqlSession, i);
	}

}
