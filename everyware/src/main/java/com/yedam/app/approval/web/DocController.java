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

import com.yedam.app.approval.service.ApprovalService;
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
	
	@Autowired
	ApprovalService approvalService;
	
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
		
		@GetMapping("goDraftDocList")
		public String goDraftDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.goDraftDocList(id);
			model.addAttribute("draftDocList", list);
			return "approvalDoc/goDraftDocList";
		}
		
		@GetMapping("rejDraftDocList")
		public String rejDraftDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.rejDraftDocList(id);
			model.addAttribute("draftDocList", list);
			return "approvalDoc/rejDraftDocList";
		}
		
		@GetMapping("compDraftDocList")
		public String compDraftDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.compDraftDocList(id);
			model.addAttribute("draftDocList", list);
			return "approvalDoc/compDraftDocList";
		}
		
		// 결재 문서
		@GetMapping("approvalDocList")
		public String approvalDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.approvalDocList(id);
			model.addAttribute("approvalDocList", list);
			return "approvalDoc/approvalDocList";
		}

		@GetMapping("goApprovalDocList")
		public String goApprovalDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.goApprovalDocList(id);
			model.addAttribute("approvalDocList", list);
			return "approvalDoc/goApprovalDocList";
		}
		
		@GetMapping("rejApprovalDocList")
		public String rejApprovalDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.rejApprovalDocList(id);
			model.addAttribute("approvalDocList", list);
			return "approvalDoc/rejApprovalDocList";
		}
		
		@GetMapping("compApprovalDocList")
		public String compApprovalDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.compApprovalDocList(id);
			model.addAttribute("approvalDocList", list);
			return "approvalDoc/compApprovalDocList";
		}
		
		// 발송 문서
		@GetMapping("sendDocList")
		public String sendDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.sendDocList(id);
			model.addAttribute("sendDocList", list);
			return "approvalDoc/sendDocList";
		}
		
		@GetMapping("waitSendDocList")
		public String waitSendDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.waitSendDocList(id);
			model.addAttribute("sendDocList", list);
			return "approvalDoc/waitSendDocList";
		}
		
		@GetMapping("recSendDocList")
		public String recSendDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.recSendDocList(id);
			model.addAttribute("sendDocList", list);
			return "approvalDoc/recSendDocList";
		}
		
		@GetMapping("goSendDocList")
		public String goSendDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.goSendDocList(id);
			model.addAttribute("sendDocList", list);
			return "approvalDoc/goSendDocList";
		}
		
		@GetMapping("compSendDocList")
		public String compSendDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.compSendDocList(id);
			model.addAttribute("sendDocList", list);
			return "approvalDoc/compSendDocList";
		}
		
		@GetMapping("rejSendDocList")
		public String rejSendDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.rejSendDocList(id);
			model.addAttribute("sendDocList", list);
			return "approvalDoc/rejSendDocList";
		}
		
		@GetMapping("retSendDocList")
		public String retSendDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.retSendDocList(id);
			model.addAttribute("sendDocList", list);
			return "approvalDoc/retSendDocList";
		}
		
		// 수신 문서
		@GetMapping("receptionDocList")
		public String receptionDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.receptionDocList(id);
			model.addAttribute("receptionDocList", list);
			return "approvalDoc/receptionDocList";
		}
		
		@GetMapping("waitReceptionDocList")
		public String waitReceptionDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.waitReceptionDocList(id);
			model.addAttribute("receptionDocList", list);
			return "approvalDoc/waitReceptionDocList";
		}
		
		@GetMapping("recReceptionDocList")
		public String recReceptionDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.recReceptionDocList(id);
			model.addAttribute("receptionDocList", list);
			return "approvalDoc/recReceptionDocList";
		}
		
		@GetMapping("goReceptionDocList")
		public String goReceptionDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.goReceptionDocList(id);
			model.addAttribute("receptionDocList", list);
			return "approvalDoc/goReceptionDocList";
		}
		
		@GetMapping("compReceptionDocList")
		public String compReceptionDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.compReceptionDocList(id);
			model.addAttribute("receptionDocList", list);
			return "approvalDoc/compReceptionDocList";
		}
		
		@GetMapping("rejReceptionDocList")
		public String rejReceptionDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.rejReceptionDocList(id);
			model.addAttribute("receptionDocList", list);
			return "approvalDoc/rejReceptionDocList";
		}
		
		@GetMapping("retReceptionDocList")
		public String retReceptionDocList(Model model, EmpVO empVO, @AuthenticationPrincipal LoginUserVO principal) {
			int id = principal.getUserVO().getEmpId();
			List<DocVO> list = docService.retReceptionDocList(id);
			model.addAttribute("receptionDocList", list);
			return "approvalDoc/retReceptionDocList";
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
	public String docInfo(DocVO docVO, Model model, @AuthenticationPrincipal LoginUserVO principal) {
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
		
		if(findVO.getTaskDocPath().equals("e1")) { return "task/e1"; } else
		if(findVO.getTaskDocPath().equals("e2")) { return "task/e2"; } else 
		if(findVO.getTaskDocPath().equals("e3")) { return "task/e3"; } else
		if(findVO.getTaskDocPath().equals("e4")) { return "task/e4"; } else
		if(findVO.getTaskDocPath().equals("e5")) { return "task/e5"; } else
		if(findVO.getTaskDocPath().equals("e6")) { return "task/e6"; } else
		if(findVO.getTaskDocPath().equals("e7")) { return "task/e7"; } else
		if(findVO.getTaskDocPath().equals("e8")) { return "task/e8"; } else
		if(findVO.getTaskDocPath().equals("e9")) { return "task/e9"; } else
		if(findVO.getTaskDocPath().equals("e10")) { return "task/e10"; } else
		if(findVO.getTaskDocPath().equals("e11")) { return "task/e11"; } else
		if(findVO.getTaskDocPath().equals("e12")) { return "task/e12"; } else
		if(findVO.getTaskDocPath().equals("e13")) { return "task/e13"; } else
		{ return null; }
	}
	// 문서 등록(페이지)
	
	// 문서 등록(처리)
	@PostMapping("docInsert")
	@ResponseBody
	public int docInsert(@RequestBody DocVO docVO) {
		return docService.docInsert(docVO);
	}
	
	// 문서 임시저장(처리)
	@PostMapping("tempInsert")
	@ResponseBody
	public int tempInsert(@RequestBody DocVO docVO) {
		return docService.tempInsert(docVO);
	}
	
	// 문서 수정
	@PostMapping("docUpdate")
	@ResponseBody
	public Map<String, Object> docUpdate(@RequestBody DocVO docVO) {
		return docService.docUpdate(docVO);
	}
	
	// 문서 임시저장 수정
	@PostMapping("tempDocUpdate")
	@ResponseBody
	public Map<String, Object> tempDocUpdate(@RequestBody DocVO docVO) {
		return docService.tempDocUpdate(docVO);
	}
	
	// 문서 삭제
	@PostMapping("docDelete")
	@ResponseBody
	public Map<String, Object> docDelete(@RequestBody List<Integer> docId) {
		return docService.docDelete(docId);
	}
	
	// 문서 삭제(단건)
	@PostMapping("docInfoDelete")
	@ResponseBody
	public String docInfoDelete(@RequestBody String docId) {
		docService.docInfoDelete(Integer.parseInt(docId));
		return "redirect:temporaryDocList";
	}
	
	// 결재 수정
	@PostMapping("approvalDocUpdate")
	@ResponseBody
	public Map<String, Object> approvalDocUpdate(@RequestBody DocVO docVO) {
		return docService.approvalDocUpdate(docVO);
	}
}
