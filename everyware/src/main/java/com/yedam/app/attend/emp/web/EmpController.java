package com.yedam.app.attend.emp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.attend.emp.service.EmpService;
import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.common.CommonVO;
import com.yedam.app.common.service.CommonService;

@Controller
public class EmpController {
	@Autowired
	EmpService empService;
	
	@Autowired
	CommonService commonService;
	
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
		
		CommonVO com = new CommonVO();
		String departmetnId = findVO.getDepartmentId();
		com.setCodeId(departmetnId);
		CommonVO findcom = commonService.commonInfo(com);
		
		findVO.setDepartmentName(findcom.getCodeName());
		model.addAttribute("emp", findVO);
		return "emp/empInfo";
	}
	// 등록

}
