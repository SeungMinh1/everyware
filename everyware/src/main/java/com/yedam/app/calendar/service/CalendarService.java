package com.yedam.app.calendar.service;

import java.util.List;

public interface CalendarService {
	//사원별 일정조회
	public List<CalendarVO> calList();
	
	//사원별 일정리스트 출력
	public List<CalendarBoxVO> calboxList(CalendarBoxVO calendarBoxVO);
}
