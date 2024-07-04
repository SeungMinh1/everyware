package com.yedam.app.schedule.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.schedule.service.ScheduleService;

/*
 * 근무표 생성, 근무조 편성, 근무조 인원 변경
 */

@Controller
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;
	
	//근무표 편성
	@GetMapping("/makeSquad")
	public String makeSchedulePage(EmpVO empVO, Model model) {
		List<Integer> empList = new ArrayList<>();
		empList.add(9999);
		
		empVO.setEmpIdList(empList);

		
		
		
		List<EmpVO> plist = scheduleService.prodEmpList(empVO);
		model.addAttribute("proList", plist);
		
		
		model.addAttribute("empList", empVO);
		
		
		return "schedule/makeSquad";
	}
	
	//직원 삽입시 제외하고 출력
	@PostMapping("/makeSquad")
	
	public String makeSquadProcess(EmpVO empVO, Model model) {
		
		List<EmpVO> plist = scheduleService.prodEmpList(empVO);
		model.addAttribute("proList", plist);
		
		
		return "schedule/makeSquad :: prodEmp";
		
	}
	
	
	
	
	
	
	
	
	
}
