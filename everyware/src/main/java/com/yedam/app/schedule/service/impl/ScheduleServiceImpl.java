package com.yedam.app.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.schedule.mapper.ScheduleMapper;
import com.yedam.app.schedule.service.ScheduleService;
import com.yedam.app.schedule.service.ScheduleVO;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	ScheduleMapper scheduleMapper;

	//생산팀 중 근무조 편성되지 않은 직원출력
	@Override
	public List<EmpVO> prodEmpList(EmpVO empVO) {
		
		return scheduleMapper.prodEmpList(empVO);
	}

	//근무조 편성
	@Override
	public int insertSquadEmp(List<ScheduleVO> list) {
		
		for(ScheduleVO scheduleVO : list) {
			scheduleMapper.insertSquadEmp(scheduleVO);
		}
		
		return 1;
	}
	
	//근무조 편성전 db 비우기
	@Override
	public int deleteAllSquadEmp() {
		
		return scheduleMapper.deleteAllSquadEmp();
	}
	
	//근무조 설정 전 db 비우기
	@Override
	public int deleteAllsquad() {
		// TODO Auto-generated method stub
		return scheduleMapper.deleteAllsquad();
	}
	//근무조 설정
	@Override
	public int insertSquad(ScheduleVO scheduleVO) {
		
		int num = scheduleVO.getSquadNum();
		
		for(int i = 0 ; i < num ; i++) {
			scheduleMapper.insertSquad(scheduleVO);
		}
				
		return 1;
	}
	
	//근무표 설정 가져오기
	@Override
	public ScheduleVO selectSquadInfo() {

		return scheduleMapper.selectSquadInfo();
	}

	//근무조 편성 가져오기
	@Override
	public List<ScheduleVO> selectSquadEmp() {
		// TODO Auto-generated method stub
		return scheduleMapper.selectSquadEmp();
	}
	
	
	
}
