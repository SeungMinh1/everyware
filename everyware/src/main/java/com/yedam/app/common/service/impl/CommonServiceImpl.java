package com.yedam.app.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.common.mapper.CommonMapper;
import com.yedam.app.common.service.CommonService;
import com.yedam.app.common.service.CommonVO;
@Service
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	CommonMapper commonMapper;

	@Override
	public CommonVO commonInfo(CommonVO commonVO) {
		return commonMapper.selectCommonCode(commonVO);
	}



}
