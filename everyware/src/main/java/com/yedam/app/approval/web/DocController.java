package com.yedam.app.approval.web;

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

import com.yedam.app.approval.service.DocService;
import com.yedam.app.approval.service.DocVO;
import com.yedam.app.approval.service.DraftVO;
import com.yedam.app.approval.service.TaskVO;
import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.attend.security.service.LoginUserVO;

@Controller
public class DocController {
	@Autowired
	DocService docService;
	
	// 문서 조회
		// 결재 대기 문서
		@GetMapping("waitDocList")
		public String waitDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {	
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.waitDocList(id);
			model.addAttribute("waitDocList", list);			
			return "approvalDoc/waitDocList";
		}
		
		// 참조/열람 대기 문서
		@GetMapping("waitRefViewDocList")
		public String waitRefViewDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.waitRefViewDocList(id);
			model.addAttribute("waitRefViewDocList", list);			
			return "approvalDoc/waitRefViewDocList";
		}
		
		// 결재 예정 문서
		@GetMapping("expectedDocList")
		public String expectedDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.expectedDocList(id);
			model.addAttribute("expectedDocList", list);
			return "approvalDoc/expectedDocList";
		}
		
		// 기안 문서
		@GetMapping("draftDocList")
		public String draftDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.draftDocList(id);
			model.addAttribute("draftDocList", list);
			return "approvalDoc/draftDocList";
		}
		
		// 결재 문서
		@GetMapping("approvalDocList")
		public String approvalDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.approvalDocList(id);
			model.addAttribute("approvalDocList", list);
			return "approvalDoc/approvalDocList";
		}
		
		// 발송 문서
		@GetMapping("sendDocList")
		public String sendDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.sendDocList(id);
			model.addAttribute("sendDocList", list);
			return "approvalDoc/sendDocList";
		}
		
		// 수신 문서
		@GetMapping("receptionDocList")
		public String receptionDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.receptionDocList(id);
			model.addAttribute("receptionDocList", list);
			return "approvalDoc/receptionDocList";
		}
		
		// 참조/열람 문서
		@GetMapping("refViewDocList")
		public String refViewDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.refViewDocList(id);
			model.addAttribute("refViewDocList", list);
			return "approvalDoc/refViewDocList";
		}
		
			// 참조 문서
			@GetMapping("refDocList")
			public String refDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
				int id = principal.getUserVO().getEmpId();
				List<DocVO> list = docService.refDocList(id);
				model.addAttribute("refDocList", list);
				return "approvalDoc/refDocList";
			}
			
			// 열람 문서
			@GetMapping("viewDocList")
			public String viewDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
				int id = principal.getUserVO().getEmpId();
				List<DocVO> list = docService.viewDocList(id);
				model.addAttribute("viewDocList", list);
				return "approvalDoc/viewDocList";
			}
			
		// 임시 문서
		@GetMapping("temporaryDocList")
		public String temporaryDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.temporaryDocList(id);
			model.addAttribute("temporaryDocList", list);
			return "approvalDoc/temporaryDocList";
		}
	
	// 개별 문서 조회
	@GetMapping("docInfo")
	public String docInfo(DocVO docVO, Model model) {
		DocVO findVO = docService.docInfo(docVO);
		model.addAttribute("approvalDoc", findVO);
		return "approvalDoc/docInfo";
	}
	
	// 문서 등록(양식)
	@GetMapping("newTask")
	public String newTaskForm(Model model, TaskVO taskVO) {
		List<TaskVO> list = docService.category();
		model.addAttribute("newTask", list);
		
		return "draft/newTask";
	}
	
	@GetMapping("task")
	public String task(TaskVO taskVO, DraftVO draftVO, Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal, String departmentId) {
		TaskVO findVO = docService.newTask(taskVO);
		int id = principal.getUserVO().getEmpId();
		EmpVO findEmp = docService.empInfo(id);
		List<EmpVO> list = docService.allDept();
		
		model.addAttribute("task", findVO);
		model.addAttribute("empInfo", findEmp);
		model.addAttribute("empList", list);
		
		if(findVO.getTaskDocPath().equals("taskDraft")) {
			return "task/taskDraft";
		} else if(findVO.getTaskDocPath().equals("buyDraft")) {
			return "task/buyDraft";
		} else {
			return null;
		}	
	}
	// 문서 등록(페이지)
	
	// 문서 등록(처리)
	
	// 문서 임시저장(처리)
	@PostMapping("tempInsert")
	@ResponseBody
	public int tempInsert(@RequestBody DocVO docVO) {
		return docService.tempInsert(docVO);
	}
	
	// 문서 수정
	
	// 문서 삭제
	@PostMapping("docDelete")
	@ResponseBody
	public Map<String, Object> docDelete(@RequestBody List<Integer> docId) {
		return docService.docDelete(docId);
	}
}
