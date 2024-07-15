package com.yedam.app.group.controll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.attend.emp.service.EmpService;
import com.yedam.app.common.util.AuthUtil;
import com.yedam.app.dataroom.dataroom.service.DataPageDTO;
import com.yedam.app.dataroom.file.service.DataFileService;
import com.yedam.app.mail.mail.service.MailService;
import com.yedam.app.mail.mail.service.MailVO;


@Controller
public class GroupController {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	DataFileService dataFileService;
	
	@Autowired
	EmpService empService;
	
	
	@GetMapping("main")
	public String goMain(Model model) { 
		MailVO mailVO = new MailVO();
		mailVO.setMailboxId("d1");
		
		int empId = AuthUtil.getEmpId();
		List<MailVO> find = mailService.mailboxInfo(mailVO, empId);
		
		model.addAttribute("mailboxInfo", find);

		
		return "main/index";
	}
	
	@GetMapping("/")
	public String home() { 
		return "emp/login";
	}

}
