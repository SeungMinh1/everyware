package com.yedam.app.approval.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.approval.service.DocService;
import com.yedam.app.approval.service.DocVO;
import com.yedam.app.approval.service.DraftVO;
import com.yedam.app.approval.service.PageVO;
import com.yedam.app.approval.service.TaskVO;
import com.yedam.app.attend.security.service.LoginUserVO;

@Controller
public class DocController {
	@Autowired
	DocService docService;
	
	// 문서 조회
		// 결재 대기 문서
		@GetMapping("waitDocList")
		public String waitDocList(Model model, Integer page, Integer cnt, String dosearch) {
			page = page == null ? 1 : page;
			cnt = cnt == null ? 3 : cnt;
			int allCount = docService.cntList();
			PageVO pg = new PageVO(page, allCount, cnt);
			
			List<DocVO> list = docService.waitDocList(page, cnt, dosearch);
			model.addAttribute("waitDocList", list);
			model.addAttribute("pg", pg);
			return "approvalDoc/waitDocList";
		}
		
		// 참조/열람 대기 문서
		@GetMapping("waitRefViewDocList")
		public String waitRefViewDocList(Model model, Integer page, Integer cnt, String dosearch) {
			page = page == null ? 1 : page;
			cnt = cnt == null ? 3 : cnt;
			int allCount = docService.cntList();
			PageVO pg = new PageVO(page, allCount, cnt);
			
			List<DocVO> list = docService.waitRefViewDocList(page, cnt, dosearch);
			model.addAttribute("waitRefViewDocList", list);
			model.addAttribute("pg", pg);
			return "approvalDoc/waitRefViewDocList";
		}
		
		// 결재 예정 문서
		@GetMapping("expectedDocList")
		public String expectedDocList(Model model, Integer page, Integer cnt, String dosearch) {
			page = page == null ? 1 : page;
			cnt = cnt == null ? 3 : cnt;
			int allCount = docService.cntList();
			PageVO pg = new PageVO(page, allCount, cnt);
			
			List<DocVO> list = docService.expectedDocList(page, cnt, dosearch);
			model.addAttribute("expectedDocList", list);
			model.addAttribute("pg", pg);
			return "approvalDoc/expectedDocList";
		}
		
		// 기안 문서
		@GetMapping("draftDocList")
		public String draftDocList(Model model, Integer page, Integer cnt, String dosearch, @AuthenticationPrincipal LoginUserVO principal) {
			page = page == null ? 1 : page;
			cnt = cnt == null ? 3 : cnt;
			int allCount = docService.cntList();
			PageVO pg = new PageVO(page, allCount, cnt);
			
			List<DocVO> list = docService.draftDocList(page, cnt, dosearch);
			model.addAttribute("draftDocList", list);
			model.addAttribute("pg", pg);
			return "approvalDoc/draftDocList";
		}
		
		// 결재 문서
		@GetMapping("approvalDocList")
		public String approvalDocList(Model model, Integer page, Integer cnt, String dosearch) {
			page = page == null ? 1 : page;
			cnt = cnt == null ? 3 : cnt;
			int allCount = docService.cntList();
			PageVO pg = new PageVO(page, allCount, cnt);
			
			List<DocVO> list = docService.approvalDocList(page, cnt, dosearch);
			model.addAttribute("approvalDocList", list);
			model.addAttribute("pg", pg);
			return "approvalDoc/approvalDocList";
		}
		
		// 발송 문서
		@GetMapping("sendDocList")
		public String sendDocList(Model model, Integer page, Integer cnt, String dosearch) {
			page = page == null ? 1 : page;
			cnt = cnt == null ? 3 : cnt;
			int allCount = docService.cntList();
			PageVO pg = new PageVO(page, allCount, cnt);
			
			List<DocVO> list = docService.sendDocList(page, cnt, dosearch);
			model.addAttribute("sendDocList", list);
			model.addAttribute("pg", pg);
			return "approvalDoc/sendDocList";
		}
		
		// 수신 문서
		@GetMapping("receptionDocList")
		public String receptionDocList(Model model, Integer page, Integer cnt, String dosearch) {
			page = page == null ? 1 : page;
			cnt = cnt == null ? 3 : cnt;
			int allCount = docService.cntList();
			PageVO pg = new PageVO(page, allCount, cnt);
			
			List<DocVO> list = docService.receptionDocList(page, cnt, dosearch);
			model.addAttribute("receptionDocList", list);
			model.addAttribute("pg", pg);
			return "approvalDoc/receptionDocList";
		}
		
		// 참조/열람 문서
		@GetMapping("refViewDocList")
		public String refViewDocList(Model model, Integer page, Integer cnt, String dosearch) {
			page = page == null ? 1 : page;
			cnt = cnt == null ? 3 : cnt;
			int allCount = docService.cntList();
			PageVO pg = new PageVO(page, allCount, cnt);
			
			List<DocVO> list = docService.refViewDocList(page, cnt, dosearch);
			model.addAttribute("refViewDocList", list);
			model.addAttribute("pg", pg);
			return "approvalDoc/refViewDocList";
		}
		
			// 참조 문서
			@GetMapping("refDocList")
			public String refDocList(Model model, Integer page, Integer cnt, String dosearch) {
				page = page == null ? 1 : page;
				cnt = cnt == null ? 3 : cnt;
				int allCount = docService.cntList();
				PageVO pg = new PageVO(page, allCount, cnt);
				
				List<DocVO> list = docService.refDocList(page, cnt, dosearch);
				model.addAttribute("refDocList", list);
				model.addAttribute("pg", pg);
				return "approvalDoc/refDocList";
			}
			
			// 열람 문서
			@GetMapping("viewDocList")
			public String viewDocList(Model model, Integer page, Integer cnt, String dosearch) {
				page = page == null ? 1 : page;
				cnt = cnt == null ? 3 : cnt;
				int allCount = docService.cntList();
				PageVO pg = new PageVO(page, allCount, cnt);
				
				List<DocVO> list = docService.viewDocList(page, cnt, dosearch);
				model.addAttribute("viewDocList", list);
				model.addAttribute("pg", pg);
				return "approvalDoc/viewDocList";
			}
			
		// 임시 문서
		@GetMapping("temporaryDocList")
		public String temporaryDocList(Model model, Integer page, Integer cnt, String dosearch) {
			page = page == null ? 1 : page;
			cnt = cnt == null ? 3 : cnt;
			int allCount = docService.cntList();
			PageVO pg = new PageVO(page, allCount, cnt);
			
			List<DocVO> list = docService.temporaryDocList(page, cnt, dosearch);
			model.addAttribute("temporaryDocList", list);
			model.addAttribute("pg", pg);
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
	public String newTaskForm(Model model) {
		List<TaskVO> list = docService.newTask();
		// List<TaskVO> category = docService.category();
		model.addAttribute("newTask", list);
		// model.addAttribute("category", category);
		return "draft/newTask";
	}
	
	@GetMapping("task")
	public String task(TaskVO taskVO, DraftVO draftVO, Model model) {
		TaskVO findVO = docService.task(taskVO);
		model.addAttribute("task", findVO);
		
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
	
	// 문서 수정
	
	// 문서 삭제
	
}
