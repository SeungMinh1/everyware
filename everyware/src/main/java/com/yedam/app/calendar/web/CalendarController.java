package com.yedam.app.calendar.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.attend.security.service.LoginUserVO;
import com.yedam.app.calendar.service.CalendarBoxVO;
import com.yedam.app.calendar.service.CalendarService;
import com.yedam.app.calendar.service.CalendarVO;


@Controller
public class CalendarController {
	
	@Autowired
	CalendarService calendarService;
	
	//캘린더 출력
	@GetMapping("calendar")
	public String goCalendar(CalendarBoxVO calendarBoxVO, Model model, @AuthenticationPrincipal LoginUserVO principal) {
		CalendarVO cvo = new CalendarVO();
		
		int empId = principal.getuserVO().getEmpId();
		calendarBoxVO.setEmpId(empId);
		cvo.setEmpId(empId);
		 
		
		
		
		List<CalendarBoxVO> blist = calendarService.calboxList(cvo);
		List<CalendarVO> clist = calendarService.calList(calendarBoxVO);
		List<CalendarBoxVO> slist = calendarService.sharedCalBoxList(calendarBoxVO);
		model.addAttribute("boxList", blist);
		model.addAttribute("boxLength", blist.size());
		model.addAttribute("calList", clist);
		model.addAttribute("sharedList", slist);
		return "calendar/calendar";
	}
	
	//캘린더 설정페이지
	@GetMapping("setCalendar")
	public String goSetCalendar(CalendarBoxVO calendarBoxVO, Model model, @AuthenticationPrincipal LoginUserVO principal ) {
		CalendarVO cvo = new CalendarVO();
		
		int empId = principal.getuserVO().getEmpId();
		calendarBoxVO.setEmpId(empId);
		cvo.setEmpId(empId);
		
		List<CalendarBoxVO> blist = calendarService.calboxList(cvo);
		List<CalendarVO> clist = calendarService.calList(calendarBoxVO);
		List<CalendarBoxVO> slist = calendarService.sharedCalBoxList(calendarBoxVO);
		List<CalendarBoxVO> shlist = calendarService.selectMySahred(calendarBoxVO);
		model.addAttribute("boxList", blist);
		model.addAttribute("boxLength", blist.size());
		model.addAttribute("calList", clist);
		model.addAttribute("sharedList", slist);
		model.addAttribute("myshared", shlist);
		return "calendar/settingCalendar";
	}
	
