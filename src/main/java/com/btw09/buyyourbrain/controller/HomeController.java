package com.btw09.buyyourbrain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.btw09.buyyourbrain.company.model.vo.PartnerCorp;
import com.btw09.buyyourbrain.company.service.PartnerService;
import com.btw09.buyyourbrain.member.service.MemberService;
import com.btw09.buyyourbrain.member.vo.MemberSHK;

@Controller
public class HomeController {
	
	private final MemberService memserv;
	private final PartnerService partnerserv;
	
	public HomeController(MemberService memserv, PartnerService partnerserv) {
		this.memserv = memserv;
		this.partnerserv = partnerserv;
	}
	

    
    @GetMapping("/auction/close")
    public String init(Model model) {
    	
//    	경매 주최자 컴퍼니를 구하는 로직
    	PartnerCorp holder = partnerserv.findSelect(3);
    	
//    	낙찰된 워커를 구하는 로직이 들어가야 함
    	MemberSHK selectedWorker = memserv.findSelect(4);
    	
    	model.addAttribute("partner", holder);
    	model.addAttribute("worker", selectedWorker);
    	
    	
    	// model에 넣어주는 값 : 워커, 주최자
        return "main/close"; // templates/main/test.html을 찾아감
    }
    
    @GetMapping("/auction/closeWorker")
    public String workerInit(Model model) {
    	
//    	경매 주최자 컴퍼니를 구하는 로직
    	PartnerCorp holder = partnerserv.findSelect(2);
    	
//    	낙찰된 워커를 구하는 로직이 들어가야 함
    	MemberSHK selectedWorker = memserv.findSelect(4);
    	
    	model.addAttribute("partner", holder);
    	model.addAttribute("worker", selectedWorker);
    	
    	 return "main/worker"; 
    	
    }
    
    @RequestMapping("/")
    public String MainHome() {
    	
    	return "main/main";
    }

}
