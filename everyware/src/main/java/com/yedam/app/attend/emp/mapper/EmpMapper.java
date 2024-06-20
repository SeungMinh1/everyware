package com.yedam.app.attend.emp.mapper;

import java.util.List;

import com.yedam.app.attend.emp.service.EmpVO;

public interface EmpMapper {
	
	//전체조회
	public List<EmpVO> selectEmpAll();
	
	// 단건조회
	public EmpVO selectEmpInfo(EmpVO empVO);
	
	// 등록
	public int insertEmpInfo(EmpVO empVO);
	
	// 수정
	public int updateEmpInfo(EmpVO empVO);
	
	// 삭제
	public int deleteEmpInfo(int empId);

}
