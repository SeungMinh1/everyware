package com.yedam.app.group.controll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Controller
public class GroupController {
	
	@GetMapping("main")
	public String goMain() {
		return "main/index";
	}
	

}
