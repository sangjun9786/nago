package com.btw09.buyyourbrain.commoncontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {
		
	@GetMapping("/coming-soon")
	public String comingSoonPage() {
	    return "common/coming-soon";
	}
	
	@GetMapping("/errorPage")
	public String errorPage() {
	    return "common/errorPage";
	}
}
