package com.btw09.buyyourbrain.rfid.controller;

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

import com.btw09.buyyourbrain.rfid.model.dto.RFIDrepDTO;
import com.btw09.buyyourbrain.rfid.model.service.RFIDCardService;
import com.btw09.buyyourbrain.rfid.model.vo.RFIDCard;

@Controller
@RequestMapping("/rfid-cards")
public class RFIDCardController {

    private final RFIDCardService rfidCardService;

    public RFIDCardController(RFIDCardService rfidCardService) {
        this.rfidCardService = rfidCardService;
    }
    
    //0. RFID_워커 매치
    /**
     * @return
     * /rfid-cards/{rfid}/assign 이런 매핑 주소 형태로 바꿔야 함 
     * 지금은 테스트용이라 07.16
     */
    @GetMapping("/assign")
    public String forwardMatchForm() {
    	
    	System.out.println("매칭 폼으로 이동");
    	
    	return "rfid/workerCardMatch";
    	
    }
    // 1. 목록 조회(Read)
    @GetMapping
    public String list(Model model) {
        List<RFIDCard> cards = rfidCardService.findAll();
        model.addAttribute("cards", cards);
        return "rfid/list";
    }

    // 2. 등록 폼(Create Form)
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("rfidCard", new RFIDCard());
        return "rfid/create";
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

