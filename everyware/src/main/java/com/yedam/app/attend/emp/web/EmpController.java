package com.yedam.app.attend.emp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.attend.emp.service.EmpService;
import com.yedam.app.attend.emp.service.EmpVO;

@Controller
public class EmpController {
	@Autowired
	EmpService empService;
	
	
	//전체조회
	@GetMapping("empList")
	public String empList(Model model) {
		List<EmpVO> list = empService.empList();
		model.addAttribute("empList", list);
		return "emp/empList";
	}
	//단건조회
	@GetMapping("empInfo")
	public String empInfo(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp", findVO);
		return "emp/empInfo";
	}
	// 등록
	@PostMapping("resetPwd")
	@ResponseBody
	public String resetpassword(@RequestBody int empId) {
		if(empService.resetPwd(empId) ==1) {
			return "emp/empList";
		}else {
			return "emp/empList";
		}
		
	}
	
	//비밀번호 초기화

}
