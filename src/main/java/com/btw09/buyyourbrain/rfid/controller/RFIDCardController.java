package com.btw09.buyyourbrain.rfid.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btw09.buyyourbrain.member.service.MemberService;
import com.btw09.buyyourbrain.member.vo.MemberSHK;
import com.btw09.buyyourbrain.member.vo.Worker;
import com.btw09.buyyourbrain.rfid.model.dto.RFIDrepDTO;
import com.btw09.buyyourbrain.rfid.model.service.RFIDCardService;
import com.btw09.buyyourbrain.rfid.model.vo.RFIDCard;

@Controller
@RequestMapping("/rfid-cards")
public class RFIDCardController {

    private final RFIDCardService rfidCardService;
    private  final MemberService memService;

    public RFIDCardController(RFIDCardService rfidCardService, MemberService memService) {
        this.rfidCardService = rfidCardService;
        this.memService = memService;
    }
    
    //0. RFID_워커 매치
    /**
     * @return
     * /rfid-cards/{rfid}/assign 이런 매핑 주소 형태로 바꿔야 함 
     * 지금은 테스트용이라 07.16
     */
    @GetMapping("/assign")
    public String forwardMatchForm(Model model) {
    	
    	System.out.println("매칭 폼으로 이동");
    	List<RFIDCard> cards = list();
    	
    	List<RFIDCard> activeList = new ArrayList<>();
    	
    	
    	//작동 여부가 Y 인 카드만을 activeList에 삽입
    	for (RFIDCard card : cards) {
    		if ("Y".equals(card.getIsActive())) {
    			
    			activeList.add(card);
				
			}
			
		}
    	
    	List<Worker> workers = alterMemList();
    	
    	model.addAttribute("cardList", activeList);
    	model.addAttribute("workers", workers);
    	
    	return "rfid/workerCardMatch";
    	
    }
    
    /**
     * @return
     * 모든 카드키 데이터 불러오는 로직
     */
    public List<RFIDCard> list() {
        List<RFIDCard> cards = rfidCardService.findAll();
       
        return cards;
        
    }

    public List<Worker> alterMemList() {
    	List<MemberSHK> memList = memService.findAll();
    	
    	List<Worker> workerList = memService.findWorkerAll();
    	
    	if (workerList.isEmpty()) {
			
    		for (MemberSHK mem : memList) {
    			
    			memService.insertWorker(mem);
    			
    		}
    		workerList = memService.findWorkerAll();
		}
    	
    	
        return workerList;
    }
    
    @PostMapping("/matching")
    public String workerMatchRFID(int workerId, int rfidId) {
    	
    	System.out.println(workerId);
    	System.out.println(rfidId);
    	
    	memService.updateWorkerCard(workerId, rfidId);
    	
    	return "redirect:/";
    }

    // 3. 등록 처리(Create Action)
    @PostMapping("/create")
    public String create(@ModelAttribute RFIDCard rfidCard) {
        rfidCardService.create(rfidCard);
        return "redirect:/rfid-cards";
    }
   
    //임시 태깅 페이지 
    @GetMapping("/tempTaging/{readerId}/{workerId}")
    public String tempTag(@PathVariable int readerId,
                          @PathVariable int workerId,
                          Model model) {
    	//작업지 정보 구하기
    	int worksiteId = rfidCardService.getWorksiteId(readerId);
    	
    	RFIDrepDTO dto = new RFIDrepDTO();
    	
    	dto.setReaderId(readerId);
    	dto.setWorkerId(workerId);
    	dto.setWorkSiteID(worksiteId);
    	
    	model.addAttribute("response", dto);
    	
    	
    	
        return "rfid/tempTagging";
    }
    
    @PostMapping("/createWorkSession")
    @ResponseBody
    public String createWorkSession(@RequestParam("readerId") int readerId,
    								@RequestParam("workerId") int workerId,
    								@RequestParam("workSiteID") int workSiteID
    								) {
    	
    	RFIDrepDTO dto = new RFIDrepDTO();
    	
    	System.out.println(readerId);
    	System.out.println(workerId);
    	System.out.println(workSiteID);
    	
    	dto.setReaderId(readerId);
    	dto.setWorkerId(workerId);
    	dto.setWorkSiteID(workSiteID);
    	
    	int result = rfidCardService.insertWorkSession(dto);
    	
    	if (result > 0) {

    		return "success";
		}
    	
    	return "fail";
    }

}

