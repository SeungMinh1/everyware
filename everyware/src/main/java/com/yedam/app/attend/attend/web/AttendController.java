package com.yedam.app.attend.attend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AttendController {
	@GetMapping("attend")
	public String attendhome() {
		return "emp/attend";
	}

}
