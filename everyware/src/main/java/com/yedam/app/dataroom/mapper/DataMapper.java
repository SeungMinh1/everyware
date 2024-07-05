package com.yedam.app.dataroom.mapper;

import java.util.List;

import com.yedam.app.dataroom.service.DataVO;

public interface DataMapper {

	//부서번호 조회
	public String selectDeptId(int empId);
	
	//자료실 조회
	public List<DataVO> selectDataCommon(DataVO dataVO);
	public List<DataVO> selectDataDept(DataVO dataVO);
	public List<DataVO> selectDataMe(DataVO dataVO);
	
	//자료 등록 + group Id 체크
	public int insertData(DataVO dataVO);
	//자료 조회 
	public List<DataVO> selectData(int dataId);
}
