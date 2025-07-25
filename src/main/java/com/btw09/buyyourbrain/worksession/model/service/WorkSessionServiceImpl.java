package com.btw09.buyyourbrain.worksession.model.service;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btw09.buyyourbrain.worksession.model.dao.WorkSessionDAO;
import com.btw09.buyyourbrain.worksession.model.vo.WorkSession;

@Service
public class WorkSessionServiceImpl implements WorkSessionService {

	
		private final WorkSessionDAO sessionDao;
		
		public WorkSessionServiceImpl( WorkSessionDAO sessionDao ) {
			
			this.sessionDao = sessionDao;
		}
		
		@Autowired
		private SqlSessionTemplate sqlSession;

		@Override
		public List<WorkSession> findAll() {
			// TODO Auto-generated method stub
			return sessionDao.findAll(sqlSession);
		}

		@Override
		public int countWorkingDay(Date startDateAsDate, int i) {
			// TODO Auto-generated method stub
			return sessionDao.countWorkingDay(sqlSession, startDateAsDate, i);
		}

		@Override
		public Date getEndDate(int i) {
			// TODO Auto-generated method stub
			return sessionDao.getEndDate(sqlSession, i);
		}
}
