package com.btw09.buyyourbrain.auction.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btw09.buyyourbrain.auction.model.dao.AuctionDao;
import com.btw09.buyyourbrain.auction.model.vo.AuctionRequest;

@Service
public class AuctionServiceimpl implements AuctionService {
	
    @Autowired
    private SqlSessionTemplate sqlSession;
	
	@Autowired
    private AuctionDao Dao;

    @Override
    public int AuctionRegister(AuctionRequest ar) {
        return Dao.AuctionRegister(sqlSession,ar);
    }
    
    @Override
    public List<AuctionRequest> AuctionList() {
        return Dao.AuctionList(sqlSession);
    }
}
