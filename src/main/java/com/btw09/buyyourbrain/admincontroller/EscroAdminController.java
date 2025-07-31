package com.btw09.buyyourbrain.admincontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btw09.buyyourbrain.company.model.vo.PartnerCorp;
import com.btw09.buyyourbrain.company.service.PartnerService;
import com.btw09.buyyourbrain.contracts.model.service.ContractsService;
import com.btw09.buyyourbrain.contracts.model.vo.Contracts;
import com.btw09.buyyourbrain.escros.model.dto.EscrowViewDTO;
import com.btw09.buyyourbrain.escros.model.service.EscrosService;
import com.btw09.buyyourbrain.escros.model.vo.Escrow;
import com.btw09.buyyourbrain.escros.model.vo.EscrowLog;
import com.btw09.buyyourbrain.member.vo.MemberSHK;

@RestController
@RequestMapping("/api/admin")
public class EscroAdminController {
	
	private final EscrosService escroServ;
	private final ContractsService contractServ;
	private final PartnerService partnServ;
	
    public EscroAdminController(EscrosService escroServ, ContractsService contractServ, PartnerService partnServ) {
        this.escroServ = escroServ;
        this.contractServ = contractServ;
        this.partnServ = partnServ;
    }
	
	@GetMapping("/escrows")
	public Map<String, Object> getEscrowAdminPageData() {
		
//    	1.에스크로 데이터를 모두 불러오기 
    	List<Escrow> escrowList = escroServ.findAll();
    	List<EscrowViewDTO> esList = new ArrayList<>();

    	for (Escrow esc : escrowList) {
    		
    		//계약 구하고
    		Contracts contract = contractServ.getContractById(esc.getContractId());
//    		워커 id
    		int workerId = contract.getWorkerId();
//    		회사 id
    		int companyId = contract.getCompanyId();
    		
    		MemberSHK worker = contractServ.getObjectById(workerId);
    		PartnerCorp company = partnServ.findSelect(companyId);
//    		추가 필드에 관한 데이터를 불러옴 
    		String projectName = contract.getProjectName();
    		String workerName = worker.getUserName();
    		String companyName = company.getName();
    		
    		EscrowViewDTO dto = new EscrowViewDTO();
//    		esc 의 필드를 해당 객체의 필드에 복사하는 로직
    		BeanUtils.copyProperties(esc, dto);
//    		dto 추가 필드에 데이터 set
    		dto.setProjectName(projectName);
    		dto.setClientName(companyName);
    		dto.setWorkerName(workerName);
    		
    		esList.add(dto);
    		
    	}
    	
//    	2.에스크로 로그를 불러오기
    	
    	//총 예치금액 구하기
    	//HELD 상태의 에스크로 로그 데이터의 금액 데이터에서 이외의 상태를 뺄셈 연산함
    	List<EscrowLog> depositList = escroServ.findEscrowLogStat("HELD");
    	
    	int totalDeposit = 0;
    	
    	for (EscrowLog depositLog : depositList) {
    		
    		totalDeposit += depositLog.getPayload();
			
		}
    	
    	List<EscrowLog> outList = escroServ.findEscrowLogStatEx("HELD");
    	
    	int totalOutput = 0;
    	
    	for (EscrowLog outputLog : outList) {
    		
    		totalOutput += outputLog.getPayload();
			
		}
    	
    	int finalDeposit = totalDeposit - totalOutput;
    	
    	Map<String, Object> result = new HashMap<>();
        result.put("esList", esList);
        result.put("finalDeposit", finalDeposit);

    	
    	return result;
		
		
		
	}

}
