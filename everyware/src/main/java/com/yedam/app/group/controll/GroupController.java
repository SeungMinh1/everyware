package com.yedam.app.group.controll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.approval.service.ApprovalService;
import com.yedam.app.approval.service.DocService;
import com.yedam.app.approval.service.DocVO;
import com.yedam.app.attend.attend.service.AttendService;
import com.yedam.app.attend.attend.service.AttendVO;
import com.yedam.app.attend.emp.service.EmpService;
import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.attend.emp.service.PageDTO;
import com.yedam.app.calendar.service.CalendarBoxVO;
import com.yedam.app.calendar.service.CalendarService;
import com.yedam.app.calendar.service.CalendarVO;
import com.yedam.app.common.util.AuthUtil;
import com.yedam.app.dataroom.file.service.DataFileService;
import com.yedam.app.mail.mail.service.Criteria;
import com.yedam.app.mail.mail.service.MailService;
import com.yedam.app.mail.mail.service.MailVO;
import com.yedam.app.post.service.PostService;
import com.yedam.app.post.service.PostVO;
import com.yedam.app.post.service.SearchVO;


@Controller
public class GroupController {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	DataFileService dataFileService;
	
	@Autowired
	EmpService empService;
	
	@Autowired
	DocService docService;
	
	@Autowired
	ApprovalService approvalService;
	
	@Autowired
	AttendService attendService;
	
	@Autowired
	CalendarService calendarService;
	
	@Autowired
	PostService postService;
	
	@GetMapping("main")
	public String goMain(Model model) { 
		MailVO mailVO = new MailVO();
		mailVO.setMailboxId("d1");
		
		int empId = AuthUtil.getEmpId();
		EmpVO empVO = new EmpVO();
		Criteria cri = new Criteria();
		empVO.setEmpId(empId);;
		List<MailVO> find = mailService.mailboxInfo(mailVO, empId, cri);
		model.addAttribute("mailboxInfo", find);
		
		List<DocVO> list = docService.waitDocList(empId);
		model.addAttribute("waitDocList", list);
		
		EmpVO userInfo = empService.empInfo(empVO); //로그인한 사람 정보찾기
		model.addAttribute("user", userInfo);
		
		AttendVO attendVO = new AttendVO();
		attendVO.setEmpId(empId);
		if(attendService.countAttend(attendVO) != 0) { // 오늘 근무기록이 있다면(출근을 했다면)
			AttendVO att = attendService.selectAttend(attendVO);
			model.addAttribute("att", att);  // 오늘 근무기록 + 해당 사원의 사원정보(이름, 부서, 직위)
		}
		
		CalendarBoxVO cvo = new CalendarBoxVO();
		cvo.setEmpId(empId);
		List<CalendarVO> clist =  calendarService.calList(cvo);
		model.addAttribute("clist", clist);
		
		// 게시판
		SearchVO searchVO = new SearchVO();
		String departmentId = AuthUtil.getDepartmentId();
		searchVO.setDepartmentId(departmentId);
		PostVO postVO = new PostVO();
		postVO.setBoardId(1);
		Integer page = 0;
		Integer cnt = 0;
		page = page == null ? 1 : page;
		cnt = cnt == null ? 10 : cnt;

		// 페이징
		postVO.setPage(page);
		postVO.setCnt(cnt);

		List<PostVO> list2 = postService.selectMainNotice(postVO);
		model.addAttribute("postUpMain", list2);
		
		List<PostVO> depList = postService.selectDeptAll(postVO, searchVO);
		model.addAttribute("deptMain", depList);
		
		
		
		return "main/index";
	}
	
	@GetMapping("/")
	public String home() { 
		return "emp/login";
	}

}
