package com.yedam.app.attend.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.attend.emp.service.EmpService;
import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.attend.emp.service.PageDTO;
import com.yedam.app.attend.security.service.LoginUserVO;
import com.yedam.app.common.service.CommonVO;

@Controller

public class EmpController {
	@Autowired
	EmpService empService;
	
	
	//전체조회
	@GetMapping("empList")
	public String empList(Model model, Integer page, Integer cnt, String dosearch,@AuthenticationPrincipal LoginUserVO principal) {
		page = page == null ? 1 : page;
		cnt = cnt == null ? 3 : cnt;
		int allCount = empService.cntList();
		PageDTO pg = new PageDTO(page, allCount, cnt);

		String aa = principal.getuserVO().getAccountId();
		int bb = principal.getuserVO().getEmpid();
		
		List<EmpVO> list = empService.empList(page, cnt, dosearch);
		model.addAttribute("empList", list);
		model.addAttribute("pg", pg);
		
		model.addAttribute("accountId", aa);
		model.addAttribute("eid", bb);
		
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
		List<CommonVO> poslist = empService.posList();
		List<CommonVO> departmentList = empService.departmentList();
		model.addAttribute("emp",empVO);
		model.addAttribute("position", poslist);
		model.addAttribute("department", departmentList);
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
		List<CommonVO> poslist = empService.posList();
		List<CommonVO> departmentList = empService.departmentList();
		model.addAttribute("emp", findVO);
		model.addAttribute("position", poslist);
		model.addAttribute("department", departmentList);
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
	@GetMapping("resetPwd")
	public String resetpassword(EmpVO empVO, Model model) {
		empService.resetPwd(empVO);
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp", findVO);
		return "emp/empInfo";

		
	}
	
	//비밀번호 초기화

}
