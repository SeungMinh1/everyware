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
	public List<DataVO> selectData(DataVO dataVO);
	
	//삭제
	public int deleteData(List<Integer> dataId);
	
	
	//자료 삭제 (+ 프로시저)
	public int deleteData1(int dataId);
	//자료번호의 그룹아이디를 가진 첨부파일 번호 가져오기
	public List<DataVO> selectFileId(DataVO dataVO);
	
}
