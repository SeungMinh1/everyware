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

import com.yedam.app.approval.service.DocService;
import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.attend.security.service.LoginUserVO;
import com.yedam.app.calendar.service.CalendarBoxVO;
import com.yedam.app.calendar.service.CalendarService;
import com.yedam.app.calendar.service.CalendarVO;
import com.yedam.app.common.util.AuthUtil;

/**
 * 일정 조회, 등록, 공유, 수정, 삭제 처리하는 컨트롤러 클래스
 * 
 * @author 송재현
 * @since 240620
 * @version 1.0
 * @see
 */
@Controller
public class CalendarController {

	@Autowired
	CalendarService calendarService;

	@Autowired
	DocService docService;

	// 캘린더 출력
	@GetMapping("calendar")
	public String goCalendar(CalendarBoxVO calendarBoxVO, Model model) {
		CalendarVO cvo = new CalendarVO();

		Integer empId = AuthUtil.getEmpId();
		calendarBoxVO.setEmpId(empId);
		cvo.setEmpId(empId);
		System.out.println(empId);

		List<EmpVO> list = docService.allDept();

		List<CalendarBoxVO> blist = calendarService.calboxList(cvo);
		List<CalendarVO> clist = calendarService.calList(calendarBoxVO);
		List<CalendarBoxVO> slist = calendarService.sharedCalBoxList(calendarBoxVO);
		model.addAttribute("empList", list);
		model.addAttribute("boxList", blist);
		model.addAttribute("boxLength", blist.size());
		model.addAttribute("calList", clist);
		model.addAttribute("sharedList", slist);

		return "calendar/calendar";
	}

