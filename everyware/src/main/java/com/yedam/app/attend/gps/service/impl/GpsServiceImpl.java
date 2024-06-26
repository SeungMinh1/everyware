package com.yedam.app.attend.gps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.attend.gps.mapper.GpsMapper;
import com.yedam.app.attend.gps.service.GpsService;
import com.yedam.app.attend.gps.service.GpsVO;
@Service
public class GpsServiceImpl implements GpsService{
	@Autowired
	GpsMapper gpsMapper;
	
	@Override
	public int insertGps(GpsVO gpsVO) {
		return gpsMapper.insertGpsInfo(gpsVO);
	}

	
}
