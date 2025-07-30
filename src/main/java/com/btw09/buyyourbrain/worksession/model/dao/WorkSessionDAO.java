package com.btw09.buyyourbrain.worksession.model.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.btw09.buyyourbrain.worksession.model.vo.WorkSession;

@Repository
public class WorkSessionDAO {

	public List<WorkSession> findAll(SqlSessionTemplate sqlSession) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("worksessionMapper.findAll");
	}

	public int countWorkingDay(SqlSessionTemplate sqlSession, Date startDateAsDate, int i) {
		// TODO Auto-generated method stub
		
		//Date -> String으로 바꿈 사유 : sql에서 util.date 와 호환이 안됨
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDateStr = sdf.format(startDateAsDate);
		
		Map<String,Object> param = new HashMap<>();
		
		param.put("startDate", startDateStr);
		param.put("value", i);
		
		return sqlSession.selectOne("worksessionMapper.countWorkingDay", param);
	}

	public Date getEndDate(SqlSessionTemplate sqlSession, int i) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("worksessionMapper.getEndDate", i);
	}

}
