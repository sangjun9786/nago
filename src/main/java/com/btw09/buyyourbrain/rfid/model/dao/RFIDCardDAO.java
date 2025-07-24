package com.btw09.buyyourbrain.rfid.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.btw09.buyyourbrain.rfid.model.dto.RFIDrepDTO;

@Repository
public class RFIDCardDAO {

	public int getWorksiteId(SqlSessionTemplate sqlsession, int readerId) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("RFIDCardMapper.getWorksiteId", readerId);
	}

	public int insertWorkSession(SqlSessionTemplate sqlsession, RFIDrepDTO dto) {
		// TODO Auto-generated method stub
		return sqlsession.insert("RFIDCardMapper.insertWorkSession", dto);
	}

}
