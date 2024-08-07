package com.yedam.app.attend.attend.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.attend.attend.service.AttendService;
import com.yedam.app.attend.attend.service.AttendVO;
import com.yedam.app.attend.attend.service.WeekVO;
import com.yedam.app.attend.emp.service.EmpService;
import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.attend.security.service.LoginUserVO;
import com.yedam.app.common.util.AuthUtil;

/**
 * 사원들의 출, 퇴근 등 근태르 관리하는 컨트롤러
 * @author admin
 */


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
		
		
		AttendVO thisMonth = attendService.countWorkTime2(empId, 0);
		if(thisMonth != null) {
			model.addAttribute("thisMonth",thisMonth);
		}else {
			model.addAttribute("thisMonth", new AttendVO());
		}
	
		
		AttendVO lastMonth = attendService.countWorkTime2(empId, 30);
		if(lastMonth != null) {
			model.addAttribute("lastMonth",lastMonth);
		}else {
			model.addAttribute("lastMonth", new AttendVO());
		}
		
		List<AttendVO> monthList = attendService.selectMonthList(attendVO);
		model.addAttribute("monList", monthList);
		return "emp/attend";
	}
	//attendService.checkWokrLate(att);
	//출근
	@PostMapping("gowork")
	@ResponseBody
	public int startWork(@RequestBody AttendVO attendVO) {
		int empId = AuthUtil.getEmpId();
		attendVO.setEmpId(empId);
		attendService.gowork(attendVO);//해당 사원 출근처리
		return attendService.checkWokrLate(attendVO);
	}
	
	//퇴근
	@PostMapping("endwork")
	@ResponseBody
	public int endWork(@RequestBody AttendVO attendVO2) {
		String state = attendVO2.getAttendType();
		int empId = AuthUtil.getEmpId();
		Date leaveTime = attendVO2.getLeaveTime();
		attendVO2.setEmpId(empId);
		AttendVO findatt = attendService.selectAttend(attendVO2); // 해당사원의 근무기록 조회
		
		findatt.setAttendType(state);
		findatt.setLeaveTime(leaveTime); 
		return attendService.endwork(findatt); //사원 퇴근처리 (조회한 근무기록을 update)
	}
	
	@PostMapping("exwork")
	@ResponseBody
	public int exwork() {
		int empId = AuthUtil.getEmpId();
		AttendVO attendVO = new AttendVO();
		attendVO.setEmpId(empId);
		AttendVO findatt = attendService.selectAttend(attendVO);
		return attendService.endworkExWork(findatt);
		
	}
	
	
	
	@PostMapping("findattend")
	@ResponseBody
	public AttendVO findToday( @AuthenticationPrincipal LoginUserVO principal) {
		AttendVO attendVO = new AttendVO();
		int empId = principal.getUserVO().getEmpId();
		attendVO.setEmpId(empId);
		return attendService.selectAttend(attendVO);
	}
	
	
	//해당일자별 근무기록
	@PostMapping("dateAttend")
	@ResponseBody
	public AttendVO findDateAttend(@RequestBody AttendVO attendVO, 
			                       @AuthenticationPrincipal LoginUserVO principal) throws ParseException {
		int empId = principal.getUserVO().getEmpId();
		attendVO.setEmpId(empId);
	
		return attendService.selectDateAttend(attendVO);
	}
	
	//전체사원 근태조회
	@GetMapping("allAttend")

	public String allAttend(Model model,@RequestParam(defaultValue = "0", required = false) Integer month) {
		
		List<WeekVO> list = attendService.findWeeks(month); //주차
		
		//1주차 정보
		List<EmpVO> empList = attendService.AllWorkTime(list.get(0));
		for(int i=0; i<empList.size(); i++) {
			List<Integer> newList = new ArrayList<>();
			newList.add(empList.get(i).getWeekwtime());
			empList.get(i).setWorkTimeList(newList);
		}
		
		//나머지주차 정보 조회
		for(int i=1; i<list.size(); i++) {			
			List<EmpVO> tempList = attendService.AllWorkTime(list.get(i));
			for(int j=0; j<tempList.size(); j++) {			
				empList.get(j).getWorkTimeList().add(tempList.get(j).getWeekwtime());  //
			}
		}
		
		//초과근무
		List<EmpVO> empList2 = attendService.AllOverWorkTime(list.get(0));
		for(int i=0; i<empList2.size(); i++) {
			List<Integer> newList = new ArrayList<>();
			newList.add(empList2.get(i).getOverweekwtime());
			empList2.get(i).setWorkTimeList(newList);
		}
		
		//나머지주차 정보 조회
		for(int i=1; i<list.size(); i++) {			
			List<EmpVO> tempList = attendService.AllOverWorkTime(list.get(i));
			for(int j=0; j<tempList.size(); j++) {			
				empList2.get(j).getWorkTimeList().add(tempList.get(j).getOverweekwtime());  //
			}
		}

		model.addAttribute("empList", empList);
		model.addAttribute("empList2", empList2);
		model.addAttribute("weeks", list);
		return "emp/allAttend";
	}
	
	
	
	
	//이전달 전체사원 근태조회
	@PostMapping("lastWeekAll")
	@ResponseBody
	public List<EmpVO> lastWorkAllAttend(@RequestBody EmpVO empVO){
		int month = empVO.getEmpId();
		List<WeekVO> list = attendService.findWeeks(month); //주차
		
		//1주차 정보
		List<EmpVO> empList = attendService.AllWorkTime(list.get(0));
		for(int i=0; i<empList.size(); i++) {
			List<Integer> newList = new ArrayList<>();
			newList.add(empList.get(i).getWeekwtime());
			empList.get(i).setWorkTimeList(newList);
		}
		//나머지주차 정보 조회
		for(int i=1; i<list.size(); i++) {			
			List<EmpVO> tempList = attendService.AllWorkTime(list.get(i));
			for(int j=0; j<tempList.size(); j++) {			
				empList.get(j).getWorkTimeList().add(tempList.get(j).getWeekwtime());  //
			}
		}
		
		return empList;
	}
	
	//부서별 직원 근태관리
	@GetMapping("deptAttend")
	public String selectDebtAttend(Model model) {
		String departmentName = AuthUtil.getDepartmentName();
		model.addAttribute("deptName", departmentName);
		return "emp/deptAttend";
	}
	
	@PostMapping("selectDept")
	@ResponseBody
	public List<EmpVO> selectDeptAttend(@RequestBody EmpVO empVO){
		String departmentName = AuthUtil.getDepartmentName();
		String newdate = empVO.getNewdate();
		return attendService.selectdeptAttend(departmentName, newdate);
	}
	
	@PostMapping("deptworkdata")
	@ResponseBody
	public List<AttendVO> selectDeptMonAttend(@RequestBody AttendVO attendVO){
		String departmentName = AuthUtil.getDepartmentName();
		attendVO.setDepartmentName(departmentName);
		
		return attendService.selectMondeptAttned(attendVO);
	}
	
}
