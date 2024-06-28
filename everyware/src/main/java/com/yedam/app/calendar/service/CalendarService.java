package com.yedam.app.calendar.service;

import java.util.List;
import java.util.Map;

public interface CalendarService {
	//사원별 일정조회
	public List<CalendarVO> calList(CalendarBoxVO calendarBoxVO);
	
	//단건 일정조회
	public CalendarVO calInfo(CalendarVO calendarVO);
	
	//사원별 일정리스트 출력
	public List<CalendarBoxVO> calboxList(CalendarVO calendarVO);
	
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
	
	//일정 등록
	public int insertCal(CalendarVO calendarVO);
	
	//일정 변경
	public Map<String, Object> updateCal(CalendarVO calendarVO);
	
	//내 일정목록 추가
	public int insertCalBox(CalendarBoxVO calendarBoxVO);
	
	//내 일정목록 수정
	public int updateCalBox(CalendarBoxVO calendarBoxVO);
	
	//내 일정목록 삭제
	public int deleteCalBox(CalendarBoxVO calendarBoxVO);
	
}
