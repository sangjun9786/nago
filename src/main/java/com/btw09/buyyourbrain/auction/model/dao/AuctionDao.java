package com.btw09.buyyourbrain.auction.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.btw09.buyyourbrain.auction.model.vo.AuctionRequest;

@Repository
public class AuctionDao {
    
	//경매 등록
	public int AuctionRegister(SqlSessionTemplate sqlSession, AuctionRequest ar) {
		// TODO Auto-generated method stub
		return sqlSession.insert("AuctionMapper.AuctionRegister",ar);
	}
	
	//경매 리스트 조회
	public List<AuctionRequest> AuctionList(SqlSessionTemplate sqlSession) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("AuctionMapper.selectAuctionList");
	}

//	public AuctionRequest AuctionDetail(SqlSessionTemplate sqlSession, int auctionRequestId) {
//		// TODO Auto-generated method stub
//		return sqlSession.;
//	}
	
}
