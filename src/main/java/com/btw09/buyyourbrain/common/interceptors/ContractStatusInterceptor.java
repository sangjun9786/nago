package com.btw09.buyyourbrain.common.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import com.btw09.buyyourbrain.contracts.model.service.ContractsService;
import com.btw09.buyyourbrain.contracts.model.vo.Contracts;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ContractStatusInterceptor implements HandlerInterceptor {

    private final ContractsService contractService;

    public ContractStatusInterceptor(ContractsService contractService) {
        this.contractService = contractService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String contractIdParam = request.getParameter("contractId");
        if (contractIdParam != null) {
            int contractId = Integer.parseInt(contractIdParam);
            Contracts contract = contractService.getContractById(contractId);
            String status = contract.getStatus();
            // NOT_STARTED 또는 PENDING_WORKER만 허용
            if (!("NOT_STARTED".equals(status) || "PENDING_WORKER".equals(status))) {
                response.sendRedirect("/boot/contracts/paymentStatus?contractId=" + contractId);
                return false;
            }
        }
        return true;
    }
}
