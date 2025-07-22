package com.btw09.buyyourbrain.chat.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AuctionChatVO {
    private int roomNo;                 // 경매 채팅방 번호 (PK이자 FK)
    private String auctionItemName;     // 경매 품목 또는 서비스의 제목
    private String auctionDescription;  // 경매 품목에 대한 상세 설명
    private int sellerUserNo;           // 경매를 생성한 판매자(일반 유저)의 USER_NO
    private String sellerUserId;        // 편의상 추가: 판매자 ID (DB 컬럼 아님, 조인으로 가져올 값)
    private int startPrice;             // 경매 시작 시 설정된 초기 입찰 가격
    private int reasonablePrice;        // 판매자가 설정한 즉시 구매 가능 가격 (적정가)
    private Date startTime;             // 경매 시작 시간
    private Date endTime;               // 경매 종료 예정 시간
    private int currentBidPrice;        // 현재까지의 최고 입찰 가격
    private Integer winningBidderUserNo; // 최종 낙찰자(고수 유저)의 USER_NO (NULL 가능)
    private String winningBidderUserId; // 편의상 추가: 낙찰자 ID (DB 컬럼 아님, 조인으로 가져올 값)
    private String auctionStatus;       // 경매의 현재 상태
    private Date lastExtensionDate;     // 마지막 경매 기간 연장 일자
    private int extensionCount;         // 경매 기간 연장 횟수
}