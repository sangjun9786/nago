package com.btw09.buyyourbrain.escros.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btw09.buyyourbrain.company.model.vo.PartnerCorp;
import com.btw09.buyyourbrain.company.service.PartnerService;
import com.btw09.buyyourbrain.contracts.model.dto.ContracResDTO;
import com.btw09.buyyourbrain.contracts.model.service.ContractsService;
import com.btw09.buyyourbrain.contracts.model.vo.Contracts;
import com.btw09.buyyourbrain.escros.model.dto.EscrowViewDTO;
import com.btw09.buyyourbrain.escros.model.service.EscrosService;
import com.btw09.buyyourbrain.escros.model.vo.Escrow;
import com.btw09.buyyourbrain.escros.model.vo.EscrowLog;
import com.btw09.buyyourbrain.member.vo.MemberSHK;
import com.btw09.buyyourbrain.worksession.model.vo.MileStone;

@Controller
@RequestMapping("/escros")
public class EscroMasterController {
	
	
	private final EscrosService escroServ;
	private final ContractsService contractServ;
	private final PartnerService partnServ;

    public EscroMasterController(EscrosService escroServ, ContractsService contractServ, PartnerService partnServ) {
        this.escroServ = escroServ;
        this.contractServ = contractServ;
        this.partnServ = partnServ;
    }
    
    //0. 에스크로 결제 페이지 조회
    /**
     * @return
     * 사용자와 연결된 "체결됨" 상태의 계약이 있을 경우, 그 계약에 대한 결제 페이지를 화면에 보여줌
     */
	@GetMapping("/paymentReady")
	public String paymentReadyForm(int contractId, Model model) {

		ContracResDTO respondobj = new ContracResDTO();

//		1. 프로젝트 명 set
		respondobj.setProjectName("웹사이트 UI 리디자인");
		
		int result = contractServ.updateProjectName(contractId, "웹사이트 UI 리디자인");

//		2.계약 객체 불러오기 
		Contracts c = contractServ.getContractById(contractId);

		respondobj.setContractId(c.getContractId());

//		워커 id
		int workerId = c.getWorkerId();
//		계약금액
		int contractPay = c.getContractPrice();
//		 멤버
		MemberSHK worker = contractServ.getObjectById(workerId);

//		3. 워커이름 set
		respondobj.setWorkerName(worker.getUserName());

//		4. 계약금을 기반으로 수수료 총 결제금액 구하기
		int chargePay = (int) (contractPay * 0.05);
		int totalPay = contractPay + chargePay;

		respondobj.setAmountValue(contractPay);
		respondobj.setChargeValue(chargePay);
		respondobj.setTotalValue(totalPay);

//		5. model에 값 넣어주기
		model.addAttribute("response", respondobj);

		return "escros/escrosPayRequest";

	}
    
    // 0_2. 에스크로 관리자 관리 페이지 조회(일단 화면만 보는 용)
    @GetMapping("/adminPage")
    public String viewAdminPage(Model model) {
    	
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
    	
    	
    	model.addAttribute("esList", esList);
    	model.addAttribute("finalDeposit", finalDeposit);
    	
    	return "escros/escroAdminForm";
    }
    
