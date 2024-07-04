package com.yedam.app.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.schedule.mapper.ScheduleMapper;
import com.yedam.app.schedule.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	ScheduleMapper scheduleMapper;

	@Override
	public List<EmpVO> prodEmpList(EmpVO empVO) {
		
		return scheduleMapper.prodEmpList(empVO);
	}
	
	
}
