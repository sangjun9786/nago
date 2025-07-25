package com.btw09.buyyourbrain.worksession.model.service;

import java.util.Date;
import java.util.List;

import com.btw09.buyyourbrain.worksession.model.vo.WorkSession;

public interface WorkSessionService {
	
	//테이블 모든 데이터를 가져옴
	List<WorkSession> findAll();
	
	// 근무 일 수 구하는 로직 
	int countWorkingDay(Date startDateAsDate, int i);

	Date getEndDate(int i);

}
