package com.yedam.app.attend.attend.web;

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
import com.yedam.app.attend.security.service.LoginUserVO;


@Controller
public class AttendController {
	@Autowired
	AttendService attendService;
	
	//조회
	@GetMapping("attend")
	public String attendhome(Model model, @AuthenticationPrincipal LoginUserVO principal) {
		int empId = principal.getuserVO().getEmpId();
		AttendVO attendVO = new AttendVO();
		attendVO.setEmpId(empId);
		
		List<AttendVO> list = attendService.selectAttendAll(attendVO);
		model.addAttribute("attnedList", list);
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
		attendVO2.setEmpId(empId);
		AttendVO findatt = attendService.selectAttend(attendVO2);
		findatt.setAttendType(state);
		
		return attendService.endwork(findatt);
	}
	

	

}
