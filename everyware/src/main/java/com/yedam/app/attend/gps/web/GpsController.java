package com.yedam.app.attend.gps.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.attend.gps.service.GpsVO;

@Controller
public class GpsController {
	
	@GetMapping("gps")
	public String insertGps() {
		return "emp/gps";
	}
	
	@GetMapping("gpss")
	public String insertGpss() {
		return "emp/insertGps";
	}
	
//	@PostMapping("insertGps")
//	public String insertGps(GpsVO gpsVO) {
//		return "redirect:empList";
//	}

}
