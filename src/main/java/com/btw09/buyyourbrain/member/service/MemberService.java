package com.btw09.buyyourbrain.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.btw09.buyyourbrain.member.vo.Member;
import com.btw09.buyyourbrain.member.vo.MemberExpert;
import com.btw09.buyyourbrain.member.vo.MemberSHK;
import com.btw09.buyyourbrain.member.vo.Worker;

@Service
public interface MemberService {

		// 일반 유저 회원가입
	    int insertMember(Member m);

	    //로그인
	    Member loginMember(Member m);
	    
	    // 고수 유저 회원가입
	    int insertExpert(MemberExpert expert);
	    
    	//김석현 테스트용 메소드	    	
	    MemberSHK findSelect(int i);
	    // 전체 멤버 가져오기
		List<MemberSHK> findAll();

		List<Worker> findWorkerAll();

		void insertWorker(MemberSHK mem);
		
		//카드키 매칭
		void updateWorkerCard(int workerId, int rfidId);
	    

}
