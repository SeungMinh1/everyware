package com.yedam.app.schedule.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.schedule.service.ScheduleService;
import com.yedam.app.schedule.service.ScheduleVO;

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
	
	//근무조 편성
	@PostMapping("/insSquEmp")
	@ResponseBody
	public String squadEmpProcess(@RequestBody List<ScheduleVO> list, Model model) {
		//근무조 편성전 db 비우기
		scheduleService.deleteAllSquadEmp();
		//근무조 편성
		scheduleService.insertSquadEmp(list);
		 	
		return "1";
		
	}
	
	//근무조 설정
	@PostMapping("/setSquEmp")
	@ResponseBody
	public String squadSetProcess(@RequestBody ScheduleVO scheduleVO) {
		//근무조 설정 전 db 비우기
		scheduleService.deleteAllsquad();
		//근무조 설정
		scheduleService.insertSquad(scheduleVO);
		
		return "1";
	}
	
	//근무표 생성 전 확인 화면
	@GetMapping("/squadInfo")
	public String squadInfo(Model model) {
		//근무표 설정 가져오기
		ScheduleVO scheduleVO = scheduleService.selectSquadInfo();
		model.addAttribute("squadInfo", scheduleVO);
		
		//근무조 편성 가져오기
		int squadNum = scheduleVO.getSquadNo();
		List<ScheduleVO> list = scheduleService.selectSquadEmp();
		model.addAttribute("squadNum", squadNum);
		model.addAttribute("squadEmp", list);
		
		
		return "schedule/squadInfo";
	}
	
	//근무표 생성
	@GetMapping("/makeSchedule")
	public String makeSchedule(Model model) {
		//근무표 설정 가져오기
		ScheduleVO scheduleVO = scheduleService.selectSquadInfo();
		model.addAttribute("squadInfo", scheduleVO);
		
		//근무조 몇조인지
		int squadNum = scheduleVO.getSquadNo();
		model.addAttribute("squadNum", squadNum);
		
		return"schedule/makeSchedule";
	}
	
	//근무조 변경
	@GetMapping("/updateSquadEmp")
	public String updateSquadEmp(EmpVO empVO, Model model) {
		List<Integer> empList = new ArrayList<>();
		List<ScheduleVO> slist = scheduleService.selectSquadEmp();
		empList.add(9999);
		
		for(ScheduleVO svo : slist) {
			empList.add(svo.getEmpId());
		}
		
		//근무표 설정 가져오기
		ScheduleVO scheduleVO = scheduleService.selectSquadInfo();
		model.addAttribute("squadInfo", scheduleVO);
		
		//근무조 몇조인지
		int squadNum = scheduleVO.getSquadNo();
		model.addAttribute("squadNum", squadNum);
		
		
		empVO.setEmpIdList(empList);
		List<EmpVO> plist = scheduleService.prodEmpList(empVO);
		model.addAttribute("proList", plist);
		model.addAttribute("squadList", slist);
		
		
		model.addAttribute("empList", empVO);
		
		return "schedule/updateSquadEmp";
	}
	
	//근무조 변경 직원 삽입시 제외하고 출력
		@PostMapping("/makeUpdSquad")
		
		public String updateSquadProcess(EmpVO empVO, Model model) {
			
			List<EmpVO> plist = scheduleService.prodEmpList(empVO);
			model.addAttribute("proList", plist);
			
			
			return "schedule/updateSquadEmp :: prodEmp";
			
		}
	
	
	
}
