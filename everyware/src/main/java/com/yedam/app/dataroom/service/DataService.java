package com.yedam.app.dataroom.service;

import java.util.List;

public interface DataService {

	//부서번호 조회
	public String selectDeptId(int empId);
	
	//자료실 조회
	public List<DataVO> dataListCommon(DataVO dataVO);
	public List<DataVO> dataListDept(DataVO dataVO);
	public List<DataVO> dataListMe(DataVO dataVO);
	
	//자료 등록 + 첨부파일 등록
	public int insertData(DataVO dataVO);
	//자료 조회
	public DataVO selectDataInfo(int dataId);
}
