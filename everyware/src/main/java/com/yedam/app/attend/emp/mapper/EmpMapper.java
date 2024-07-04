package com.yedam.app.attend.emp.mapper;

import java.util.List;


import com.yedam.app.attend.emp.service.EmpVO;
import com.yedam.app.common.service.CommonVO;

public interface EmpMapper {
	
	//전체조회
	public List<EmpVO> selectEmpAll(int page, int cnt, String dosearch);
	
	// 단건조회
	public EmpVO selectEmpInfo(EmpVO empVO);
	
	// 등록
	public int insertEmpInfo(EmpVO empVO);
	
	// 수정
	public int updateEmpInfo(EmpVO empVO);
	
	// 삭제
	public int deleteEmpInfo(int empId);
	
	//emp_id 조회
	public int selectEmpId();
	
	//직위 조회
	public List<CommonVO> selectPosition();
	
	//부서 조회
	public List<CommonVO> selectDepartment();
	
	//비밀번호 초기화
	public int resetPwd(EmpVO empVO);
	
	//리스트개수
	public int countList();

	//생산팀 사원 출력
	public List<EmpVO> prodEmpList ();
	
}
