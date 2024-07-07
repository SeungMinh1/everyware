package com.yedam.app.schedule.service;

import java.util.List;

import com.yedam.app.attend.emp.service.EmpVO;


public interface ScheduleService {
	

	//생산팀 중 근무조 편성되지 않은 직원출력
		public List<EmpVO> prodEmpList(EmpVO empVO);
		
	//근무조 편성 전 db 비우기
		public int deleteAllSquadEmp();
		
	//근무조 편성
		public int insertSquadEmp(List<ScheduleVO> list);
		
	//근무조 설정 전 db 비우기
	public int deleteAllsquad();
}
