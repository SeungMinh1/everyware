package com.yedam.app.attend.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
