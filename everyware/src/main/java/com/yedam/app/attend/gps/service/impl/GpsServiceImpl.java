package com.yedam.app.attend.gps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.yedam.app.attend.gps.mapper.GpsMapper;
import com.yedam.app.attend.gps.service.GpsService;
import com.yedam.app.attend.gps.service.GpsVO;

public class GpsServiceImpl implements GpsService{
	@Autowired
	GpsMapper gpsMapper;
	
	@Override
	public int insertGps(GpsVO gpsVO) {
		return gpsMapper.insertGpsInfo(gpsVO);
	}

	
}