	// 캘린더 설정페이지
	@GetMapping("setCalendar")
	public String goSetCalendar(CalendarBoxVO calendarBoxVO, Model model) {
		CalendarVO cvo = new CalendarVO();

		Integer empId = AuthUtil.getEmpId();
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
	public List<CalendarVO> calInfo(CalendarBoxVO calendarBoxVO, Model model) {
		Integer empId = AuthUtil.getEmpId();
		calendarBoxVO.setEmpId(empId);

		List<CalendarVO> clist = calendarService.calList(calendarBoxVO);
		clist.addAll(calendarService.sharedCalList(calendarBoxVO));
		return clist;
	}

	// 캘린더 색 변경
	@PostMapping("changedColor")
	@ResponseBody
	public Map<String, Object> changedColor(CalendarBoxVO calendarBoxVO) {

		calendarBoxVO.setEmpId(AuthUtil.getEmpId());
		return calendarService.updateCalColor(calendarBoxVO);
	}

	// 공유받은 일정 색 변경
	@PostMapping("changedSharedColor")
	@ResponseBody
	public Map<String, Object> changeSharedColor(CalendarBoxVO calendarBoxVo) {
		calendarBoxVo.setEmpId(AuthUtil.getEmpId());

		return calendarService.updateSharedCalColor(calendarBoxVo);
	}

	// 캘린더 출력여부
	@PostMapping("changeExpress")
	@ResponseBody
	public Map<String, Object> changeExpress(CalendarBoxVO calendarBoxVO) {
		calendarBoxVO.setEmpId(AuthUtil.getEmpId());

		return calendarService.updateCalExpress(calendarBoxVO);
	}

	// 공유받은 캘린더 출력여부
	@PostMapping("changeSharedExpress")
	@ResponseBody
	public Map<String, Object> changeSharedExpress(CalendarBoxVO calendarBoxVO) {
		calendarBoxVO.setEmpId(AuthUtil.getEmpId());
		return calendarService.updateSharedCalExpress(calendarBoxVO);
	}

	// 일정등록페이지 이동
	@GetMapping("insertCal")
	public String calendarInsertForm(CalendarVO calendarVO, Model model) {

		Integer empId = AuthUtil.getEmpId();
		calendarVO.setEmpId(empId);
		List<CalendarBoxVO> blist = calendarService.calboxList(calendarVO);
		model.addAttribute("boxList", blist);
		return "calendar/insertCalendar";
	}

	// 일정 등록
	@PostMapping("insertCal")
	@ResponseBody
	public int insertCalProcess(CalendarVO caledarVO) {
		caledarVO.setEmpId(AuthUtil.getEmpId());

		int result = calendarService.insertCal(caledarVO);

		return result;
	}

	// 일정 삭제
	@PostMapping("deleteCal")
	@ResponseBody
	public int deleteCalProcess(CalendarVO calendarVO) {
		int result = calendarService.deleteCal(calendarVO);

		return result;
	}

	// 일정 상세페이지 이동
	@GetMapping("calInfo")
	public String calendarInfoForm(CalendarVO calendarVO, Model model, @RequestParam int calendarId) {
		calendarVO.setCalendarId(calendarId);
		calendarVO.setEmpId(AuthUtil.getEmpId());
		CalendarVO cVO = calendarService.calInfo(calendarVO);

		List<CalendarBoxVO> blist = calendarService.calboxList(calendarVO);
		model.addAttribute("boxList", blist);
		model.addAttribute("cal", cVO);

		return "calendar/updateCalendar";
	}

	// 공유받은 일정 상세페이지 이동
	@GetMapping("sharedCalInfo")
	public String sharedClaendarInfoForm(CalendarVO calendarVO, Model model, @RequestParam int calendarId) {
		calendarVO.setCalendarId(calendarId);
		calendarVO.setEmpId(AuthUtil.getEmpId());
		CalendarVO cVO = calendarService.calInfo(calendarVO);

		List<CalendarBoxVO> blist = calendarService.calboxList(calendarVO);
		model.addAttribute("boxList", blist);
		model.addAttribute("cal", cVO);

		return "calendar/sharedCalendarInfo";
	}

	@PostMapping("calInfo")
	@ResponseBody
	public Map<String, Object> updateCalProcess(CalendarVO calendarVO) {

		return calendarService.updateCal(calendarVO);

	}

	// 일정목록 추가
	@PostMapping("insertCalBox")

	public String inertCalBoxProcess(CalendarBoxVO calendarBoxVO, Model model) {

		int empId = AuthUtil.getEmpId();
		calendarBoxVO.setEmpId(empId);
		calendarService.insertCalBox(calendarBoxVO);

		return "forward:/calBox";
	}

	@PostMapping("calBox")
	public String calBoxList(Model model) {
		int empId = AuthUtil.getEmpId();

		CalendarVO cvo = new CalendarVO();
		cvo.setEmpId(empId);
		List<CalendarBoxVO> blist = calendarService.calboxList(cvo);
		model.addAttribute("boxList", blist);

		return "calendar/calendar :: calBox";
	}

	// 일정목록 변경
	@PostMapping("updateCalBox")

	public String updateCalBoxProcess(@RequestBody List<CalendarBoxVO> list, Model model) {

		calendarService.updateCalBox(list);

		return "forward:/calBox";
	}

	// 일정목록 삭제
	@PostMapping("deleteCalBox")
	public String deleteCalBoxProcess(@RequestBody List<CalendarBoxVO> list, Model model,
			@AuthenticationPrincipal LoginUserVO principal) {

		calendarService.deleteCalBox(list);

		return "forward:/calBox";

	}

	// 공유신청 수락
	@PostMapping("approveShare")
	public String approveShareProcess(CalendarBoxVO calendarBoxVO, Model model,
			@AuthenticationPrincipal LoginUserVO principal) {
		calendarService.updateApproveShare(calendarBoxVO);

		int empId = principal.getUserVO().getEmpId();
		calendarBoxVO.setEmpId(empId);

		List<CalendarBoxVO> shlist = calendarService.selectMySahred(calendarBoxVO);
		model.addAttribute("myshared", shlist);

		return "calendar/settingCalendar :: iShare";
	}

	// 관심일정 삭제
	@PostMapping("deleteShare")
	public String deleteShareProcess(@RequestBody List<CalendarBoxVO> list, Model model,
			@AuthenticationPrincipal LoginUserVO principal) {
		int empId = AuthUtil.getEmpId();
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
	
	// 관심일정 거절
	@PostMapping("denyShare")
	public String denyShareProcess(@RequestBody List<CalendarBoxVO> list, Model model) {
		
		for(CalendarBoxVO c : list) {
			calendarService.deleteApproveShare(c);
		}
		
		CalendarBoxVO calendarBoxVO = new CalendarBoxVO();		
		Integer empId = AuthUtil.getEmpId();
		calendarBoxVO.setEmpId(empId);

		List<CalendarBoxVO> shlist = calendarService.selectMySahred(calendarBoxVO);
		model.addAttribute("myshared", shlist);
		
		return "calendar/settingCalendar :: iShare";
	}


	// 해당사원 일정목록출력
	@PostMapping("empIdBox")
	
	public String empIdBox(CalendarVO calendarVO, Model model) {

		List<CalendarBoxVO> blist = calendarService.calboxList(calendarVO);
		model.addAttribute("empIdBox", blist);

		return "calendar/calendar :: calList";
	}

	//공유신청 전 조회
	@PostMapping("checkRedup")
	@ResponseBody
	public int checkRedup(CalendarBoxVO calendarBoxVO, Model model) {
		calendarBoxVO.setEmpId(AuthUtil.getEmpId());
		int result = calendarService.checkRedup(calendarBoxVO);
		
		return result; 
	}
	
	
	 //공유신청
	 
	 @PostMapping("applyShare")
	 @ResponseBody
	 public int applyShareProcess(CalendarBoxVO calendarBoxVO, Model model) {
		 calendarBoxVO.setEmpId(AuthUtil.getEmpId());
		 int result = calendarService.applyShare(calendarBoxVO);
	 
		 return result; 
	 }

}
