package com.yedam.app.schedule.mapper;

import java.util.List;

import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.schedule.service.ScheduleVO;

public interface ScheduleMapper {
	//생산팀 중 근무조 편성되지 않은 직원출력
	public List<EmpVO> prodEmpList(EmpVO empVO);
	
	//근무조 편성전 db 비우기
	public int deleteAllSquadEmp();
	
	//근무조 편성
	public int insertSquadEmp(ScheduleVO scheduleVO);
	
	//근무조 설정 전 db 비우기
	public int deleteAllsquad();
	
	//근무조 설정
	public int insertSquad(ScheduleVO scheduleVO);
	
	//근무조 설정 출력
	public ScheduleVO selectSquadInfo();
	
	//근무조 편성 가져오기
	public List<ScheduleVO> selectSquadEmp();
}
