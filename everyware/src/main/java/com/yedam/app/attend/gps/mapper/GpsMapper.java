package com.yedam.app.attend.gps.mapper;

import com.yedam.app.attend.gps.service.GpsVO;

public interface GpsMapper {
	//등록
	public int insertGpsInfo(GpsVO gpsVO);
	
	//거리계산
	public double findDistance(GpsVO gpsVO);
}
