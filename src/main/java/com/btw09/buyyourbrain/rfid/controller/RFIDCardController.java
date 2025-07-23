package com.btw09.buyyourbrain.rfid.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.btw09.buyyourbrain.rfid.model.service.RFIDCardService;
import com.btw09.buyyourbrain.rfid.model.vo.RFIDCard;

@Controller
@RequestMapping("/rfid-cards")
public class RFIDCardController {

    private final RFIDCardService rfidCardService;

    public RFIDCardController(RFIDCardService rfidCardService) {
        this.rfidCardService = rfidCardService;
    }
    
    //0. RFID_워커 매치
    /**
     * @return
     * /rfid-cards/{rfid}/assign 이런 매핑 주소 형태로 바꿔야 함 
     * 지금은 테스트용이라 07.16
     */
    @GetMapping("/assign")
    public String forwardMatchForm() {
    	
    	System.out.println("매칭 폼으로 이동");
    	
    	return "rfid/workerCardMatch";
    	
    }
    // 1. 목록 조회(Read)
    @GetMapping
    public String list(Model model) {
        List<RFIDCard> cards = rfidCardService.findAll();
        model.addAttribute("cards", cards);
        return "rfid/list";
    }

    // 2. 등록 폼(Create Form)
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("rfidCard", new RFIDCard());
        return "rfid/create";
    }

    // 3. 등록 처리(Create Action)
    @PostMapping("/create")
    public String create(@ModelAttribute RFIDCard rfidCard) {
        rfidCardService.create(rfidCard);
        return "redirect:/rfid-cards";
    }

    // 4. 상세 조회(Read One)
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        RFIDCard card = rfidCardService.findById(id);
        model.addAttribute("rfidCard", card);
        return "rfid/detail";
    }

    // 5. 수정 폼(Update Form)
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        RFIDCard card = rfidCardService.findById(id);
        model.addAttribute("rfidCard", card);
        return "rfid/edit";
    }

    // 6. 수정 처리(Update Action)
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute RFIDCard rfidCard) {
        rfidCardService.update(id, rfidCard);
        return "redirect:/rfid-cards";
    }

    // 7. 삭제(Delete)
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        rfidCardService.delete(id);
        return "redirect:/rfid-cards";
    }
}

