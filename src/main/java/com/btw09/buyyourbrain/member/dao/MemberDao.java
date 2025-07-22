package com.btw09.buyyourbrain.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.btw09.buyyourbrain.member.vo.Member;
import com.btw09.buyyourbrain.member.vo.MemberExpert;

@Repository
public class MemberDao {
	
	// 일반유저 회원가입용 메서드
    public int insertMember(SqlSessionTemplate sqlSession, Member m) {
    	return sqlSession.insert("memberMapper.insertMember",m);
	}
    
    //고수 유저 회원가입용 메서드
    public int insertExpert(SqlSessionTemplate sqlSession, MemberExpert expert) {
    	return sqlSession.insert("memberMapper.insertExpert", expert);
    }

    //로그인
	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
		
		Member loginUser = sqlSession.selectOne("memberMapper.loginMember", m);
		
		return loginUser;
	}

}
