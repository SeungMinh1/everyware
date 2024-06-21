package com.yedam.app.mail.mail.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.mail.mail.service.MailService;
import com.yedam.app.mail.mail.service.MailVO;

@Controller
public class MailController {
	@Autowired
	MailService mailService;
	
	//단건 메일함
	@GetMapping("mailboxInfo")
	public String mailboxInfo(MailVO mailVO, Model model) {
		List<MailVO> find = mailService.mailboxInfo(mailVO);
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
	
	
	
		
}