    @PostMapping("/payment")
    @ResponseBody
    public String  createEscrowPayment(@RequestParam("contractId") int contractId, 
									   @RequestParam("totalValue") int totalValue ) {
       
    	EscrowViewDTO dto = new EscrowViewDTO();
    	
    	dto.setContractId(contractId);
    	dto.setTotalAmount(totalValue);
    	
    	// 에스크로 & 로그 생성 로직 (ex: DB insert, 결제 연동 등)
        try {
        	int result = escroServ.createEscrowByContractId(dto); // 서비스 계층 구현
        	
        	int escrowId = dto.getEscrowId();
        	
        	int result2 = escroServ.createEscrowLogHold(dto);
        	
        	
        	//           계약 상태 변경 로직 필요
        	return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

    
    // 1_2. 에스크로 결제 성공 로직 폼 메소드
    @GetMapping("/paymentSuccess")
    public String paymentSuccessForm() {
    	

    	
    	
    	return "escros/escrosPaySuccess";
    }
   

    @GetMapping("/detail/{escrowId}")
    public String showEscrowDetail(@PathVariable int escrowId, Model model) {
        
    	EscrowViewDTO escrow = escroServ.getEscrowViewById(escrowId); // DTO 조회
        
    	//계약 id를 통해 마일스톤 구하기 
    	List<MileStone> mileList = escroServ.getMileListByConId(escrow.getContractId());
    	
    	//보낼 마일스톤 구하기
    	MileStone nextPayMilestone = null;
    	
    	for (MileStone m : mileList) {
    	    if ("complete".equals(m.getStatus()) && "N".equals(m.getIsPaid())) {
    	        if (nextPayMilestone == null || m.getSeqNo() < nextPayMilestone.getSeqNo()) {
    	            nextPayMilestone = m;
    	        }
    	    }
    	}

    	
    	// 찾은 마일스톤을 모델에 담기 (null 체크)
    	if (nextPayMilestone == null) {
    		nextPayMilestone = new MileStone();
    	}
    	model.addAttribute("milestone", nextPayMilestone);
    	model.addAttribute("escrow", escrow);
        
    	return "escros/escrowDetail"; // 위 html 파일 이름
    }
    
    /**
     * @param escrowId
     * @param price
     * @return
     * 선금 버튼 클릭시 호출되는 api
     */
    @PostMapping("/payment/downPay")
    @ResponseBody
    public String updateStatusDownPay(@RequestParam("escrowId") int escrowId,
    								  @RequestParam("price") int price) {
    	
    	EscrowViewDTO escrow = escroServ.getEscrowViewById(escrowId);
    	
    	int balance = escrow.getTotalAmount() - price;
    	
    	int updateResult = escroServ.updateStatusDP(escrowId, balance);
    	int createResult = escroServ.createEscrowLogDownpay(escrowId, price);
    	
    	if (updateResult > 0 && createResult > 0) {
			
    		return "success";
		}
    	return "fail";
    }
    
    @PostMapping("/payment/refund")
    @ResponseBody
    public String refundEscros(@RequestParam("escrowId") int escrowId
    								  ) {
    	
    	EscrowViewDTO escrow = escroServ.getEscrowViewById(escrowId);
    	
    	int refundPrice = escrow.getTotalAmount();
    	
    	//에스크로 상태 환불 변경
    	int updateResult = escroServ.escrosRefund(escrowId);
    	int createResult = escroServ.createEscrowLogRefund(escrowId, refundPrice);
    	
    	if (updateResult > 0 && createResult > 0) {
			
    		return "success";
		}
    	return "fail";
    }
    
    @PostMapping("/payment/milePay")
    @ResponseBody
    public String milePayEscros(
    							@RequestParam("escrowId") int escrowId,
    							@RequestParam("milestoneId") int milestoneId,
								@RequestParam("price") int price
    							) {
//    	에스크로 업데이트 
    	EscrowViewDTO escrow = escroServ.getEscrowViewById(escrowId);
    	int balance = escrow.getTotalAmount() - price;
    	
    	int updateEscroResult = 0;
    	int createLogResult = 0;
    	int updateConResult = 0;
    	
    	
    	if (balance != 0) {
    		
    		updateEscroResult = escroServ.updateStatusDP(escrowId, balance);
    		createLogResult = escroServ.createEscrowLogMilePay(escrowId, price);
			
		}else {
			
			updateEscroResult = escroServ.updateStatusFinal(escrowId);
			createLogResult = escroServ.createEscrowLogFinalPay(escrowId, price);
			updateConResult = contractServ.updateStatusExpire(escrow.getContractId());
			
		}
    	
//    	마일스톤 업데이트
    	int updateMileResult = escroServ.updateMilePay(milestoneId);
    	
    	return "success";
    }
	

}
