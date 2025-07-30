package com.btw09.buyyourbrain.escros.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btw09.buyyourbrain.company.dao.PartnerDAO;
import com.btw09.buyyourbrain.company.model.vo.PartnerCorp;
import com.btw09.buyyourbrain.contracts.model.dao.ContractsDAO;
import com.btw09.buyyourbrain.contracts.model.vo.Contracts;
import com.btw09.buyyourbrain.escros.model.dao.EscrosDAO;
import com.btw09.buyyourbrain.escros.model.dto.EscrowViewDTO;
import com.btw09.buyyourbrain.escros.model.vo.Escrow;
import com.btw09.buyyourbrain.escros.model.vo.EscrowLog;
import com.btw09.buyyourbrain.member.vo.MemberSHK;
import com.btw09.buyyourbrain.worksession.model.vo.MileStone;
import com.btw09.buyyourbrain.worksession.model.vo.WorkSession;

@Service
public class EscrosServiceImpl implements EscrosService {

	private final EscrosDAO dao;
	private final ContractsDAO conDao;
	private final PartnerDAO partDao;
	
	
	public EscrosServiceImpl(EscrosDAO dao, ContractsDAO conDao, PartnerDAO partDao) {
		this.dao = dao;
		this.conDao = conDao;
		this.partDao = partDao;
	}
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//에스크로 데이터 생성 로직
	@Override
	public int createEscrowByContractId(EscrowViewDTO dto) {
		// TODO Auto-generated method stub
		return dao.createEscrowByContractId(sqlSession, dto );
	}

	@Override
	public List<Escrow> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll(sqlSession);
	}

	/**
	 * 에스크로 뷰 DTO 를 구하는 로직 
	 */
	@Override
	public EscrowViewDTO getEscrowViewById(int escrowId) {
		
		// 1. 에스크로 엔티티(실제 DB 테이블) 조회
	    Escrow escrow = dao.getEscrowById(sqlSession, escrowId);

	    // 2. 계약 정보
	    Contracts contract = conDao.getContractById(sqlSession, escrow.getContractId());

	    // 3. 유저 정보 (워커/클라이언트)
	    MemberSHK worker = conDao.getObjectById(sqlSession, contract.getWorkerId());
	    PartnerCorp client = partDao.findSelect(sqlSession, contract.getCompanyId() );

	    // 4. DTO 조립
	    EscrowViewDTO dto = new EscrowViewDTO();
	    
	    BeanUtils.copyProperties(escrow, dto);
	    
	    dto.setEscrowId(escrow.getEscrowId());
	    dto.setContractId(escrow.getContractId());
	    dto.setProjectName(contract.getProjectName());
	    dto.setWorkerName(worker.getUserName());
	    dto.setClientName(client.getName());
	    dto.setTotalAmount(escrow.getTotalAmount());
		// TODO Auto-generated method stub
		
	    return dto;
	}

	@Override
	public int updateStatusDP(int escrowId, int balance) {
		// TODO Auto-generated method stub
		return dao.updateStatusDP(sqlSession, escrowId, balance);
	}

	@Override
	public int escrosRefund(int escrowId) {
		// TODO Auto-generated method stub
		return dao.escrosRefund(sqlSession, escrowId);
	}

	@Override
	public int createEscrowLogHold(EscrowViewDTO dto) {
		// TODO Auto-generated method stub
		return dao.createEscrowLogHold(sqlSession, dto);
	}

	@Override
	public List<EscrowLog> findEscrowLogStat(String string) {
		// TODO Auto-generated method stub
		return dao.findEscrowLogStat(sqlSession, string);
	}

	@Override
	public List<EscrowLog> findEscrowLogStatEx(String string) {
		// TODO Auto-generated method stub
		return dao.findEscrowLogStatEx(sqlSession, string);
	}

	@Override
	public int createEscrowLogDownpay(int escrowId, int price) {
		// TODO Auto-generated method stub
		return dao.createEscrowLogDownpay(sqlSession, escrowId, price);
	}

	@Override
	public int createEscrowLogRefund(int escrowId, int refundPrice) {
		// TODO Auto-generated method stub
		return dao.createEscrowLogRefund(sqlSession, escrowId, refundPrice);
	}

	@Override
	public WorkSession getWsByContractId(int contractId) {
		// TODO Auto-generated method stub
		return dao.getWsByContractId(sqlSession, contractId);
	}

	@Override
	public List<MileStone> getMileListByConId(int contractId) {
		// TODO Auto-generated method stub
		return dao.getMileListByConId(sqlSession, contractId);
	}

	@Override
	public int createEscrowLogMilePay(int escrowId, int price) {
		// TODO Auto-generated method stub
		return dao.createEscrowLogMilePay(sqlSession, escrowId, price);
	}

	@Override
	public int createEscrowLogFinalPay(int escrowId, int price) {
		// TODO Auto-generated method stub
		return dao.createEscrowLogFinalPay(sqlSession, escrowId, price);
	}

	@Override
	public int updateStatusFinal(int escrowId) {
		// TODO Auto-generated method stub
		return dao.updateStatusFinal(sqlSession, escrowId);
	}

	@Override
	public int updateMilePay(int milestoneId) {
		// TODO Auto-generated method stub
		return dao.updateMilePay(sqlSession, milestoneId);
	}
	
	

}
