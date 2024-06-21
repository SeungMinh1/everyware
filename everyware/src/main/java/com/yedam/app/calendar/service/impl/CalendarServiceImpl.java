package com.yedam.app.calendar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.calendar.mapper.CalendarMapper;
import com.yedam.app.calendar.service.CalendarBoxVO;
import com.yedam.app.calendar.service.CalendarService;
import com.yedam.app.calendar.service.CalendarVO;

@Service
public class CalendarServiceImpl implements CalendarService{
	
	@Autowired
	CalendarMapper calendarMapper;
	

	
	@Override
	public List<CalendarVO> calList() {
		// TODO Auto-generated method stub
		return calendarMapper.selectCalendarAll();
	}

	@Override
	public List<CalendarBoxVO> calboxList(CalendarBoxVO calendarBoxVO) {
		// TODO Auto-generated method stub
		return calendarMapper.selectCalBox(calendarBoxVO);
	}
	
}
