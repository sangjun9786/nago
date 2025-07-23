package com.btw09.buyyourbrain.contracts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.btw09.buyyourbrain.company.model.vo.PartnerCorp;
import com.btw09.buyyourbrain.company.service.PartnerService;
import com.btw09.buyyourbrain.contracts.model.dto.ContracReqtDTO;
import com.btw09.buyyourbrain.contracts.model.dto.ContracResDTO;
import com.btw09.buyyourbrain.contracts.model.service.ContractsService;
import com.btw09.buyyourbrain.contracts.model.vo.Contracts;
import com.btw09.buyyourbrain.member.vo.MemberSHK;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/contracts")
public class ContractsController {
	
	private final ContractsService contractServ;
	private final PartnerService partnServ;
	
	public ContractsController(ContractsService contractServ, PartnerService partnServ) {
		
		this.contractServ = contractServ;
		this.partnServ = partnServ;
	}
	
	/**
	 * @param dto
	 * @param model
	 * @return
	 * 
	 */
	@PostMapping("/createAndSend")
	public String createAutoContract(ContracReqtDTO dto, Model model, HttpServletRequest request ) {
		
		
//		계약 데이터 새로 생성
		int insertResultC = contractServ.insertContracts(dto);
		
		int contractId = dto.getContractId();
		
		request.getSession().setAttribute("contractId", contractId);
		
//		
		
		return "redirect:/contracts/createAndSend/success?contractId="+contractId;
	}
	
	@GetMapping("/createAndSend/success")
	public String forwardContractView(int contractId, Model model) {
		
//		계약 객체 불러오기 
		Contracts c = contractServ.getContractById(contractId);
		
//		워커 id
		int workerId = c.getWorkerId();
//		회사 id
		int companyId = c.getCompanyId();
//		계약금액
		int contractPay = c.getContractPrice();
		
		MemberSHK worker = contractServ.getObjectById(workerId);
		
		PartnerCorp company = partnServ.findSelect(companyId);
		
//		model에 값 삽입
		model.addAttribute("worker", worker);
		model.addAttribute("company", company);
		model.addAttribute("pay", contractPay);
		
		
		return "contracts/contractForm";
	}
	
	/**
	 * @param dto
	 * @param model
	 * @return
	 * 워커가 클라이언트의 서명을 대기중
	 */
	@PostMapping("/workerPending")
	public String readyClientSign(ContracReqtDTO dto, Model model, HttpServletRequest request ) {
		
//		워커 id
		int workerId = dto.getSelectedWorkerId();
//		회사 id
		int companyId = dto.getCompanyId();
//		계약금액
		int contractpay = dto.getAmountValue();
		
		MemberSHK worker = contractServ.getObjectById(workerId);
		
		PartnerCorp company = partnServ.findSelect(companyId);
		
//		계약 객체 생성 
		Contracts contract = null;
		
		int contractId = (int)request.getSession().getAttribute("contractId");
		if (contractId != 0) {
			
			contract	= contractServ.getContractById(contractId);
		}
		
//		model에 값 삽입
		model.addAttribute("worker", worker);
		model.addAttribute("company", company);
		model.addAttribute("pay", contractpay);
		model.addAttribute("contract", contract);
		
		return "contracts/workerPending";
	}
	
	
	@PostMapping("/workerSign")
	public String workerSignForm(int contractId, Model model) {
		
//		계약 객체 불러오기 
		Contracts c = contractServ.getContractById(contractId);
		
//		워커 id
		int workerId = c.getWorkerId();
//		회사 id
		int companyId = c.getCompanyId();
//		계약금액
		int contractPay = c.getContractPrice();
		
		MemberSHK worker = contractServ.getObjectById(workerId);
		
		PartnerCorp company = partnServ.findSelect(companyId);
		
//		model에 값 삽입
		model.addAttribute("worker", worker);
		model.addAttribute("company", company);
		model.addAttribute("pay", contractPay);
		model.addAttribute("contract", c);
		
		
		
		return "contracts/contractWorkerView";
		
	}
	
	
	@PostMapping("/updatePending")
	public String updatePendingStatus(HttpServletRequest request) {
//		계약 아이디 구하기
		 int contractId = (int) request.getSession().getAttribute("contractId");
		
//		계약상태 변경 NOT_STARTED -> PENDING_WORKER
		int result = contractServ.updateStatusPW(contractId);
		
		if (result > 0) {
			
			System.out.println("업데이트 성공");
		}
		
		
		return "redirect:/contracts/pending?contractId="+contractId;
	}
	@GetMapping("/pending")
	public String readyWorkerSign(int contractId, Model model ) {
		
//		계약 객체 불러오기 
		Contracts c = contractServ.getContractById(contractId);
		
//		워커 id
		int workerId = c.getWorkerId();
//		회사 id
		int companyId = c.getCompanyId();
//		계약금액
		int contractPay = c.getContractPrice();
		
		MemberSHK worker = contractServ.getObjectById(workerId);
		
		PartnerCorp company = partnServ.findSelect(companyId);
		
//		model에 값 삽입
		model.addAttribute("worker", worker);
		model.addAttribute("company", company);
		model.addAttribute("pay", contractPay);
		model.addAttribute("contract", c);
		
		return "contracts/pending";
	}
	
	/**
	 * @return
	 * 매핑주소는 후에 workerId를 입력해 해당 워커에게 전달하기
	 */
	@PostMapping("/forwardToWorker")
	public String forwardContractToClient() {
		
		System.out.println("워커에게 계약서 주기");
		
		return "contracts/contractWorkerView";
	}
	
	/**
	 * @return
	 * 둘 사이의 사인이 완료되었을 경우
	 */
	@PostMapping("/confirm")
	public String contractConfirm( int contractId, HttpServletRequest request) {
		
		System.out.println("두 명 다 동의하던 군요");
		
//		계약 상태 변경
		int result = contractServ.updateStatusPro(contractId);
		
	    // 세션에 저장된 contractId 제거
	    request.getSession().removeAttribute("contractId");
		
		return "redirect:/";
		
	}
	
	@GetMapping("/paymentStatus")
	public String forwardToPayStatus(int contractId, Model model) {
		
		ContracResDTO respondojb = new ContracResDTO();
		
//		1. 프로젝트 명 set
		respondojb.setProjectName("웹사이트 UI 리디자인");
		
//		2.계약 객체 불러오기 
		Contracts c = contractServ.getContractById(contractId);
		
		respondojb.setContractId(c.getContractId());
		
//		워커 id
		int workerId = c.getWorkerId();
//		계약금액
		int contractPay = c.getContractPrice();
//		 멤버
		MemberSHK worker = contractServ.getObjectById(workerId);
		
//		3. 워커이름 set
		respondojb.setWorkerName(worker.getUserName());
		
//		4. 계약금을 기반으로 수수료 총 결제금액 구하기
		int chargePay = (int) (contractPay * 0.05);
		int totalPay = contractPay + chargePay;
		
		respondojb.setAmountValue(contractPay);
		respondojb.setChargeValue(chargePay);
		respondojb.setTotalValue(totalPay);  
		
//		5. model에 값 넣어주기
		model.addAttribute("response",respondojb);
		
		return "contract/paymentStatus";
	}

}
