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
	
	//조회 - 근태기록 페이지로
	@GetMapping("attend")
	public String attendhome(Model model, @AuthenticationPrincipal LoginUserVO principal) {
		int empId = principal.getUserVO().getEmpId(); 
		EmpVO empVO = new EmpVO();
		empVO.setEmpId(empId); 
		
		EmpVO userInfo = empService.empInfo(empVO); //로그인한 사람 정보찾기
		model.addAttribute("user", userInfo);
		
		AttendVO attendVO = new AttendVO();
		attendVO.setEmpId(empId);
		if(attendService.countAttend(attendVO) != 0) { // 오늘 근무기록이 있다면(출근을 했다면)
			AttendVO att = attendService.selectAttend(attendVO);
			model.addAttribute("att", att);  // 오늘 근무기록 + 해당 사원의 사원정보(이름, 부서, 직위)
		}
		AttendVO totalTime =  attendService.countWorkTime(attendVO); // 로그인한 사원의 누적 근무 기록
		model.addAttribute("totalTime", totalTime);
		
		return "emp/attend";
	}
	
	//출근
	@PostMapping("gowork")
	@ResponseBody
	public int startWork(@RequestBody AttendVO attendVO, @AuthenticationPrincipal LoginUserVO principal) {
		int empId = principal.getUserVO().getEmpId();
		attendVO.setEmpId(empId);
		return attendService.gowork(attendVO); //해당 사원 출근처리
	}
	
	//퇴근
	@PostMapping("endwork")
	@ResponseBody
	public int endWork(@RequestBody AttendVO attendVO2,  @AuthenticationPrincipal LoginUserVO principal) {
		String state = attendVO2.getAttendType();
		int empId = principal.getUserVO().getEmpId();
		Date leaveTime = attendVO2.getLeaveTime();
		attendVO2.setEmpId(empId);
		AttendVO findatt = attendService.selectAttend(attendVO2); // 해당사원의 근무기록 조회
		findatt.setAttendType(state);
		findatt.setLeaveTime(leaveTime);
		return attendService.endwork(findatt); //사원 퇴근처리 (조회한 근무기록을 update)
	}
	
	
	@PostMapping("findattend")
	@ResponseBody
	public AttendVO findToday( @AuthenticationPrincipal LoginUserVO principal) {
		AttendVO attendVO = new AttendVO();
		int empId = principal.getUserVO().getEmpId();
		attendVO.setEmpId(empId);
		return attendService.selectAttend(attendVO);
	}
	

}
