package com.yedam.app.attend.attend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.attend.attend.mapper.AttendMapper;
import com.yedam.app.attend.attend.service.AttendService;
import com.yedam.app.attend.attend.service.AttendVO;
@Service
public class AttendServiceImpl implements AttendService {

	@Autowired
	AttendMapper attendMapper;
	
	@Override
	public int gowork(AttendVO attendVO) {
		return attendMapper.gowork(attendVO);
	}
	@Override
	public int endwork(AttendVO attendVO) {
		if(attendVO.getWorkTime() < 9*60) {
			attendVO.setExceedWorkTime(0);
		}
		return attendMapper.endwork(attendVO);
	}

	@Override
	public List<AttendVO> selectAttendAll(AttendVO attendVO) {
		return attendMapper.selectAttendList(attendVO);
	}


	@Override
	public AttendVO selectAttend(AttendVO attendVO) {
		return attendMapper.selectAttend(attendVO);
	}
	@Override
	public int countAttend(AttendVO attendV) {
		return attendMapper.countAttend(attendV);
	}
	@Override
	public List<AttendVO> countWorkTime(AttendVO attendVO, int week) {
		return attendMapper.countWorkTime(attendVO, week);
	}

}
