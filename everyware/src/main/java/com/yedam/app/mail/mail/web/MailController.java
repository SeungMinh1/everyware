package com.yedam.app.mail.mail.web;

import java.util.HashMap;
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
import com.yedam.app.common.util.AuthUtil;
import com.yedam.app.dataroom.dataroom.service.DataPageDTO;
import com.yedam.app.dataroom.file.service.DataFileService;
import com.yedam.app.mail.mail.service.MailService;
import com.yedam.app.mail.mail.service.MailVO;

@Controller
public class MailController {
	@Autowired
	MailService mailService;
	
	@Autowired
	DataFileService dataFileService;
	
	@Autowired
	EmpService empService;
	
	//조회 : 단건 메일함
	@GetMapping("mailboxInfo")
	public String mailboxInfo(MailVO mailVO, Model model, @AuthenticationPrincipal LoginUserVO principal) {
		int empId = principal.getUserVO().getEmpId();
		List<MailVO> find = mailService.mailboxInfo(mailVO, empId);
		model.addAttribute("mailboxInfo", find);
		return "mail/mail_list";
	}
	
	//조회 : 단건 메일 상세페이지
	@GetMapping("mailInfo")
	public String mailInfo(MailVO mailVO, Model model) {
		MailVO mvo = mailService.mailInfo(mailVO);
		//List<DataFileVO> data = dataFileService.selectFileByMailId(mailVO);
		model.addAttribute("mail", mvo);
		//model.addAttribute("datas", data);
		return "mail/mail_info";
	}
	
	//등록 : 메일 등록 - 페이지
	@GetMapping("mailInsert")
	public String mailInsertForm(Model model, @AuthenticationPrincipal LoginUserVO principal) {
		MailVO mailVO = new MailVO();
		int empId = principal.getUserVO().getEmpId();
		String email = mailService.emailSelect(empId);
		mailVO.setSender(email);
		model.addAttribute("mailVO", mailVO);
		return "mail/mail_insert";
	}
	
	//등록 : 메일 등록 - 처리
	@PostMapping("mailInsert")
	@ResponseBody
	public int mailInsertProcess(@RequestBody MailVO mailVO) {
		return mailService.senderMail(mailVO);
	}
	
	
	//등록 : 임시보관함 메일 등록 - 처리
	@PostMapping("draftMailInsert")
	@ResponseBody
	public int draftMailInsertProcess(@RequestBody MailVO mailVO) {
		return mailService.draftMail(mailVO);
	}
	
	//수정 : 임시보관함 메일 수정 - 페이지
	@GetMapping("draftMailUpdate")
	public String draftMailForm(MailVO mailVO, Model model) {
		MailVO find = mailService.mailInfo(mailVO);
		model.addAttribute("draft", find);
		return "mail/mail_update";
	}
	
	//수정 : 임시보관함 메일 수정 - 처리(저장)
	@PostMapping("draftMailUpdate")
	@ResponseBody
	public Map<String, Object> draftMailUpdate(@RequestBody MailVO mailVO){
		return mailService.updateDraftMail(mailVO);
	}
	
	//수정 : 휴지통 이동 (여러개)
	@PostMapping("moveTrashMail")
	@ResponseBody
	public String moveTrashMail(@RequestBody List<Integer> mailIds, Model model) {
	    Map<String, Object> result = mailService.moveTrashMail(mailIds);
	    model.addAttribute("result", result);
	    return "redirect:mailboxInfo?mailboxId=d1";
	}
	
	// 수정 : 휴지통 이동 (단건)
	@PostMapping("moveTrashMailInfo")
	@ResponseBody
	public int moveTrashMailInfo(@RequestBody int mailId) {	
		return mailService.moveTrashMailInfo(mailId);
	}

	//삭제 : 메일 완전 삭제 (여러개)
	@PostMapping("mailDelete")
	@ResponseBody
	public Map<String, Object> mailDelete(@RequestBody List<Integer> mailIds) {
		return mailService.deleteMail(mailIds);
	}
	
	//삭제 : 메일 완전 삭제 (단건)
	@GetMapping("mailInfoDelete")
	public String mailInfoDelete(int mailId) {
		mailService.deleteMailInfo(mailId);
		return "redirect:mailboxInfo?mailboxId=d5";
	}
	
	//수정: 휴지통에서 메일 복구 (여러개)
	@PostMapping("restoreMailUpdate")
	@ResponseBody
	public int restoreMailUpdate(@RequestBody MailVO mailVO) {
	    return mailService.moveRestoreMail(mailVO);
	}
	
	//답장: mailInfo가져와서 조회
	@GetMapping("replyMail")
	public String replyMailForm(MailVO mailVO, Model model, @AuthenticationPrincipal LoginUserVO principal) {
		MailVO info = mailService.mailInfo(mailVO);
		String sender1 = info.getSender(); //Original 보낸 사람
		String recip = info.getRecipient();//Original 받는 사람
		info.setRecipient(sender1); //Original 보낸사람을 받는사람에 넣기
		
		int empId = principal.getUserVO().getEmpId(); //empId로 email 조회
		String email = mailService.emailSelect(empId);
		info.setSender(email);	//보낸사람에 로그인한 사원의 email 넣기
		
		model.addAttribute("sender", sender1); 
		model.addAttribute("recip", recip);
		model.addAttribute("info", info);
		
		return "mail/mail_reply";
	}
	
	//삭제 : 임시보관 메일 완전 삭제 (단건)
	@PostMapping("deleteDraftMailInfo")
	@ResponseBody
	public int deleteDraftMailInfo(@RequestBody MailVO mailVO) {
		int mailId = mailVO.getMailId();
		return mailService.deleteMailInfo(mailId);
	}
	
	
	//============주소록===============

	//전체조회
	@GetMapping("empSearch")
	@ResponseBody
	public Map<String, Object> empList(
			              Integer page, 
			              Integer cnt, 
			              String dosearch,
			              String searchOption,
			              @AuthenticationPrincipal LoginUserVO principal) {
		page = page == null ? 1 : page; //페이지 default 설정
		cnt = cnt == null ? 10 : cnt; 	// 사원수 default 설정
		int allCount = empService.cntList(dosearch, searchOption); // 전체 사원수 count 
		PageDTO pg = new PageDTO(page, allCount, cnt); //페이징
		
		 System.out.println("searchOption: " + searchOption);
		    System.out.println("dosearch: " + dosearch);
		
		Map<String, Object> map = new HashMap<>();
		
		List<EmpVO> list = empService.empList(page, cnt, dosearch, searchOption); //전체사원리스트
		map.put("empList", list);
		map.put("pg", pg);
		return map;
	}
}
