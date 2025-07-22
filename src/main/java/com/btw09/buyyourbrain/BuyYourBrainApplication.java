package com.btw09.buyyourbrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class BuyYourBrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyYourBrainApplication.class, args);
	}
	@GetMapping("/")
	public String index() {
	    return "redirect:/boot/"; // 혹은 /boot/home 등
	    ////http://localhost:8083/boot/ 로 접근시 index.html로 갑니다. 
	    //// 여기서의 index 는 스프링 부트 template 폴더의 index로 접근함.
	    //// 7월 11일 오전 10시58분 정상작동 확인

	}
	
}
