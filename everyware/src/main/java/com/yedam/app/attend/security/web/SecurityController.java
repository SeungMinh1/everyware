package com.yedam.app.attend.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.attach.service.FileVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SecurityController {
	

	
	@GetMapping("login")
	public String login(String errorMessage, Model model) {
		model.addAttribute("err", errorMessage);
		return "emp/login";
	}
	

}
