package com.btw09.buyyourbrain.rfid.model.service;

import java.util.List;

import com.btw09.buyyourbrain.rfid.model.dto.RFIDrepDTO;
import com.btw09.buyyourbrain.rfid.model.vo.RFIDCard;

public interface RFIDCardService {

	List<RFIDCard> findAll();

	RFIDCard findById(Long id);

	void create(RFIDCard rfidCard);

	void update(Long id, RFIDCard rfidCard);

	void delete(Long id);

	//작업지 id 구하기
	int getWorksiteId(int readerId);
	
	int insertWorkSession(RFIDrepDTO dto);
	
//	findAll(), create(), findById(), update(), delete()

}
