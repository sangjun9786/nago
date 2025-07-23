package com.btw09.buyyourbrain.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btw09.buyyourbrain.member.dao.MemberDao;
import com.btw09.buyyourbrain.member.vo.Member;
import com.btw09.buyyourbrain.member.vo.MemberExpert;
import com.btw09.buyyourbrain.member.vo.MemberSHK;

@Service
public class MemberServiceImpl implements MemberService {
	
	 @Autowired
	    private MemberDao dao;
	 
	 @Autowired
		private SqlSessionTemplate sqlSession;

	 	//일반 유저 회원가입
	    @Override
	    public int insertMember(Member m) {
	        return dao.insertMember(sqlSession,m);
	    }
	    
	    //고수 유저 회원가입
	    @Override
	    public int insertExpert(MemberExpert expert) {
	    	return dao.insertExpert(sqlSession, expert);
	    }
	    
	    
	    //로그인
	    @Override
		public Member loginMember(Member m) {

			Member loginUser = dao.loginMember(sqlSession,m);
			
			return loginUser;
		}

	    
	    //김석현 테스트용 로직
		@Override
		public MemberSHK findSelect(int i) {
			// TODO Auto-generated method stub
			return dao.findSelect(sqlSession, i);
		}

}
