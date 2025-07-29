package com.btw09.buyyourbrain.rfid.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.btw09.buyyourbrain.rfid.model.dto.RFIDrepDTO;
import com.btw09.buyyourbrain.rfid.model.vo.RFIDCard;

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

	public List<RFIDCard> findAll(SqlSessionTemplate sqlsession) {
		// TODO Auto-generated method stub
		return sqlsession.selectList("RFIDCardMapper.findAll");
	}

}
