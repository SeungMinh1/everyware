package com.yedam.app.calendar.service;

import java.util.List;
import java.util.Map;

public interface CalendarService {
	//사원별 일정조회
	public List<CalendarVO> calList(CalendarBoxVO calendarBoxVO);
	
	//사원별 일정리스트 출력
	public List<CalendarBoxVO> calboxList(CalendarBoxVO calendarBoxVO);
	
	//캘린더 색 변경
	public Map<String, Object> updateCalColor(CalendarBoxVO calendarBoxVO);
	
	//캘린더 출력 여부
	public Map<String, Object> updateCalExpress(CalendarBoxVO calendarBoxVO);
	
	//공유받은 캘린더 출력 여부
	public Map<String, Object> updateSharedCalExpress(CalendarBoxVO calendarBoxVO);
	
	//공유받은 일정리스트 조회
	public List<CalendarBoxVO> sharedCalBoxList(CalendarBoxVO calendarBoxVO);
	
	//공유받은 세부일정 조회
	public List<CalendarVO> sharedCalList(CalendarBoxVO calendarBoxVO);
	
	//공유받은 일정 색 변경
	public Map<String, Object> updateSharedCalColor(CalendarBoxVO calendarBoxVO);
}
