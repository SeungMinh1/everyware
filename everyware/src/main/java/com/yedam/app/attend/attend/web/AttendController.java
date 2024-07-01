package com.yedam.app.attend.attend.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.attend.attend.service.AttendService;
import com.yedam.app.attend.attend.service.AttendVO;
import com.yedam.app.attend.emp.service.EmpService;
import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.attend.security.service.LoginUserVO;


@Controller
public class AttendController {
	@Autowired
	AttendService attendService;
	@Autowired
	EmpService empService;
	
	//조회
	@GetMapping("attend")
	public String attendhome(Model model, @AuthenticationPrincipal LoginUserVO principal) {
		int empId = principal.getuserVO().getEmpId();
		EmpVO empVO = new EmpVO();
		empVO.setEmpId(empId);
		
		EmpVO userInfo = empService.empInfo(empVO);
		model.addAttribute("user", userInfo);
		
		AttendVO attendVO = new AttendVO();
		attendVO.setEmpId(empId);
		if(attendService.countAttend(attendVO) != 0) {
			AttendVO att = attendService.selectAttend(attendVO);
			model.addAttribute("att", att);
			int week = 0;
			List<AttendVO> thisWeekTime =  attendService.countWorkTime(att, week);
			model.addAttribute("thisweek", thisWeekTime);
		};
		
		
		//List<AttendVO> lastWeekTime =  attendService.countWorkTime(attendVO, 7);
		
		//model.addAttribute("thisweek", thisWeekTime);
		
		return "emp/attend";
	}
	
	//출근
	@PostMapping("gowork")
	@ResponseBody
	public int startWork(@RequestBody AttendVO attendVO, @AuthenticationPrincipal LoginUserVO principal) {
		int empId = principal.getuserVO().getEmpId();
		attendVO.setEmpId(empId);
		return attendService.gowork(attendVO);
	}
	
	//퇴근
	@PostMapping("endwork")
	@ResponseBody
	public int endWork(@RequestBody AttendVO attendVO2,  @AuthenticationPrincipal LoginUserVO principal) {
		String state = attendVO2.getAttendType();
		int empId = principal.getuserVO().getEmpId();
		Date leaveTime = attendVO2.getLeaveTime();
		attendVO2.setEmpId(empId);
		AttendVO findatt = attendService.selectAttend(attendVO2);
		findatt.setAttendType(state);
		findatt.setLeaveTime(leaveTime);
		return attendService.endwork(findatt);
	}
	
	
	@PostMapping("findattend")
	@ResponseBody
	public AttendVO findToday( @AuthenticationPrincipal LoginUserVO principal) {
		AttendVO attendVO = new AttendVO();
		int empId = principal.getuserVO().getEmpId();
		attendVO.setEmpId(empId);
		return attendService.selectAttend(attendVO);
	}
	

}
