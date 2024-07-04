package com.yedam.app.schedule.mapper;

import java.util.List;

import com.yedam.app.attend.emp.service.EmpVO;

public interface ScheduleMapper {
	//생산팀 중 근무조 편성되지 않은 직원출력
	public List<EmpVO> prodEmpList(EmpVO empVO);
}
