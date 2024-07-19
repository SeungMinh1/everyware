package com.yedam.app.calendar.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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
	public List<CalendarBoxVO> calboxList(CalendarVO calendarVO) {
		// TODO Auto-generated method stub
		return calendarMapper.selectCalBox(calendarVO);
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
	
	//일정등록
	@Override
	public int insertCal(CalendarVO calendarVO) {
		int result = calendarMapper.insertCalendarInfo(calendarVO);
		return result;
	}
	
	//일정 단건조회
	@Override
	public CalendarVO calInfo(CalendarVO calendarVO) {
		
		return calendarMapper.selectCalendarInfo(calendarVO);
	}

	//일정 수정
	@Override
	public Map<String, Object> updateCal(CalendarVO calendarVO) {
		Map<String, Object> map = new HashMap<>();
		
		int result = calendarMapper.updateCalInfo(calendarVO);
		
		map.put("result", result);
		
		return map;
	}
	
	//일정목록 추가
	@Override
	public int insertCalBox(CalendarBoxVO calendarBoxVO) {
		int result = calendarMapper.insertCalBox(calendarBoxVO);
		return result;
	}

	@Override
	@Transactional //에러시 전부 롤백
	public int updateCalBox(List<CalendarBoxVO> list) {
		int result = 0;
		list.forEach(cal -> calendarMapper.updateCalBox(cal));	
		
		return 1;
	}

	@Override
	@Transactional //에러시 전부 롤백
	public int deleteCalBox(List<CalendarBoxVO> list) {
		list.forEach(cal->calendarMapper.deleteCalBox(cal));
		return 1;
	}
	
	@Override
	@Transactional
	public int deleteCalBoxCal(List<CalendarBoxVO> list) {
		list.forEach(cal->calendarMapper.deleteCalBoxCal(cal));
		return 1;
	}

	@Override
	public List<CalendarBoxVO> selectMySahred(CalendarBoxVO calendarBoxVO) {
		
		return calendarMapper.selectMyShared(calendarBoxVO);
	}

	@Override
	public int updateApproveShare(CalendarBoxVO calendarBoxVO) {
		int result = calendarMapper.updateApproveShare(calendarBoxVO);
		return result;
	}

	@Override
	public int deleteApproveShare(CalendarBoxVO calendarBoxVO) {
		int result = calendarMapper.deleteApproveShare(calendarBoxVO);
		return result;
	}

	//일정 삭제
	@Override
	public int deleteCal(CalendarVO calrendarVO) {
		int result = calendarMapper.deleteCalInfo(calrendarVO);
		return result;
	}

	@Override
	public int checkRedup(CalendarBoxVO calendarBoxVO) {
		int result = calendarMapper.checkRedup(calendarBoxVO);
		return result;
	}

	@Override
	public int applyShare(CalendarBoxVO calendarBoxVO) {
		int result = calendarMapper.applyShare(calendarBoxVO);
		
		return result;
	}

	
	
	
	


	
}
