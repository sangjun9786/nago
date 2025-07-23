package com.btw09.buyyourbrain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.btw09.buyyourbrain.common.interceptors.ContractAccessInterceptor;
import com.btw09.buyyourbrain.common.interceptors.ContractStatusInterceptor;
import com.btw09.buyyourbrain.contracts.model.service.ContractsService;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private ContractsService contractService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 1. 로그인 인증 인터셉터
//        registry.addInterceptor(new AuthInterceptor())
//        .addPathPatterns("/contracts/**");

        // 2. 계약 접근권한 인터셉터
        registry.addInterceptor(new ContractAccessInterceptor(contractService))
            .addPathPatterns("/contracts/createAndSend/succeed"); // 또는 권한 체크 필요한 url
    
        //3. 워커 pending 접근권한 인터셉터
        registry.addInterceptor(new ContractStatusInterceptor(contractService))
        .addPathPatterns("/contracts/workerPending"); // 워커 서명대기 등 대기 페이지 경로
    }
}

