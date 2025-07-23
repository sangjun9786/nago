package com.btw09.buyyourbrain.worksession.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/work")
public class WorkSession {
	
	
	@GetMapping("/sessionView")
	public String forwardWorkSessionFomr() {
		
		return "worksession/workerSession";
	}

}
