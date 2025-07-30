package com.btw09.buyyourbrain.auction.model.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuctionRequest {
	private int auctionRequestId;          // 경매 요청 고유 번호
    private int userId;                    // 요청자(회원) ID
    private int regionId;                  // 지역 ID
    private String auctionType;            // 경매 형태
    private String requestType;            // 요청 형태
    private String title;                  // 견적 제목
    private String description;            // 상세 내용
    private String status;                 // 진행 상태
    private String isDirect;               // 즉시 낙찰 여부 (Y/N)
    private String applyType;              // 지원 형태 (개인/팀)
    private LocalDate startDate;           // 업무 시작일
    private LocalDate dueDate;             // 납기 희망일
    private Integer startPrice;            // 경매 시작 가격
    private Integer endPrice;              // 즉시 낙찰 가격
    private String auctionStartDayStr;
    private String auctionStartTimeStr;
    private String auctionEndDayStr;
    private String auctionEndTimeStr;
    private LocalDateTime auctionStartDay;     // 경매 시작일
    private LocalDateTime auctionEndDay;   // 경매 종료일 (timestamp)
    private LocalDateTime createdAt;       // 생성일시
    private LocalDateTime updatedAt;       // 수정일시
    private Integer viewCount;             // 조회수
    private Integer bidCount;              // 입찰 수
    private int serviceId;                 // 카테고리 ID
    private int selectedExpertId;          // 낙찰된 고수 ID
}
