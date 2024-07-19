package com.yedam.app.attend.gps.service;

import java.util.List;

import com.yedam.app.attach.service.FileVO;

public interface GpsService {
	
	public int insertGps(GpsVO gpsVO);
	
	public double findgps(GpsVO gpsVO);
	
	public List<GpsVO> selectGpsList();
	
	//현재적용중인 GPS
	public GpsVO selectNowGps();
	
	//현재적용중인 GPS
	public int updateState(GpsVO gpsVO);
	
	//gps삭제
	public int deleteGps(GpsVO gpsVO);
	
	public FileVO selectLogo(); 
}
