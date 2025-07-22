//package com.btw09.buyyourbrain;
//
//import java.awt.Desktop;
//import java.net.URI;
//
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BrowserLauncher {
//	//스프링 부트 앱 실행시
//	//자동으로 브라우저 띄우기 by 상준
//	@EventListener(ApplicationReadyEvent.class)
//	public void openBrowser() {
//	    try {
//	        String url = "http://localhost:8083/boot";
//	        Runtime.getRuntime().exec("cmd /c start " + url);
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	}
//}
