package com.btw09.buyyourbrain.escros.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.btw09.buyyourbrain.escros.model.dto.EscrowViewDTO;
import com.btw09.buyyourbrain.escros.model.vo.Escrow;
import com.btw09.buyyourbrain.escros.model.vo.EscrowLog;
import com.btw09.buyyourbrain.worksession.model.vo.MileStone;
import com.btw09.buyyourbrain.worksession.model.vo.WorkSession;

@Repository
public class EscrosDAO {

	
	
	public int createEscrowByContractId(SqlSessionTemplate sqlSession, EscrowViewDTO dto) {
		// TODO Auto-generated method stub
		
		return sqlSession.insert("escrosMapper.createEscrowByContractId", dto);
	}

	public List<Escrow> findAll(SqlSessionTemplate sqlSession) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("escrosMapper.findAll");
	}

	public Escrow getEscrowById(SqlSessionTemplate sqlSession, int escrowId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("escrosMapper.getEscrowById", escrowId);
	}

	public int updateStatusDP(SqlSessionTemplate sqlSession, int escrowId, int balance) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<>();
		
		param.put("escrowId", escrowId);
		
//		int balance 
		param.put("balance", balance);
		
		return sqlSession.update("escrosMapper.updateStatusDP", param);
	}

	public int escrosRefund(SqlSessionTemplate sqlSession, int escrowId) {
		// TODO Auto-generated method stub
		return sqlSession.update("escrosMapper.escrosRefund", escrowId);
	}

	public int createEscrowLogHold(SqlSessionTemplate sqlSession, EscrowViewDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.insert("escrosMapper.createEscrowLogHold", dto);
	}

	public List<EscrowLog> findEscrowLogStat(SqlSessionTemplate sqlSession, String string) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("escrosMapper.findEscrowLogStat", string);
	}

	public List<EscrowLog> findEscrowLogStatEx(SqlSessionTemplate sqlSession, String string) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("escrosMapper.findEscrowLogStatEx", string);
	}

	public int createEscrowLogDownpay(SqlSessionTemplate sqlSession, int escrowId, int price) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<>();
		param.put("escrowId", escrowId);
		param.put("price", price);
		
		return sqlSession.insert("escrosMapper.createEscrowLogDownpay", param);
	}

	public int createEscrowLogRefund(SqlSessionTemplate sqlSession, int escrowId, int refundPrice) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<>();
		param.put("escrowId", escrowId);
		param.put("refundPrice", refundPrice);
		
		return sqlSession.insert("escrosMapper.createEscrowLogRefund", param);
	}

	public WorkSession getWsByContractId(SqlSessionTemplate sqlSession, int contractId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("worksessionMapper.getWsByContractId", contractId);
	}

	public List<MileStone> getMileListByConId(SqlSessionTemplate sqlSession, int contractId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("worksessionMapper.getMileListByConId", contractId);
	}

	public int createEscrowLogMilePay(SqlSessionTemplate sqlSession, int escrowId, int price) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<>();
		param.put("escrowId", escrowId);
		param.put("price", price);
		
		return sqlSession.insert("escrosMapper.createEscrowLogMilePay", param);
	}

	public int createEscrowLogFinalPay(SqlSessionTemplate sqlSession, int escrowId, int price) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<>();
		param.put("escrowId", escrowId);
		param.put("price", price);
		
		return sqlSession.insert("escrosMapper.createEscrowLogFinalPay", param);
	}

	public int updateStatusFinal(SqlSessionTemplate sqlSession, int escrowId) {
		// TODO Auto-generated method stub
		return sqlSession.update("escrosMapper.updateStatusFinal", escrowId);
	}

	public int updateMilePay(SqlSessionTemplate sqlSession, int milestoneId) {
		// TODO Auto-generated method stub
		return sqlSession.update("worksessionMapper.updateMilePay", milestoneId);
	}
	
	
	

}
