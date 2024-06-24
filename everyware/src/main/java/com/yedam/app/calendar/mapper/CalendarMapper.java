package com.yedam.app.calendar.mapper;

import java.util.List;

import com.yedam.app.calendar.service.CalendarBoxVO;
import com.yedam.app.calendar.service.CalendarVO;

public interface CalendarMapper {
	//전체조회
	public List<CalendarVO> selectCalendarAll();
	
	// 단건조회
	public CalendarVO selectCalendarInfo(CalendarVO calendarVO);
	
	// 등록
	public int insertCalendarInfo(CalendarVO calendarVO);
	
	//수정
	public int updateCalendarInfo(CalendarVO calendarVO);
	
	//삭제
	public int deleteCalendarInfo(CalendarVO calendarVO);
	
	//사원별 일정리스트 출력
	public List<CalendarBoxVO> selectCalBox(CalendarBoxVO calendarBoxVO);
	
	//캘린더 색 변경
	public int updateCalColor(CalendarBoxVO calendarBoxVO);
}
