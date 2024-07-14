package com.yedam.app.attend.gps.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	//GPS등록 페이지로 이동
	@GetMapping("gpsInsert")
	public String insertGpss() {
		return "emp/insertGps";
	}
	
	//GPS정보 등록처리
	@PostMapping("gpsInsert")
	@ResponseBody
	public String insertGps(@RequestBody GpsVO gpsVO) {
		double x = Double.parseDouble(gpsVO.getXxx()); //위도
		double y = Double.parseDouble(gpsVO.getYyy()); //경도
		gpsVO.setLongtitueX(x);
		gpsVO.setLattitueY(y);
		gpsService.insertGps(gpsVO);
		return "redirect:empList";
	}
	
	//GPS 그냥 TRY한거
	@GetMapping("ipgps")
	public String iii() {
		return "/emp/ipgps";
	}
	
	//등록된GPS와 현재위치 거리측정
	@PostMapping("findgps")
	@ResponseBody
	public double findg(@RequestBody GpsVO gpsVO) {
		double x = Double.parseDouble(gpsVO.getXxx());
		double y = Double.parseDouble(gpsVO.getYyy());
		gpsVO.setLongtitueX(x);
		gpsVO.setLattitueY(y);
		return gpsService.findgps(gpsVO);
	}
	
	
	@GetMapping("gpsList")
	public String gpsList(Model model) {
		List<GpsVO> list = gpsService.selectGpsList();
		GpsVO gpsVO = gpsService.selectNowGps();
		model.addAttribute("gps", list);
		model.addAttribute("nowgps", gpsVO);
		return "emp/gpsList";
	}
	
	@PostMapping("thisgps")
	@ResponseBody
	public GpsVO nowgps() {
		return gpsService.selectNowGps();
	}
	
	//등록된GPS와 현재위치 거리측정
	@PostMapping("updateState")
	@ResponseBody
	public int updateState(@RequestBody GpsVO gpsVO) {
		return gpsService.updateState(gpsVO);
	}
	
	

}
