package com.yedam.app.calendar.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.expression.Lists;

import com.yedam.app.calendar.service.CalendarBoxVO;
import com.yedam.app.calendar.service.CalendarService;
import com.yedam.app.calendar.service.CalendarVO;

@Controller
public class CalendarController {
	
	@Autowired
	CalendarService calendarService;
	
	@GetMapping("calendar")
	public String goCalendar(CalendarBoxVO calendarBoxVO, Model model) {
		
		List<CalendarBoxVO> blist = calendarService.calboxList(calendarBoxVO);
		List<CalendarVO> clist = calendarService.calList();
		model.addAttribute("boxList", blist);
		model.addAttribute("calList", clist);
		return "calendar/calendar";
	}
	
	@PostMapping("calendar")
	@ResponseBody
	public List<CalendarVO> calInfo(CalendarBoxVO calendarBoxVO, Model model) {
		return calendarService.calList();
	}
	
}
