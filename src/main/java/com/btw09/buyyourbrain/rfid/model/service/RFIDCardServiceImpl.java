package com.btw09.buyyourbrain.rfid.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btw09.buyyourbrain.rfid.model.dao.RFIDCardDAO;
import com.btw09.buyyourbrain.rfid.model.dto.RFIDrepDTO;
import com.btw09.buyyourbrain.rfid.model.vo.RFIDCard;

@Service
public class RFIDCardServiceImpl  implements RFIDCardService{
	
	private final RFIDCardDAO dao;
	
	public RFIDCardServiceImpl ( RFIDCardDAO dao ) {
		this.dao = dao;
	}
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	

	@Override
	public List<RFIDCard> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RFIDCard findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(RFIDCard rfidCard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Long id, RFIDCard rfidCard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getWorksiteId(int readerId) {
		// TODO Auto-generated method stub
		return dao.getWorksiteId(sqlsession, readerId);
	}

	@Override
	public int insertWorkSession(RFIDrepDTO dto) {
		// TODO Auto-generated method stub
		return dao.insertWorkSession(sqlsession, dto);
	}
	
	

}
