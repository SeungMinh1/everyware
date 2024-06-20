package com.yedam.app.attend.emp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		return "emp/index";
	}
	
	
	//단건조회
	
	// 등록

}
