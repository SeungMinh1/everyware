package com.yedam.app.attend.gps.mapper;

import java.util.List;

import com.yedam.app.attend.gps.service.GpsVO;

public interface GpsMapper {
	//등록
	public int insertGpsInfo(GpsVO gpsVO);
	
	//거리계산
	public double findDistance(GpsVO gpsVO);
	
	//GPS리스트
	public List<GpsVO>selectGpsList();
	
	//현재적용중인 GPS
	public GpsVO selectNowGps();
	
	//적용중인 GPS변경
	public int updateState(GpsVO gpsVO);
	
	//gps삭제
	public int deleteGps(GpsVO gpsVO);
	
}
