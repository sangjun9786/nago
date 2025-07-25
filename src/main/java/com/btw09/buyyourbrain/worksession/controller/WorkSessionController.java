package com.btw09.buyyourbrain.worksession.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.btw09.buyyourbrain.worksession.model.dto.SessionReqDTO;
import com.btw09.buyyourbrain.worksession.model.service.WorkSessionService;
import com.btw09.buyyourbrain.worksession.model.vo.MileStoneStats;
import com.btw09.buyyourbrain.worksession.model.vo.WorkSession;

@Controller
@RequestMapping("/work")
public class WorkSessionController {
	
	private final WorkSessionService sessionServ;
	
	private WorkSessionController(WorkSessionService sessionServ ) {
		this.sessionServ = sessionServ;
	}
	
	
	@GetMapping("/sessionView")
	public String forwardWorkSessionFomr(Model model) {
		
		
//		오늘 날짜 구하기
		String today = LocalDate.now().toString(); // "2025-07-25" 형식
		
		//마일스톤1		
		MileStoneStats mile1 = calculateMilestoneStats(1, LocalDate.of(2025, 7, 3));

		//마일스톤2
		MileStoneStats mile2 = calculateMilestoneStats(2, LocalDate.of(2025, 7, 18));
		
		
//		워크세션 불러오기
		List<WorkSession> workSessionList = sessionServ.findAll();
		
		
		List<SessionReqDTO> sessionList = new ArrayList<>();
		
		for (WorkSession session : workSessionList) {
			
			SessionReqDTO dto = new SessionReqDTO();
			
//			포매팅이 필요없는 컬럼들 먼저 매치
			dto.setSessionId(session.getSessionId());
			dto.setStatus(session.getStatus());
			
//			출근시각이 찍혀 있을 경우
			if (session.getStartedAt() != null) {
				
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		        
		        dto.setDay(dateFormat.format(session.getPlanDay()));              // 근무일자
		        dto.setStartedAt(timeFormat.format(session.getStartedAt()));        // 출근시각
				
			}else {
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				dto.setDay(dateFormat.format(session.getPlanDay()));
				dto.setStartedAt("");
			}
			
//			퇴근시각이 찍혀 있을 경우
			if (session.getEndedAt() !=  null) {
				
		        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		        dto.setEndedAt(timeFormat.format(session.getEndedAt()));   
				
			}else {
				
				dto.setEndedAt("");
			}
			
			sessionList.add(dto);
//			

			
		}
		
//		모델에 구한 데이터 저장
		model.addAttribute("sessionList", sessionList);
		model.addAttribute("today", today);
		model.addAttribute("mile1", mile1);
		model.addAttribute("mile2", mile2);
		
		return "worksession/workerSession";
	}
	
    /**
     * @param start 시작날짜
     * @param end 종료날짜
     * @return 작업 일수
     * 반복로직 메소드화 
     * 마일스톤 시작날짜와 종료날짜를 뺄셈연산을 통해 예정된 작업일 수를 리턴
     */
    public  long calculatePlannedWorkDays(LocalDate start, Date end) {
        if (end == null) {
            return 0;
        }

        LocalDate endDate = end.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return ChronoUnit.DAYS.between(start, endDate) + 1;
    }
    
    public MileStoneStats calculateMilestoneStats(int milestoneId, LocalDate startDate) {
       
    	Date startDateAsDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    	
    	int workDays = sessionServ.countWorkingDay(startDateAsDate, milestoneId);
        Date endDate = sessionServ.getEndDate(milestoneId);
        
        long plannedDays = calculatePlannedWorkDays(startDate, endDate);
        
        int graphValue = Math.round(workDays * 201 / plannedDays);
        int rateValue = (int) Math.round(((double) workDays / plannedDays) * 100);

        return new MileStoneStats(graphValue, rateValue);
    }

}
