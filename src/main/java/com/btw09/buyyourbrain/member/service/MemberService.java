package com.btw09.buyyourbrain.member.service;

import org.springframework.stereotype.Service;

import com.btw09.buyyourbrain.member.vo.Member;
import com.btw09.buyyourbrain.member.vo.MemberExpert;
import com.btw09.buyyourbrain.member.vo.MemberSHK;

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
	    

}
