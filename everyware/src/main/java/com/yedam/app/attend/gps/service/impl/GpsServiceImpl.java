package com.yedam.app.attend.gps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.attend.gps.mapper.GpsMapper;
import com.yedam.app.attend.gps.service.GpsService;
import com.yedam.app.attend.gps.service.GpsVO;

import groovy.util.logging.Slf4j;
@Service
@Slf4j
public class GpsServiceImpl implements GpsService{
	@Autowired
	GpsMapper gpsMapper;
	
	@Override
	public int insertGps(GpsVO gpsVO) {
		return gpsMapper.insertGpsInfo(gpsVO);
	}

	@Override
	public double findgps(GpsVO gpsVO) {
		System.out.println(gpsVO.getLattitueY());
//		log.info(gpsVO.getLongtitueX());
		return gpsMapper.findDistance(gpsVO);
	}

	@Override
	public List<GpsVO> selectGpsList() {
		return gpsMapper.selectGpsList();
	}

	@Override
	public GpsVO selectNowGps() {
		return gpsMapper.selectNowGps();
	}

	@Override
	public int updateState(GpsVO gpsVO) {
		return gpsMapper.updateState(gpsVO);
	}

	
}