	@PostMapping("calendar")
	@ResponseBody
	public List<CalendarVO> calInfo(CalendarBoxVO calendarBoxVO, Model model, @AuthenticationPrincipal LoginUserVO principal) {
		int empId = principal.getuserVO().getEmpId();
		calendarBoxVO.setEmpId(empId);
		
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
	public String calendarInsertForm(CalendarVO calendarVO, Model model) {
		List<CalendarBoxVO> blist = calendarService.calboxList(calendarVO);
		model.addAttribute("boxList", blist);
		return "calendar/insertCalendar";
	}
	
	//일정 등록
	@PostMapping("insertCal")
	@ResponseBody
	public String insertCalProcess(CalendarVO caledarVO) {
		int result = calendarService.insertCal(caledarVO);
		
		
		return "redirect:calendar";
	}
	
	//일정 상세페이지 이동
	@GetMapping("calInfo")
	public String calendarInfoForm(CalendarVO calendarVO, Model model, @RequestParam int calendarId) {
		calendarVO.setCalendarId(calendarId);
		CalendarVO cVO = calendarService.calInfo(calendarVO);
		
		List<CalendarBoxVO> blist = calendarService.calboxList(calendarVO);
		model.addAttribute("boxList", blist);
		model.addAttribute("cal", cVO);
		return "calendar/updateCalendar";
	}
	
	//공유받은 일정 상세페이지 이동
	@GetMapping("sharedCalInfo")
	public String sharedClaendarInfoForm(CalendarVO calendarVO, Model model, @RequestParam int calendarId) {
		calendarVO.setCalendarId(calendarId);
		CalendarVO cVO = calendarService.calInfo(calendarVO);
		
		List<CalendarBoxVO> blist = calendarService.calboxList(calendarVO);
		model.addAttribute("boxList", blist);
		model.addAttribute("cal", cVO);
		return "calendar/sharedCalendarInfo";
	}
	
	@PostMapping("calInfo")
	@ResponseBody
	public String updateCalProcess(CalendarVO calendarVO) {
		
		calendarService.updateCal(calendarVO);
		return "redirect:calendar";
	}
	
	//일정목록 추가
	@PostMapping("insertCalBox")
	
	public String inertCalBoxProcess(CalendarBoxVO calendarBoxVO, Model model, @AuthenticationPrincipal LoginUserVO principal) {
		CalendarVO cvo = new CalendarVO();

		int empId = principal.getuserVO().getEmpId();
		calendarBoxVO.setEmpId(empId);
		calendarService.insertCalBox(calendarBoxVO);
		
		
		cvo.setEmpId(empId);
		List<CalendarBoxVO> blist = calendarService.calboxList(cvo);
		model.addAttribute("boxList", blist);
		
		return "calendar/calendar :: calBox";
	}
	
	//일정목록 변경
	@PostMapping("updateCalBox")
	
	public String updateCalBoxProcess(@RequestBody List<CalendarBoxVO> list , Model model, @AuthenticationPrincipal LoginUserVO principal) {
		CalendarVO cvo = new CalendarVO();
		int empId = principal.getuserVO().getEmpId();
		
		list.forEach(cal -> calendarService.updateCalBox(cal));;	
		
		cvo.setEmpId(empId);
		List<CalendarBoxVO> blist = calendarService.calboxList(cvo);
		model.addAttribute("boxList", blist);
		
		return "calendar/calendar :: calBox";
	}
	
	//일정목록 삭제
	@PostMapping("deleteCalBox")
	public String deleteCalBoxProcess(@RequestBody List<CalendarBoxVO> list, Model model, @AuthenticationPrincipal LoginUserVO principal) {
		CalendarVO cvo = new CalendarVO();
		int empId = principal.getuserVO().getEmpId();
		
		list.forEach(cal -> calendarService.deleteCalBox(cal));
		
		cvo.setEmpId(empId);
		List<CalendarBoxVO> blist = calendarService.calboxList(cvo);
		model.addAttribute("boxList", blist);
		
		
		return "calendar/calendar :: calBox";
	
	}
	
	//공유신청 수락
	@PostMapping("approveShare")
	public String approveShareProcess(CalendarBoxVO calendarBoxVO, Model model, @AuthenticationPrincipal LoginUserVO principal) {
		calendarService.updateApproveShare(calendarBoxVO);
		
		int empId = principal.getuserVO().getEmpId();
		calendarBoxVO.setEmpId(empId);
		
		List<CalendarBoxVO> shlist = calendarService.selectMySahred(calendarBoxVO);
		model.addAttribute("myshared", shlist);
		
		return "calendar/settingCalendar :: iShare";
	}
	
	//관심일정 삭제
	@PostMapping("deleteShare")
	public String deleteShareProcess(@RequestBody List<CalendarBoxVO> list, Model model, @AuthenticationPrincipal LoginUserVO principal) {
		int empId = principal.getuserVO().getEmpId();
		CalendarBoxVO cBox = new CalendarBoxVO();
		
		for (CalendarBoxVO c : list) {
			c.setEmpId(empId);
			calendarService.deleteApproveShare(c);
		}
		
		cBox.setEmpId(empId);
		List<CalendarBoxVO> slist = calendarService.sharedCalBoxList(cBox);
		model.addAttribute("sharedList", slist);
		
		return "calendar/settingCalendar :: otherShare";
	}
	
	//공유신청 거절
		@PostMapping("declineShare")
		public String declineShareProcess(@RequestBody List<CalendarBoxVO> list, Model model, @AuthenticationPrincipal LoginUserVO principal) {
			int empId = principal.getuserVO().getEmpId();
			CalendarBoxVO cBox = new CalendarBoxVO();
			
			for (CalendarBoxVO c : list) {
				calendarService.deleteApproveShare(c);
			}
			
			cBox.setEmpId(empId);
			List<CalendarBoxVO> shlist = calendarService.selectMySahred(cBox);
			model.addAttribute("myshared", shlist);
			
			return "calendar/settingCalendar :: iShare";
		}
	
	
}
