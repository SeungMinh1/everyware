package com.yedam.app.calendar.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<CalendarVO> calList(CalendarBoxVO calendarBoxVO) {
		// TODO Auto-generated method stub
		return calendarMapper.selectCalendarAll(calendarBoxVO);
	}
	
	//공유받은 세부 일정 조회
	@Override
	public List<CalendarVO> sharedCalList(CalendarBoxVO calendarBoxVO) {
		// TODO Auto-generated method stub
		return calendarMapper.selectSharedCal(calendarBoxVO);
	}

	@Override
	public List<CalendarBoxVO> calboxList(CalendarBoxVO calendarBoxVO) {
		// TODO Auto-generated method stub
		return calendarMapper.selectCalBox(calendarBoxVO);
	}

	@Override
	public Map<String, Object> updateCalColor(CalendarBoxVO calendarBoxVO) {
		Map<String, Object> map = new HashMap<>();
		
		int result = calendarMapper.updateCalColor(calendarBoxVO);
		
		if(result == 1) {
			map.put("calendarBoxVO" , calendarBoxVO);
		}
		return map;
	}

	@Override
	public Map<String, Object> updateCalExpress(CalendarBoxVO calendarBoxVO) {
		Map<String,Object> map = new HashMap<>();
		
		int result = calendarMapper.updateCalExpress(calendarBoxVO);
		
		if(result == 1) {
			map.put("calendarBoxVO" , calendarBoxVO);
		}
		return map;
	}

	@Override
	public List<CalendarBoxVO> sharedCalBoxList(CalendarBoxVO calendarBoxVO) {
		
		return calendarMapper.selectSharedCalBox(calendarBoxVO);
	}

	@Override
	public Map<String, Object> updateSharedCalColor(CalendarBoxVO calendarBoxVO) {
		Map<String, Object> map = new HashMap<>();
		
		int result = calendarMapper.updateSharedCalColor(calendarBoxVO);
		
		if(result == 1) {
			map.put("calendarBoxVO" , calendarBoxVO);
		}
		return map;
		
	}

	@Override
	public Map<String, Object> updateSharedCalExpress(CalendarBoxVO calendarBoxVO) {
		Map<String,Object> map = new HashMap<>();
		
		int result = calendarMapper.updateSharedCalExpress(calendarBoxVO);
		
		if(result == 1) {
			map.put("calendarBoxVO" , calendarBoxVO);
		}
		return map;
	}


	
}
