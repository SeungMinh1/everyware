package com.yedam.app.attend.emp.service;

import java.util.List;
import java.util.Map;

import com.yedam.app.common.service.CommonVO;

public interface EmpService {
	
	//전체 사원정보 조회
	public List<EmpVO> empList(int page, int cnt, String dosearch);
	//사원정보 조회
	public EmpVO empInfo(EmpVO empVO);
	//사원정보 등록
	public int empInsert(EmpVO empVO);
	//사원정보 수정
	public Map<String, Object> empUpdate(EmpVO empVO);
	//사원정보 삭제
	public int empDelete(int empId);
	
	//사원번호 조회
	public int searchEmpId();
	
	//직위 조회
	public List<CommonVO> posList();
	
	//부서 조회
	public List<CommonVO> departmentList();
	
	//비밀번호 초기화
	public int resetPwd(EmpVO empVO);
	
	//리스트 개수
	public int cntList();
	
	//생산팀 사원 출력
	public List<EmpVO> prodEmpList();
}
