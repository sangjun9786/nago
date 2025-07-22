package com.btw09.buyyourbrain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

	    @GetMapping("/admin")
	    public String admin() {
	        return "forward:/admin/index.html"; // React 앱의 진입점
	    }
	    //http://localhost:8083/boot/admin 리액트 앱으로의 연결점. 
	}
