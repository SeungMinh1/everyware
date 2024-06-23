package com.yedam.app.common.mapper;

import com.yedam.app.common.service.CommonVO;

public interface CommonMapper {
	
	// 공통코드 단건조회
	public CommonVO selectCommonCode(CommonVO commonVO);

}
