package com.yedam.app.attend.gps.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.attend.gps.service.GpsService;
import com.yedam.app.attend.gps.service.GpsVO;

@Controller
public class GpsController {
	
	@Autowired
	GpsService gpsService;
	
	@GetMapping("gpsInsert")
	public String insertGpss() {
		return "emp/insertGps";
	}
	
	@PostMapping("gpsInsert")
	@ResponseBody
	public String insertGps(@RequestBody GpsVO gpsVO) {
		double x = Double.parseDouble(gpsVO.getXxx());
		double y = Double.parseDouble(gpsVO.getYyy());
		gpsVO.setLongtitueX(x);
		gpsVO.setLattitueY(y);
		gpsService.insertGps(gpsVO);
		return "redirect:empList";
	}

}
