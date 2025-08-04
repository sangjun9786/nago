package com.btw09.buyyourbrain.auction.model.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btw09.buyyourbrain.auction.model.vo.AuctionRequest;

@Repository
public interface AuctionService {
	//경매등록
    int AuctionRegister(AuctionRequest ar);
    
    //경매 리스트
    List<AuctionRequest>AuctionList();
    
    //상세 페이지
    AuctionRequest selectAuctionDetail(int auctionRequestId);

}
