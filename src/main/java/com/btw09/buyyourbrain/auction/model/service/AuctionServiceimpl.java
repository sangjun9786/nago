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
	
	//경매등록
    @Override
    public int AuctionRegister(AuctionRequest ar) {
        return Dao.AuctionRegister(sqlSession,ar);
    }
    
    //경매리스트
    @Override
    public List<AuctionRequest> AuctionList() {
        return Dao.AuctionList(sqlSession);
    }

    //경매상세페이지
	@Override
	public AuctionRequest selectAuctionDetail(int auctionRequestId) {
		// TODO Auto-generated method stub
		return Dao.selectAuctionDetail(sqlSession, auctionRequestId);
	}
    
    
}
