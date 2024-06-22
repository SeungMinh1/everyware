package com.yedam.app.attend.emp.web;

import java.util.List;
import java.util.Map;

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
	
	// 등록 - 페이지
	@GetMapping("empInsert")
	public String empInsertForm(Model model) {
		EmpVO empVO = new EmpVO();
		int newEmpId = empService.searchEmpId();
		empVO.setEmpId(newEmpId);
		model.addAttribute("emp",empVO);
		return "emp/empInsert";
	}
	
	//등록 -  처리
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		empService.empInsert(empVO);
		return "redirect:empList";
	}
	
	//수정 - 페이지
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp", findVO);
		return "emp/empUpdate";
	}
	
	//수정 처리
	@PostMapping("empUpdate")
	@ResponseBody
	public Map<String, Object> empUpdateProcess(@RequestBody EmpVO empVO){
		return empService.empUpdate(empVO);
	}
	
	//삭제
	@GetMapping("empDelete")
	public String deleteEmp(int empId) {
		empService.empDelete(empId);
		return "redirect:empList";
	}
	
	
	
	// 비밀번호초기화
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
