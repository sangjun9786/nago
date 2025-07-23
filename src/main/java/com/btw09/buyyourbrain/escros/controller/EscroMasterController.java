package com.btw09.buyyourbrain.escros.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btw09.buyyourbrain.contracts.model.dto.ContracResDTO;
import com.btw09.buyyourbrain.contracts.model.service.ContractsService;
import com.btw09.buyyourbrain.contracts.model.vo.Contracts;
import com.btw09.buyyourbrain.escros.model.service.EscrosService;
import com.btw09.buyyourbrain.member.vo.MemberSHK;

@Controller
@RequestMapping("/escros")
public class EscroMasterController {
	
	
	private final EscrosService escroServ;
	private final ContractsService contractServ;

    public EscroMasterController(EscrosService escroServ, ContractsService contractServ) {
        this.escroServ = escroServ;
        this.contractServ = contractServ;
    }
    
    //0. 에스크로 결제 페이지 조회
    /**
     * @return
     * 사용자와 연결된 "체결됨" 상태의 계약이 있을 경우, 그 계약에 대한 결제 페이지를 화면에 보여줌
     */
	@GetMapping("/paymentReady")
	public String paymentReadyForm(int contractId, Model model) {

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
		model.addAttribute("response", respondojb);

		return "escros/escrosPayRequest";

	}
    
    // 0_2. 에스크로 관리자 관리 페이지 조회(일단 화면만 보는 용)
    @GetMapping("/adminPage")
    public String viewAdminPage() {
    	
    	System.out.println("에스크로 관리자 화면 페이지 보는 로직 타는 중");
    	
    	return "escros/escroAdminForm";
    }
    
    @PostMapping("/payment")
    @ResponseBody
    public String  createEscrowPayment(@RequestParam("contractId") int contractId, 
									   @RequestParam("totalValue") int totalValue ) {
       
    	// 에스크로 생성 로직 (ex: DB insert, 결제 연동 등)
        try {
        	int result = escroServ.createEscrowByContractId(contractId, totalValue); // 서비스 계층 구현
        	
        	if (result >0 ) {
        		
        		System.out.println("에스크로 생성 성공~!!!!!!!!");
				
			}
        	
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
   

    // 2. 등록 폼(Create Form)
//    @GetMapping("/create")
//    public String createForm(Model model) {
//        model.addAttribute("rfidCard", new RFIDCard());
//        return "rfid/create";
//    }
//
//    // 3. 등록 처리(Create Action)
//    @PostMapping("/create")
//    public String create(@ModelAttribute RFIDCard rfidCard) {
//        rfidCardService.create(rfidCard);
//        return "redirect:/rfid-cards";
//    }
//
//    // 4. 상세 조회(Read One)
//    @GetMapping("/{id}")
//    public String detail(@PathVariable Long id, Model model) {
//        RFIDCard card = rfidCardService.findById(id);
//        model.addAttribute("rfidCard", card);
//        return "rfid/detail";
//    }
//
//    // 5. 수정 폼(Update Form)
//    @GetMapping("/{id}/edit")
//    public String editForm(@PathVariable Long id, Model model) {
//        RFIDCard card = rfidCardService.findById(id);
//        model.addAttribute("rfidCard", card);
//        return "rfid/edit";
//    }
//
//    // 6. 수정 처리(Update Action)
//    @PostMapping("/{id}/edit")
//    public String edit(@PathVariable Long id, @ModelAttribute RFIDCard rfidCard) {
//        rfidCardService.update(id, rfidCard);
//        return "redirect:/rfid-cards";
//    }
//
//    // 7. 삭제(Delete)
//    @PostMapping("/{id}/delete")
//    public String delete(@PathVariable Long id) {
//        rfidCardService.delete(id);
//        return "redirect:/rfid-cards";
//    }
	

}
