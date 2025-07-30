package com.btw09.buyyourbrain.auction.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.btw09.buyyourbrain.auction.model.service.AuctionService;
import com.btw09.buyyourbrain.auction.model.vo.AuctionRequest;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auction")
public class AuctionController {
	
	@Autowired
    private AuctionService service;

	
	// 경매 등록 화면 요청
    @GetMapping("/register")
    public String AuctionRegister() {
        return "auction/auctionRegister"; // templates/auction/auctionRegister.html
    }
    
    @PostMapping("/register")
    public String registerAuction(@ModelAttribute AuctionRequest ar,
                                  HttpSession session,
                                  Model model) {
//        // 1. 세션에서 로그인 유저 번호 가져오기
//        int userNo = (int) session.getAttribute("loginUserNo");
//        if (userNo == 0) {
//            model.addAttribute("errorMsg", "로그인이 필요합니다.");
//            return "common/errorPage";
//        }
        
        // 1. 세션에서 로그인 유저 번호 가져오기
        Integer userNoObj = (Integer) session.getAttribute("loginUserNo");

        // 🔧 로그인 기능 미구현 시 테스트용 값 강제 세팅
        if (userNoObj == null) {
            userNoObj = 6;
            session.setAttribute("loginUserNo", userNoObj);
        }

        int userNo = userNoObj;

     // 2. 날짜/시각 파라미터 VO에서 추출 (VO에 String 필드로 있어야 함)
        String auctionStartDayStr = ar.getAuctionStartDayStr();
        String auctionStartTimeStr = ar.getAuctionStartTimeStr();
        String auctionEndDayStr = ar.getAuctionEndDayStr();
        String auctionEndTimeStr = ar.getAuctionEndTimeStr();

        // 3. 시각 기본값 처리
        if (auctionStartTimeStr == null || auctionStartTimeStr.isBlank()) {
            auctionStartTimeStr = "00:00";
        }
        if (auctionEndTimeStr == null || auctionEndTimeStr.isBlank()) {
            auctionEndTimeStr = "23:59";
        }

        // 4. LocalDateTime 조합 후 VO의 필드에 실제 세팅
        if (auctionStartDayStr != null && !auctionStartDayStr.isBlank()) {
            ar.setAuctionStartDay(LocalDateTime.parse(auctionStartDayStr + "T" + auctionStartTimeStr));
        }
        if (auctionEndDayStr != null && !auctionEndDayStr.isBlank()) {
            ar.setAuctionEndDay(LocalDateTime.parse(auctionEndDayStr + "T" + auctionEndTimeStr));
        }

        
        // 2. 필수 값만 설정 (테이블 기본값 활용)
        ar.setUserId(userNo);
        ar.setStatus("진행중");
        System.out.println("📥 경매 등록 컨트롤러 진입"); // ★ 꼭 확인

        // 3. 등록 처리
        int result = service.AuctionRegister(ar);
        System.out.println("등록 결과: " + result); // 1이 아니면 실패

        // 4. 결과 처리
        if (result > 0) {
            session.setAttribute("alertMsg", "경매 요청이 등록되었습니다.");
            return "redirect:/auction/list";
        } else {
            model.addAttribute("errorMsg", "경매 요청 등록 실패");
            return "common/errorPage";
        }
    }
    
    // 최초 진입용 - 전체 페이지
    @GetMapping("/list")
    public String AuctionList(Model model) {
        List<AuctionRequest> list = service.AuctionList();
        model.addAttribute("auctionList", list);
        return "auction/auctionlist";
    }
}
