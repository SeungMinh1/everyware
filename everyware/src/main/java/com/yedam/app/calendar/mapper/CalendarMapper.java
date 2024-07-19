package com.yedam.app.calendar.mapper;

import java.util.List;

import com.yedam.app.calendar.service.CalendarBoxVO;
import com.yedam.app.calendar.service.CalendarVO;

public interface CalendarMapper {
	//전체조회
	public List<CalendarVO> selectCalendarAll(CalendarBoxVO calendarBoxVO);
	
	// 단건조회
	public CalendarVO selectCalendarInfo(CalendarVO calendarVO);
	
	// 등록
	public int insertCalendarInfo(CalendarVO calendarVO);
	
	//수정
	public int updateCalInfo(CalendarVO calendarVO);
	
	//삭제
	public int deleteCalInfo(CalendarVO calendarVO);
	
	//사원별 일정리스트 출력
	public List<CalendarBoxVO> selectCalBox(CalendarVO calendarVO);
	
	//캘린더 색 변경
	public int updateCalColor(CalendarBoxVO calendarBoxVO);
	
	//캘린더 출력 여부
	public int updateCalExpress(CalendarBoxVO calendarBoxVo);
	
	//공유받은 캘린더 출력 여부
	public int updateSharedCalExpress(CalendarBoxVO calendarBoxVO);
	
	//공유받은 일정리스트 조회
	public List<CalendarBoxVO> selectSharedCalBox(CalendarBoxVO calendarBoxVO);
	
	//공유받은 일정 상세조회
	public List<CalendarVO> selectSharedCal(CalendarBoxVO calendarBoxVO);
	
	//공유받은 일정 색 변경
	public int updateSharedCalColor(CalendarBoxVO calendarBoxVO);
	
	//일정목록 추가
	public int insertCalBox(CalendarBoxVO calendarBoxVO);
	
	//일정목록 변경
	public int updateCalBox(CalendarBoxVO calendarBoxVO);
	
	//내 일정목록 삭제
	public int deleteCalBox(CalendarBoxVO calendarBoxVO);
	
	//일정목록 삭제 시 일정삭제
	public int deleteCalBoxCal(CalendarBoxVO calendarBoxVO);
	
	//공유신청받은 내 일정목록
	public List<CalendarBoxVO> selectMyShared(CalendarBoxVO calendarBoxVO);
	
	//공유신청 수락
	public int updateApproveShare(CalendarBoxVO calendarBoxVO);
	
	//관심일정 삭제
	public int deleteApproveShare(CalendarBoxVO calendarBoxVO);
	
	//공유신청 전 조회
	public int checkRedup(CalendarBoxVO calendarBoxVO);
	
	//공유신청
	public int applyShare(CalendarBoxVO calendarBoxVO);
	
	
	
}
