package com.yedam.app.mail.mail.web;

import java.util.ArrayList;
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

import com.yedam.app.attend.security.service.LoginUserVO;
import com.yedam.app.mail.mail.service.MailService;
import com.yedam.app.mail.mail.service.MailVO;

@Controller
public class MailController {
	@Autowired
	MailService mailService;
	
	//단건 메일함
	@GetMapping("mailboxInfo")
	public String mailboxInfo(MailVO mailVO, Model model, @AuthenticationPrincipal LoginUserVO principal) {
		int empId = principal.getuserVO().getEmpId();
		List<MailVO> find = mailService.mailboxInfo(mailVO, empId);
		
		model.addAttribute("mailboxInfo", find);
		return "mail/mail_list";
	}
	
	//단건 메일 상세페이지
	@GetMapping("mailInfo")
	public String mailInfo(MailVO mailVO, Model model) {
		MailVO find = mailService.mailInfo(mailVO);
		model.addAttribute("mail", find);
		return "mail/mail_info";
	}
	
	//메일 등록 - 페이지
	@GetMapping("mailInsert")
	public String mailInsertForm(Model model, @AuthenticationPrincipal LoginUserVO principal) {
		MailVO mailVO = new MailVO();
		int empId = principal.getuserVO().getEmpId();
		String email = mailService.emailSelect(empId);
		mailVO.setSender(email);
		model.addAttribute("mailVO", mailVO);
		return "mail/mail_insert";
	}
	
	//메일 등록 - 처리
	@PostMapping("mailInsert")
	@ResponseBody
	public int mailInsertProcess(@RequestBody MailVO mailVO) {
		return mailService.senderMail(mailVO);
	}
	
	
	//임시보관함 메일 등록 - 처리
	@PostMapping("draftMailInsert")
	@ResponseBody
	public int draftMailInsertProcess(@RequestBody MailVO mailVO) {
		return mailService.draftMail(mailVO);
	}
	
	//임시보관함 메일 수정 - 페이지
	@GetMapping("draftMailUpdate")
	public String draftMailForm(MailVO mailVO, Model model) {
		MailVO find = mailService.mailInfo(mailVO);
		model.addAttribute("draft", find);
		return "mail/mail_update";
	}
	
	//임시보관함 메일 수정 - 처리(저장)
	@PostMapping("draftMailUpdate")
	@ResponseBody
	public Map<String, Object> draftMailUpdate(@RequestBody MailVO mailVO){
		return mailService.updateDraftMail(mailVO);
	}
	
	//메일 삭제
	@GetMapping("mailDelete")
	public String mailDelete(Integer mailId) {
		mailService.deleteMail(mailId);
		return "redirect:mailboxInfo?mailboxId=d1";
	}
}
