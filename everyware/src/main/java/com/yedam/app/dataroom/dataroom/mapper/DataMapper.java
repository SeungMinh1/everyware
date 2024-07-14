package com.yedam.app.dataroom.dataroom.mapper;

import java.util.List;

import com.yedam.app.dataroom.dataroom.service.DataVO;

public interface DataMapper {

	//부서번호 조회
	public String selectDeptId(int empId);
	
	//자료실 조회
	public List<DataVO> selectDataList(DataVO dataVO, int page, 
			int cnt, String category, String remarks);
	
	//자료실 cnt
	public int cntDataList(DataVO dataVO, String category, String remarks);
	
	
	//자료 등록 + group Id 체크
	public int insertData(DataVO dataVO);
	
	//자료 조회 
	public List<DataVO> selectData(DataVO dataVO);
	
	//자료 삭제
	public int deleteData(List<Integer> dataId);
	//data_id의 그룹아이디를 가진 파일 삭제 
	public int deleteFiles(List<Integer> dataIds);
	
	
	//자료 삭제 (+ 프로시저)
	//public int deleteData1(int dataId);
	
	
}
