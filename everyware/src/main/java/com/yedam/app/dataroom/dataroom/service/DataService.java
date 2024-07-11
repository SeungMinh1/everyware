package com.yedam.app.dataroom.dataroom.service;

import java.util.List;
import java.util.Map;

public interface DataService {

	//부서번호 조회
	public String selectDeptId(int empId);
	
	//자료실 조회
	public List<DataVO> dataList(DataVO dataVO, int page, 
				int cnt, String category, String remarks);
	
	//자료실 cnt
	public int cntDataList(DataVO dataVO, String category, String remarks);
	
	//자료 등록 + 첨부파일 등록
	public int insertData(DataVO dataVO);
	//자료 조회
	public List<DataVO> selectDataInfo(DataVO dataVO);
	
	//자료삭제 여러개
	public Map<String, Object> deleteData(List<Integer> dataId);
	//dataIds로 파일삭제 
	public Map<String, Object> deleteFiles(List<Integer> dataIds);
	
	//자료삭제 (+ 프로시저)
	//public int deleteData(int dataId);
}
