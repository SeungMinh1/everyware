package com.yedam.app.attend.emp.service;

import java.util.List;
import java.util.Map;

public interface EmpService {
	
	//전체 사원정보 조회
	public List<EmpVO> empList();
	//사원정보 조회
	public EmpVO empInfo(EmpVO empVO);
	//사원정보 등록
	public int empInsert(EmpVO empVO);
	//사원정보 수정
	public Map<String, Object> empUpdate(EmpVO empVO);
	//사원정보 삭제
	public int empDelete(int empId);
	
	//비밀번호 초기화
	public int resetPwd(int empId);
}
