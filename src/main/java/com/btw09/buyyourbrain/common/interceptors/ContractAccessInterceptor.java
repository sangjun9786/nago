package com.btw09.buyyourbrain.common.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import com.btw09.buyyourbrain.contracts.model.service.ContractsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ContractAccessInterceptor implements HandlerInterceptor {
    private final ContractsService contractService;

    // 생성자 주입 (Bean으로 등록하면 Spring DI 가능)
    public ContractAccessInterceptor(ContractsService contractService) {
        this.contractService = contractService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // GET 파라미터로 contractId 추출
        String contractIdParam = request.getParameter("contractId");
        if (contractIdParam != null) {
            int contractId = Integer.parseInt(contractIdParam);
            // 로그인 유저 정보 추출
            int loginUserId =  (int) request.getSession().getAttribute("loginUserId"); // 방식에 따라 다름

            // contractId 접근 권한 체크!
            boolean hasAccess = contractService.checkContractAccess(contractId, loginUserId);

            if (!hasAccess) {
                response.sendRedirect("/boot/error/forbidden"); // 혹은 403 에러코드 전송
                return false;
            }
        }
        // contractId 파라미터 없는 경우엔 통과(또는 경로에 따라 분기)
        return true;
    }
}

