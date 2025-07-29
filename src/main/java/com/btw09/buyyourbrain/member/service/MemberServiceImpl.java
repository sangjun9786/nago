package com.btw09.buyyourbrain.member.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btw09.buyyourbrain.member.dao.MemberDao;
import com.btw09.buyyourbrain.member.vo.Member;
import com.btw09.buyyourbrain.member.vo.MemberExpert;
import com.btw09.buyyourbrain.member.vo.MemberSHK;
import com.btw09.buyyourbrain.member.vo.Worker;

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

		@Override
		public List<MemberSHK> findAll() {
			// TODO Auto-generated method stub
			return dao.findAll(sqlSession);
		}

		@Override
		public List<Worker> findWorkerAll() {
			// TODO Auto-generated method stub
			return dao.findWorkerAll(sqlSession);
		}

		@Override
		public void insertWorker(MemberSHK mem) {
			// TODO Auto-generated method stub
			
			dao.insertWorker(sqlSession, mem);
			
		}

		@Override
		public void updateWorkerCard(int workerId, int rfidID) {
			// TODO Auto-generated method stub
			dao.updateWorkerCard(sqlSession, workerId, rfidID);
		}

}
