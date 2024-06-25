package com.yedam.app.calendar.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.calendar.service.CalendarBoxVO;
import com.yedam.app.calendar.service.CalendarService;
import com.yedam.app.calendar.service.CalendarVO;

@Controller
public class CalendarController {
	
	@Autowired
	CalendarService calendarService;
	
	//캘린더 출력
	@GetMapping("calendar")
	public String goCalendar(CalendarBoxVO calendarBoxVO, Model model) {
		
		List<CalendarBoxVO> blist = calendarService.calboxList(calendarBoxVO);
		List<CalendarVO> clist = calendarService.calList(calendarBoxVO);
		List<CalendarBoxVO> slist = calendarService.sharedCalBoxList(calendarBoxVO);
		model.addAttribute("boxList", blist);
		model.addAttribute("calList", clist);
		model.addAttribute("sharedList", slist);
		return "calendar/calendar";
	}
	
	
	@PostMapping("calendar")
	@ResponseBody
	public List<CalendarVO> calInfo(CalendarBoxVO calendarBoxVO, Model model) {
		List<CalendarVO> clist = calendarService.calList(calendarBoxVO);
		clist.addAll(calendarService.sharedCalList(calendarBoxVO));
		return clist;
	}
	
	// 캘린더 색 변경
	@PostMapping("changedColor")
	@ResponseBody
	public Map<String, Object> changedColor(CalendarBoxVO calendarBoxVO){
		return calendarService.updateCalColor(calendarBoxVO);
	}
	
	//공유받은 일정 색 변경
	@PostMapping("changedSharedColor")
	@ResponseBody
	public Map<String, Object> changeSharedColor(CalendarBoxVO calendarBoxVo){
		return calendarService.updateSharedCalColor(calendarBoxVo);
	}
	

	
	//캘린더 출력여부
	@PostMapping("changeExpress")
	@ResponseBody
	public Map<String, Object> changeExpress(CalendarBoxVO calendarBoxVO){
		return calendarService.updateCalExpress(calendarBoxVO);
	}
	
	//공유받은 캘린더 출력여부
	@PostMapping("changeSharedExpress")
	@ResponseBody
	public Map<String, Object> changeSharedExpress(CalendarBoxVO calendarBoxVO){
		return calendarService.updateSharedCalExpress(calendarBoxVO);
	}
	
	//일정등록페이지 이동
	@GetMapping("insertCal")
	public String calendarInsertForm() {
		return "calendar/insertCalendar";
	}
	
	
	
	
}
